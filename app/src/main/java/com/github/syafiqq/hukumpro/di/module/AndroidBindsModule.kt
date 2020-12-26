package com.github.syafiqq.hukumpro.di.module

import com.github.syafiqq.hukumpro.common.provider.AndroidResourceProvider
import com.github.syafiqq.hukumpro.common.provider.AndroidResourceProviderImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@InstallIn(ApplicationComponent::class)
@Module
abstract class AndroidBindsModule {
    @Binds
    abstract fun bindsAndroidResourceProvider(provider: AndroidResourceProviderImpl): AndroidResourceProvider
}
