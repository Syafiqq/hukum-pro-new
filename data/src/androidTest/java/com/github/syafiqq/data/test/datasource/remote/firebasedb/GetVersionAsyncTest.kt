package com.github.syafiqq.data.test.datasource.remote.firebasedb

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.github.syafiqq.data.datasource.remote.firebase.contract.AppProfileRemoteDataSource
import com.github.syafiqq.data.test.BaseTest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.notNullValue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import javax.inject.Inject

@RunWith(AndroidJUnit4::class)
class GetVersionAsyncTest : BaseTest() {
    @Inject
    lateinit var dataSource: AppProfileRemoteDataSource

    @Before
    override fun setUp() {
        super.setUp()
        appComponent.inject(this)
    }

    @Test
    fun fetch_version_successfully() {
        runBlocking(Dispatchers.Main) {
            val version = dataSource.fetchVersion()
            println("CurrentLog - $version")
            assertThat(version, `is`(notNullValue()))
        }
    }
}
