package com.github.syafiqq.datarealtest.di

import android.content.Context
import com.github.syafiqq.data.datasource.cache.sharedpref.di.SharedPreferenceModule
import com.github.syafiqq.data.datasource.database.realm.di.RealmModule
import com.github.syafiqq.data.datasource.remote.firebase.di.RemoteDataSourceBinds
import com.github.syafiqq.data.di.DomainRepositoryBinds
import com.github.syafiqq.datarealtest.test.BaseTest
import com.github.syafiqq.datarealtest.test.datasource.remote.firebasedb.GetOrderTest
import com.github.syafiqq.datarealtest.test.datasource.remote.firebasedb.GetVersionAsyncTest
import com.github.syafiqq.datarealtest.test.datasource.remote.firebasestorage.GetUuTest
import com.github.syafiqq.datarealtest.test.di.RemoteDataSourceBindsTest
import com.github.syafiqq.realtestutil.data.di.CacheDataSourceBinds
import com.github.syafiqq.realtestutil.data.di.DatabaseDataSourceBinds
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        RealmModule::class,
        SharedPreferenceModule::class,
        RemoteDataSourceBinds::class,
        DatabaseDataSourceBinds::class,
        CacheDataSourceBinds::class,
        DomainRepositoryBinds::class,
    ]
)
interface AppComponent {
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance applicationContext: Context): AppComponent
    }

    fun inject(appModuleBindsTest: RemoteDataSourceBindsTest)
    fun inject(appModuleBindsTest: GetVersionAsyncTest)
    fun inject(getOrderRealTest: GetOrderTest)
    fun inject(getUuTest: GetUuTest)
    fun inject(realBaseTest: BaseTest)
}
