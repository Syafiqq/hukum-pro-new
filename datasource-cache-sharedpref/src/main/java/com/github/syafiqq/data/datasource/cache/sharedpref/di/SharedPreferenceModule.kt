package com.github.syafiqq.data.datasource.cache.sharedpref.di

import android.content.Context
import com.github.syafiqq.data.datasource.cache.sharedpref.model.SharedPreferenceConfiguration
import dagger.Module
import dagger.Provides
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
object SharedPreferenceModule {
    @Qualifier
    @MustBeDocumented
    @Retention(AnnotationRetention.RUNTIME)
    annotation class AppProfileCache

    @Qualifier
    @MustBeDocumented
    @Retention(AnnotationRetention.RUNTIME)
    annotation class UUCache

    @Singleton
    @AppProfileCache
    @Provides
    fun provideAppProfileSharedPreferenceConfiguration(): SharedPreferenceConfiguration {
        return SharedPreferenceConfiguration("common", Context.MODE_PRIVATE)
    }

    @Singleton
    @UUCache
    @Provides
    fun provideUuSharedPreferenceConfiguration(): SharedPreferenceConfiguration {
        return SharedPreferenceConfiguration("uu", Context.MODE_PRIVATE)
    }
}
