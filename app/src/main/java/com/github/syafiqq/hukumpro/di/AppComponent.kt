package com.github.syafiqq.hukumpro.di

import android.content.Context
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
}
