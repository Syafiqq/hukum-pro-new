package com.github.syafiqq.apprealtest.di

import android.content.Context
import com.github.syafiqq.apprealtest.test.BaseTest
import com.github.syafiqq.apprealtest.test.domain.usecase.repoversion.CheckLocalVersionUseCaseTest
import com.github.syafiqq.apprealtest.test.domain.usecase.repoversion.CheckRemoteVersionUseCaseTest
import com.github.syafiqq.apprealtest.test.domain.usecase.repoversion.UpdateRepositoryVersionUseCaseTest
import com.github.syafiqq.apprealtest.test.domain.usecase.uu.DeleteExistingUuRepositoryUseCaseTest
import com.github.syafiqq.apprealtest.test.domain.usecase.uu.UpdateUuRepositoryUseCaseTest
import com.github.syafiqq.data.datasource.cache.sharedpref.di.SharedPreferenceModule
import com.github.syafiqq.data.datasource.database.realm.di.RealmModule
import com.github.syafiqq.data.datasource.remote.firebase.di.RemoteDataSourceBinds
import com.github.syafiqq.data.di.DomainRepositoryBinds
import com.github.syafiqq.domain.di.binds.RepositoryVersionUseCaseBinds
import com.github.syafiqq.domain.di.binds.UuRepositoryUseCaseBinds
import com.github.syafiqq.realtestutil.data.di.CacheDataSourceBinds
import com.github.syafiqq.realtestutil.data.di.DatabaseDataSourceBinds
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        //Data
        RealmModule::class,
        SharedPreferenceModule::class,
        RemoteDataSourceBinds::class,
        DatabaseDataSourceBinds::class,
        CacheDataSourceBinds::class,

        //Data Domain Inversion
        DomainRepositoryBinds::class,

        //Domain
        RepositoryVersionUseCaseBinds::class,
        UuRepositoryUseCaseBinds::class,
    ]
)
interface AppComponent {
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance applicationContext: Context): AppComponent
    }

    fun inject(to: BaseTest)
    fun inject(to: CheckLocalVersionUseCaseTest)
    fun inject(to: CheckRemoteVersionUseCaseTest)
    fun inject(to: UpdateRepositoryVersionUseCaseTest)
    fun inject(to: DeleteExistingUuRepositoryUseCaseTest)
    fun inject(to: UpdateUuRepositoryUseCaseTest)
}
