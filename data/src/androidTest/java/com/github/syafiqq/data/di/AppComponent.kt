package com.github.syafiqq.data.di

import android.content.Context
import com.github.syafiqq.data.test.datasource.remote.firebasedb.GetVersionAsyncTest
import com.github.syafiqq.data.test.di.RemoteDataSourceBindsTest
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModuleBinds::class
    ]
)
interface AppComponent {
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance applicationContext: Context): AppComponent
    }

    fun inject(appModuleBindsTest: RemoteDataSourceBindsTest)
    fun inject(appModuleBindsTest: GetVersionAsyncTest)
}
