package com.github.syafiqq.apprealtest.test.domain.usecase.repoversion

import com.github.syafiqq.apprealtest.domain.usecase.ClearAppUseCase
import com.github.syafiqq.apprealtest.test.BaseTest
import com.github.syafiqq.data.datasource.cache.sharedpref.contract.AppProfileCacheDataSource
import com.github.syafiqq.data.datasource.cache.sharedpref.entity.toData
import com.github.syafiqq.data.datasource.remote.firebase.contract.AppProfileRemoteDataSource
import com.github.syafiqq.data.datasource.remote.firebase.toDomain
import com.github.syafiqq.domain.entity.repoversion.RepositoryVersionUpToDate
import com.github.syafiqq.domain.entity.repoversion.RepositoryVersionUpdateRequired
import com.github.syafiqq.domain.usecase.repoversion.CheckLocalVersionUseCase
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
class CheckLocalVersionUseCaseTest : BaseTest() {
    @Inject
    lateinit var clearAppUseCase: ClearAppUseCase

    @Inject
    lateinit var appProfileCacheDataSource: AppProfileCacheDataSource

    @Inject
    lateinit var appProfileRemoteDataSource: AppProfileRemoteDataSource

    @Inject
    lateinit var checkLocalVersionUseCase: CheckLocalVersionUseCase

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Before
    override fun setUp() {
        super.setUp()
        hiltRule.inject()
        clearAppUseCase.truncateSavedStorage()
    }

    @Test
    fun test_it_should_return_required_update() {
        runBlocking {
            val localVersion = appProfileCacheDataSource.fetchVersion()
            assertThat(localVersion, `is`(nullValue()))

            val response = checkLocalVersionUseCase.execute()
            assertThat(response, instanceOf(RepositoryVersionUpdateRequired::class.java))

            Timber.v("CurrentLog - version - $response")
        }
    }

    @Test
    fun test_it_should_return_up_to_date_even_the_remote_has_the_newer_version() {
        runBlocking {
            val localVersion = appProfileCacheDataSource.fetchVersion()
            assertThat(localVersion, `is`(nullValue()))

            val remoteVersion = appProfileRemoteDataSource.fetchVersion()
            assertThat(remoteVersion, `is`(notNullValue()))
            remoteVersion.milis = 1000000
            remoteVersion.timestamp = "10-10-2010 10:10:10"

            appProfileCacheDataSource.storeVersion(remoteVersion.toDomain().toData())
            assertThat(appProfileRemoteDataSource.fetchVersion(), `is`(notNullValue()))

            val response = checkLocalVersionUseCase.execute()
            assertThat(response, instanceOf(RepositoryVersionUpToDate::class.java))

            Timber.v("CurrentLog - version - $response")
        }
    }

    @Test
    fun test_it_should_return_up_to_date_with_same_remote_version() {
        runBlocking {
            val localVersion = appProfileCacheDataSource.fetchVersion()
            assertThat(localVersion, `is`(nullValue()))

            val remoteVersion = appProfileRemoteDataSource.fetchVersion()
            assertThat(remoteVersion, `is`(notNullValue()))

            appProfileCacheDataSource.storeVersion(remoteVersion.toDomain().toData())
            assertThat(appProfileRemoteDataSource.fetchVersion(), `is`(notNullValue()))

            val response = checkLocalVersionUseCase.execute()
            assertThat(response, instanceOf(RepositoryVersionUpToDate::class.java))

            Timber.v("CurrentLog - version - $response")
        }
    }
}
