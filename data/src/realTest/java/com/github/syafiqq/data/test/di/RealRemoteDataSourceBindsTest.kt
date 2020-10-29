package com.github.syafiqq.data.test.di

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.github.syafiqq.data.datasource.remote.AppProfileRemoteDataSource
import com.github.syafiqq.data.test.RealBaseTest
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.notNullValue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import javax.inject.Inject

@RunWith(AndroidJUnit4::class)
class RealRemoteDataSourceBindsTest : RealBaseTest() {
    @Inject
    lateinit var dataSource: AppProfileRemoteDataSource

    @Before
    override fun setUp() {
        super.setUp()
        appComponent.inject(this)
    }

    @Test
    fun repository_is_not_null() {
        assertThat(dataSource, `is`(notNullValue()))
    }
}
