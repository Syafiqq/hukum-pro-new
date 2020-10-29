package com.github.syafiqq.data.base

import android.content.Context
import androidx.test.platform.app.InstrumentationRegistry
import com.github.syafiqq.data.di.DataComponent
import com.google.firebase.FirebaseApp

abstract class AbstractBaseTest<T : DataComponent> {
    protected lateinit var appContext: Context
    protected lateinit var appComponent: T

    open fun setUp() {
        appContext = InstrumentationRegistry.getInstrumentation().targetContext
        FirebaseApp.initializeApp(appContext)
        setGraph()
    }

    abstract fun setGraph()
}
