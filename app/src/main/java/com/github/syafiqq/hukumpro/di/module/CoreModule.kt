package com.github.syafiqq.hukumpro.di.module

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext

@InstallIn(ApplicationComponent::class)
@Module
object CoreModule {
    @Provides
    fun provideAppContext(@ApplicationContext context: Context): Context {
        return context
    }
}
