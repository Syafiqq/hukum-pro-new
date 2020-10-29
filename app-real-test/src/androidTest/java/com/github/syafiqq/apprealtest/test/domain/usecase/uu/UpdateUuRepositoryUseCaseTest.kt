package com.github.syafiqq.apprealtest.test.domain.usecase.uu

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.github.syafiqq.apprealtest.test.BaseTest
import com.github.syafiqq.data.datasource.cache.AppProfileCacheDataSource
import com.github.syafiqq.data.datasource.database.UuLocalDataSource
import com.github.syafiqq.data.datasource.remote.AppProfileRemoteDataSource
import com.github.syafiqq.domain.usecase.uu.UpdateUuRepositoryUseCase
import kotlinx.coroutines.runBlocking
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import javax.inject.Inject

@RunWith(AndroidJUnit4::class)
class UpdateUuRepositoryUseCaseTest : BaseTest() {
    @Inject
    lateinit var appProfileRemoteDataSource: AppProfileRemoteDataSource

    @Inject
    lateinit var appProfileCacheDataSource: AppProfileCacheDataSource

    @Inject
    lateinit var uuLocalDataSource: UuLocalDataSource

    @Inject
    lateinit var updateUuRepositoryUseCase: UpdateUuRepositoryUseCase

    @Before
    override fun setUp() {
        super.setUp()
        appComponent.inject(this)
    }

    @Test
    fun test_it_should_not_update_version() {
        runBlocking {
            val remoteVersion = appProfileRemoteDataSource.fetchVersion()

            assertThat(appProfileCacheDataSource.fetchVersion(), `is`(nullValue()))
            updateUuRepositoryUseCase.execute(remoteVersion)
            assertThat(appProfileCacheDataSource.fetchVersion(), `is`(nullValue()))
        }
    }

    @Test
    fun test_it_should_fill_document_data() {
        runBlocking {
            val remoteVersion = appProfileRemoteDataSource.fetchVersion()

            assertThat(uuLocalDataSource.fetchUuYear(1), `is`(empty()))
            updateUuRepositoryUseCase.execute(remoteVersion)
            assertThat(uuLocalDataSource.fetchUuYear(1), `is`(not(empty())))
        }
    }
}
