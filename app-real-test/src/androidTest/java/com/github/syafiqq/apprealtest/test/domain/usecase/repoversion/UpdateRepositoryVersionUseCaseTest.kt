package com.github.syafiqq.apprealtest.test.domain.usecase.repoversion

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.github.syafiqq.apprealtest.test.BaseTest
import com.github.syafiqq.data.datasource.cache.AppProfileCacheDataSource
import com.github.syafiqq.data.datasource.remote.AppProfileRemoteDataSource
import com.github.syafiqq.domain.usecase.repoversion.UpdateRepositoryVersionUseCase
import kotlinx.coroutines.runBlocking
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import timber.log.Timber
import javax.inject.Inject

@RunWith(AndroidJUnit4::class)
class UpdateRepositoryVersionUseCaseTest : BaseTest() {
    @Inject
    lateinit var appProfileCacheDataSource: AppProfileCacheDataSource

    @Inject
    lateinit var appProfileRemoteDataSource: AppProfileRemoteDataSource

    @Inject
    lateinit var updateVersionUseCase: UpdateRepositoryVersionUseCase

    @Before
    override fun setUp() {
        super.setUp()
        appComponent.inject(this)
    }

    @Test
    fun test_it_should_record_new_version() {
        runBlocking {
            assertThat(appProfileCacheDataSource.fetchVersion(), `is`(nullValue()))

            val remoteVersion = appProfileRemoteDataSource.fetchVersion()
            updateVersionUseCase.execute(remoteVersion)
            assertThat(appProfileCacheDataSource.fetchVersion(), `is`(notNullValue()))

            Timber.v("CurrentLog - version - $remoteVersion")
        }
    }
}
