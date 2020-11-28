package com.github.syafiqq.realtestutil.data.datasource.database.realm

import com.github.syafiqq.common.contract.data.Truncatable
import com.github.syafiqq.data.datasource.database.realm.di.RealmModule
import com.github.syafiqq.data.datasource.database.realm.entity.UuDocumentEntity
import com.github.syafiqq.data.datasource.database.realm.entity.UuEntity
import com.github.syafiqq.data.datasource.database.realm.entity.UuYearEntity
import com.github.syafiqq.data.datasource.database.realm.implementation.RealmUuLocalDataSource
import io.realm.Realm
import io.realm.RealmConfiguration
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class TruncatableRealmUuLocalDataSource @Inject constructor(
    @RealmModule.InternalRealm internalConfig: RealmConfiguration,
    @RealmModule.ExternalRealm externalConfig: RealmConfiguration,
) : RealmUuLocalDataSource(internalConfig, externalConfig), Truncatable {
    override suspend fun truncate() {
        withContext(Dispatchers.IO) {
            val internalRealm = Realm.getInstance(internalConfig)
            internalRealm.use { db ->
                db.executeTransaction { transaction ->
                    transaction.delete(UuYearEntity::class.java)
                    transaction.delete(UuEntity::class.java)
                }
            }
            val externalRealm = Realm.getInstance(externalConfig)
            externalRealm.use { db ->
                db.executeTransaction { transaction ->
                    transaction.delete(UuDocumentEntity::class.java)
                }
            }
        }
    }
}
