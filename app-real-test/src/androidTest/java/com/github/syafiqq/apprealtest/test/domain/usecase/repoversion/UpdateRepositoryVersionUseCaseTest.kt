package com.github.syafiqq.apprealtest.test.domain.usecase.repoversion

import com.github.syafiqq.apprealtest.test.BaseTest
import com.github.syafiqq.data.datasource.cache.sharedpref.contract.AppProfileCacheDataSource
import com.github.syafiqq.data.datasource.remote.firebase.contract.AppProfileRemoteDataSource
import com.github.syafiqq.data.datasource.remote.firebase.toDomain
import com.github.syafiqq.domain.usecase.repoversion.UpdateRepositoryVersionUseCase
import com.github.syafiqq.realtestutil.domain.usecase.ClearLocalDataUseCase
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.runBlocking
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import timber.log.Timber
import javax.inject.Inject

@HiltAndroidTest
class UpdateRepositoryVersionUseCaseTest : BaseTest() {
    @Inject
    lateinit var clearAppUseCase: ClearLocalDataUseCase

    @Inject
    lateinit var appProfileCacheDataSource: AppProfileCacheDataSource

    @Inject
    lateinit var appProfileRemoteDataSource: AppProfileRemoteDataSource

    @Inject
    lateinit var updateVersionUseCase: UpdateRepositoryVersionUseCase

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
    fun test_it_should_record_new_version() {
        runBlocking {
            assertThat(appProfileCacheDataSource.fetchVersion(), `is`(nullValue()))

            val remoteVersion = appProfileRemoteDataSource.fetchVersion()
            updateVersionUseCase.execute(remoteVersion.toDomain())
            assertThat(appProfileCacheDataSource.fetchVersion(), `is`(notNullValue()))

            Timber.v("CurrentLog - version - $remoteVersion")
        }
    }
}
