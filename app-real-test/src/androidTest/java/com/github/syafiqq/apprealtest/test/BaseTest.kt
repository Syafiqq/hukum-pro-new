package com.github.syafiqq.apprealtest.test

import androidx.test.platform.app.InstrumentationRegistry
import io.realm.Realm
import timber.log.Timber

open class BaseTest {
    open fun setUp() {
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        Realm.init(appContext)
        Timber.plant(Timber.DebugTree())
    }
}
