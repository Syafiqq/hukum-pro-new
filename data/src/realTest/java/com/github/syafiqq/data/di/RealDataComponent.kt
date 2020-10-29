package com.github.syafiqq.data.di

import android.content.Context
import com.github.syafiqq.data.datasource.cache.sharedpref.di.SharedPreferenceModule
import com.github.syafiqq.data.datasource.database.realm.di.RealmModule
import com.github.syafiqq.data.test.RealBaseTest
import com.github.syafiqq.data.test.datasource.remote.firebasedb.GetOrderRealTest
import com.github.syafiqq.data.test.datasource.remote.firebasedb.GetVersionAsyncRealTest
import com.github.syafiqq.data.test.datasource.remote.firebasestorage.GetUuTest
import com.github.syafiqq.data.test.di.RealRemoteDataSourceBindsTest
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        RealmModule::class,
        SharedPreferenceModule::class,
        RemoteDataSourceBinds::class,
        RealDatabaseDataSourceBinds::class,
        RealCacheDataSourceBinds::class,
        DomainRepositoryBinds::class,
    ]
)
interface RealDataComponent : DataComponent {
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance applicationContext: Context): RealDataComponent
    }

    fun inject(appModuleBindsTest: RealRemoteDataSourceBindsTest)
    fun inject(appModuleBindsTest: GetVersionAsyncRealTest)
    fun inject(getOrderRealTest: GetOrderRealTest)
    fun inject(getUuTest: GetUuTest)
    fun inject(realBaseTest: RealBaseTest)
}
