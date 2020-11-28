package com.github.syafiqq.datarealtest.test.di

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.github.syafiqq.data.di.DaggerDataComponent
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.notNullValue
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class DomainComponentTest {
    @Test
    fun createGraphTest() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        val appComponent = DaggerDataComponent.factory().create(appContext)
        assertThat(appContext, `is`(notNullValue()))
        assertThat(appComponent, `is`(notNullValue()))
    }
}
