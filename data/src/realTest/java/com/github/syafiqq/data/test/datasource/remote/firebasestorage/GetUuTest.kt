package com.github.syafiqq.data.test.datasource.remote.firebasestorage

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.github.syafiqq.data.datasource.remote.UuRemoteDataSource
import com.github.syafiqq.data.test.RealBaseTest
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
class GetUuTest : RealBaseTest() {
    @Inject
    lateinit var dataSource: UuRemoteDataSource
    private val filename = "1000000000000-1-1.json"

    @Before
    override fun setUp() {
        super.setUp()
        appComponent.inject(this)
    }

    @Test
    fun fetch_uu_successfully() {
        runBlocking(Dispatchers.Main) {
            val order = dataSource.fetchUu(filename)
            println("CurrentLog - $order")
            assertThat(order, `is`(notNullValue()))
        }
    }
}
