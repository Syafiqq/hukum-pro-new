package com.github.syafiqq.hukumpro

import android.app.Application
import android.util.Log
import timber.log.Timber
import timber.log.Timber.DebugTree

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        initLog()
    }

    private fun initLog() {
        if (BuildConfig.DEBUG) {
            Timber.plant(DebugTree())
        } else {
            Timber.plant(CrashReportingTree())
        }
    }
}


/** A tree which logs important information for crash reporting.  */
private class CrashReportingTree : Timber.Tree() {
    override fun log(
        priority: Int,
        tag: String?,
        message: String,
        t: Throwable?
    ) {
        if (priority == Log.VERBOSE || priority == Log.DEBUG) {
            return
        }
        FakeCrashLibrary.log(tag, message)
        if (t != null) {
            if (priority == Log.ERROR) {
                FakeCrashLibrary.logError()
            } else if (priority == Log.WARN) {
                FakeCrashLibrary.logWarning()
            }
        }
    }
}

class FakeCrashLibrary private constructor() {
    companion object {
        fun log(tag: String?, message: String?) {
        }

        fun logWarning() {
        }

        fun logError() {
        }
    }

    init {
        throw AssertionError("No instances.")
    }
}
