package com.github.syafiqq.apprealtest.test.domain.usecase.uu

import com.github.syafiqq.apprealtest.test.BaseTest
import com.github.syafiqq.data.datasource.cache.sharedpref.contract.AppProfileCacheDataSource
import com.github.syafiqq.data.datasource.cache.sharedpref.contract.UuCacheDataSource
import com.github.syafiqq.domain.usecase.uu.UpdateUuOrderUseCase
import com.github.syafiqq.realtestutil.domain.usecase.ClearLocalDataUseCase
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.runBlocking
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject

@HiltAndroidTest
class UpdateUuOrderUseCaseTest : BaseTest() {
    @Inject
    lateinit var clearAppUseCase: ClearLocalDataUseCase

    @Inject
    lateinit var appProfileCacheDataSource: AppProfileCacheDataSource

    @Inject
    lateinit var uuCacheDataSource: UuCacheDataSource

    @Inject
    lateinit var updateUuOrderUseCase: UpdateUuOrderUseCase

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Before
    override fun setUp() {
        super.setUp()
        hiltRule.inject()
        runBlocking {
            clearAppUseCase.execute()
        }
    }

    @Test
    fun test_it_should_not_update_version() {
        runBlocking {
            assertThat(appProfileCacheDataSource.fetchVersion(), `is`(nullValue()))
            updateUuOrderUseCase.execute()
            assertThat(appProfileCacheDataSource.fetchVersion(), `is`(nullValue()))
        }
    }

    @Test
    fun test_it_should_fill_order_data() {
        runBlocking {
            assertThat(uuCacheDataSource.fetchUuOrder(), `is`(empty()))
            updateUuOrderUseCase.execute()
            assertThat(uuCacheDataSource.fetchUuOrder(), `is`(not(empty())))
        }
    }
}
