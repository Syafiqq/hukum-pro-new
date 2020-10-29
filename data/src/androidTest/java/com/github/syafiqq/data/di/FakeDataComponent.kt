package com.github.syafiqq.data.di

import android.content.Context
import com.github.syafiqq.data.test.datasource.remote.firebasedb.GetVersionAsyncFakeTest
import com.github.syafiqq.data.test.di.FakeRemoteDataSourceBindsTest
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        FakeAppModuleBinds::class
    ]
)
interface FakeDataComponent : DataComponent {
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance applicationContext: Context): FakeDataComponent
    }

    fun inject(appModuleBindsTest: FakeRemoteDataSourceBindsTest)
    fun inject(appModuleBindsTest: GetVersionAsyncFakeTest)
}
