package com.github.syafiqq.data.test

import com.github.syafiqq.data.base.AbstractBaseTest
import com.github.syafiqq.data.di.DaggerFakeDataComponent
import com.github.syafiqq.data.di.FakeDataComponent

open class FakeBaseTest : AbstractBaseTest<FakeDataComponent>() {
    override fun setGraph() {
        appComponent = DaggerFakeDataComponent.factory().create(appContext)
    }
}
