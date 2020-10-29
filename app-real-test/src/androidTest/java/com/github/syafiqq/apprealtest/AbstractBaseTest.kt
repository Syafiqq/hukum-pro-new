package com.github.syafiqq.apprealtest

import android.content.Context
import androidx.test.platform.app.InstrumentationRegistry
import com.github.syafiqq.apprealtest.di.AppComponent

abstract class AbstractBaseTest<T : AppComponent> {
    protected lateinit var appContext: Context
    protected lateinit var appComponent: T

    open fun setUp() {
        appContext = InstrumentationRegistry.getInstrumentation().targetContext
        setGraph()
    }

    abstract fun setGraph()
}
