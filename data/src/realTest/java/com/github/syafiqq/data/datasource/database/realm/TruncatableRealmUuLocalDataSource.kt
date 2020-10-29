package com.github.syafiqq.data.datasource.database.realm

import com.github.syafiqq.common.contract.data.Truncatable
import com.github.syafiqq.data.datasource.database.entity.UuDocumentEntity
import com.github.syafiqq.data.datasource.database.entity.UuEntity
import com.github.syafiqq.data.datasource.database.entity.UuYearEntity
import com.github.syafiqq.data.datasource.database.realm.di.RealmModule
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
            val realm = Realm.getInstance(internalConfig)
            realm.use { db ->
                db.delete(UuDocumentEntity::class.java)
                db.delete(UuYearEntity::class.java)
                db.delete(UuEntity::class.java)
            }
        }
    }
}
