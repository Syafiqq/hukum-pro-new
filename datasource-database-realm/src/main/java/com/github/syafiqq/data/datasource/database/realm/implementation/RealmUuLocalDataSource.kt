package com.github.syafiqq.data.datasource.database.realm.implementation

import com.github.syafiqq.data.datasource.database.realm.contract.UuLocalDataSource
import com.github.syafiqq.data.datasource.database.realm.di.RealmModule
import com.github.syafiqq.data.datasource.database.realm.entity.UuDocumentEntity
import com.github.syafiqq.data.datasource.database.realm.entity.UuEntity
import com.github.syafiqq.data.datasource.database.realm.entity.UuYearEntity
import com.github.syafiqq.data.datasource.database.realm.util.error.RealmExceptionFactory
import io.realm.Realm
import io.realm.RealmConfiguration
import io.realm.kotlin.where
import kotlinx.coroutines.coroutineScope
import javax.inject.Inject

open class RealmUuLocalDataSource @Inject constructor(
    @RealmModule.InternalRealm val internalConfig: RealmConfiguration,
    @RealmModule.ExternalRealm val externalConfig: RealmConfiguration,
) : UuLocalDataSource {
    override suspend fun storeUu(uu: List<UuEntity>) {
        coroutineScope {
            val realm = Realm.getInstance(internalConfig)
            realm.use { db ->
                db.executeTransaction { transaction ->
                    transaction.insertOrUpdate(uu)
                }
            }
        }
    }

    override suspend fun storeUuYear(entity: List<UuYearEntity>) {
        coroutineScope {
            val realm = Realm.getInstance(internalConfig)
            realm.use { db ->
                db.executeTransaction { transaction ->
                    transaction.insertOrUpdate(entity)
                }
            }
        }
    }

    override suspend fun storeUuDocument(document: List<UuDocumentEntity>) {
        coroutineScope {
            val realm = Realm.getInstance(externalConfig)
            realm.use { db ->
                db.executeTransaction { transaction ->
                    transaction.insertOrUpdate(document)
                }
            }
        }
    }

    override suspend fun updateUuDocument(id: String, document: String) {
        coroutineScope {
            val realm = Realm.getInstance(externalConfig)
            if (!realm.isAutoRefresh) {
                realm.refresh()
            }
            val obj = realm
                .where<UuDocumentEntity>()
                .equalTo("id", id)
                .findFirst()
                ?: throw RealmExceptionFactory.createNoDataException()
            realm.use { db ->
                db.executeTransaction {
                    obj.document = document
                }
            }
        }
    }

    override suspend fun fetchUu(id: String): UuEntity {
        return coroutineScope {
            val realm = Realm.getInstance(internalConfig)
            if (!realm.isAutoRefresh) {
                realm.refresh()
            }

            realm
                .where<UuEntity>()
                .equalTo("id", id)
                .findFirst()
                ?: throw RealmExceptionFactory.createNoDataException()
        }
    }

    override suspend fun fetchUuByCategoryAndYear(category: Int, year: Int): List<UuEntity> {
        return coroutineScope {
            val realm = Realm.getInstance(internalConfig)
            if (!realm.isAutoRefresh) {
                realm.refresh()
            }

            realm
                .where<UuEntity>()
                .equalTo("category", category)
                .equalTo("year", year)
                .findAll()
        }
    }

    override suspend fun fetchUuDocument(id: String): UuDocumentEntity {
        return coroutineScope {
            val realm = Realm.getInstance(externalConfig)
            if (!realm.isAutoRefresh) {
                realm.refresh()
            }

            realm
                .where<UuDocumentEntity>()
                .equalTo("id", id)
                .findFirst()
                ?: throw RealmExceptionFactory.createNoDataException()
        }
    }

    override suspend fun fetchUuYear(category: Int): List<UuYearEntity> {
        return coroutineScope {
            val realm = Realm.getInstance(internalConfig)
            if (!realm.isAutoRefresh) {
                realm.refresh()
            }

            realm
                .where<UuYearEntity>()
                .equalTo("category", category)
                .findAll()
                ?: throw RealmExceptionFactory.createNoDataException()
        }
    }

    override suspend fun removeAllUu() {
        coroutineScope {
            val realm = Realm.getInstance(internalConfig)
            if (!realm.isAutoRefresh) {
                realm.refresh()
            }

            realm.use { db ->
                db.executeTransaction { transaction ->
                    transaction.delete(UuEntity::class.java)
                }
            }
        }
    }

    override suspend fun removeAllUuDocument() {
        coroutineScope {
            val realm = Realm.getInstance(externalConfig)
            if (!realm.isAutoRefresh) {
                realm.refresh()
            }

            realm.use { db ->
                db.executeTransaction { transaction ->
                    transaction.delete(UuDocumentEntity::class.java)
                }
            }
        }
    }

    override suspend fun removeAllUuYear() {
        coroutineScope {
            val realm = Realm.getInstance(internalConfig)
            if (!realm.isAutoRefresh) {
                realm.refresh()
            }

            realm.use { db ->
                db.executeTransaction { transaction ->
                    transaction.delete(UuYearEntity::class.java)
                }
            }
        }
    }
}
