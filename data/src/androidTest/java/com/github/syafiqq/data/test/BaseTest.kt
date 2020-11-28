package com.github.syafiqq.data.test

import android.content.Context
import androidx.test.platform.app.InstrumentationRegistry
import com.github.syafiqq.data.di.AppComponent

open class BaseTest {
    protected lateinit var appContext: Context
    protected lateinit var appComponent: AppComponent

    open fun setUp() {
        appContext = InstrumentationRegistry.getInstrumentation().targetContext
        setGraph()
    }

    override fun setGraph() {
        appComponent = DaggerAppComponent.factory().create(appContext)
    }
}
