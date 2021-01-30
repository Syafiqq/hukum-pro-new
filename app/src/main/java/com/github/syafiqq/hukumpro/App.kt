package com.github.syafiqq.hukumpro

import android.app.Application
import com.github.syafiqq.hukumpro.common.util.CrashReportingTree
import dagger.hilt.android.HiltAndroidApp
import io.realm.Realm
import timber.log.Timber
import timber.log.Timber.DebugTree

@HiltAndroidApp
class App : Application() {
    override fun onCreate() {
        super.onCreate()
        initLog()
        initRealm()
    }

    private fun initRealm() {
        Realm.init(this)
    }

    private fun initLog() {
        if (BuildConfig.DEBUG) {
            Timber.plant(DebugTree())
        } else {
            Timber.plant(CrashReportingTree())
        }
    }
}
