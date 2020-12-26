package com.github.syafiqq.data.datasource.database.realm.di

import android.content.Context
import com.github.syafiqq.common.error.DefinedException
import dagger.Module
import dagger.Provides
import io.realm.Realm
import io.realm.RealmConfiguration
import timber.log.Timber
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
object RealmModule {
    @Qualifier
    @MustBeDocumented
    @Retention(AnnotationRetention.RUNTIME)
    annotation class InternalRealm

    @Qualifier
    @Retention(AnnotationRetention.RUNTIME)
    annotation class ExternalRealm

    @Singleton
    @InternalRealm
    @Provides
    fun provideInternalRealmConfiguration(): RealmConfiguration {
        val configuration = RealmConfiguration
            .Builder()
            //.encryptionKey(key)
            //.schemaVersion(REALM_NEXT_SCHEMA_VERSION)
            //.migration(RealmMigration())
            .deleteRealmIfMigrationNeeded()
            .build()
        try {
            Realm.getInstance(configuration)
        } catch (e: Exception) {
            Timber.e(e)
            Realm.deleteRealm(configuration)
            Realm.getInstance(configuration)
        }
        return configuration
    }

    @Singleton
    @ExternalRealm
    @Provides
    fun provideExternalRealmConfiguration(context: Context): RealmConfiguration {
        val file =
            context.getExternalFilesDir(null) ?: throw DefinedException("No Storage provided")
        val configuration = RealmConfiguration
            .Builder()
            .directory(file)
            //.encryptionKey(key)
            //.schemaVersion(REALM_NEXT_SCHEMA_VERSION)
            //.migration(RealmMigration())
            .deleteRealmIfMigrationNeeded()
            .build()
        try {
            Realm.getInstance(configuration)
        } catch (e: Exception) {
            Timber.e(e)
            Realm.deleteRealm(configuration)
            Realm.getInstance(configuration)
        }
        return configuration
    }
}
