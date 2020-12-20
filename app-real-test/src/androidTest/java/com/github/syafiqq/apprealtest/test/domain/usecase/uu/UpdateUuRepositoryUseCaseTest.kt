package com.github.syafiqq.apprealtest.test.domain.usecase.uu

import com.github.syafiqq.apprealtest.domain.usecase.ClearAppUseCase
import com.github.syafiqq.apprealtest.test.BaseTest
import com.github.syafiqq.data.datasource.cache.sharedpref.contract.AppProfileCacheDataSource
import com.github.syafiqq.data.datasource.database.realm.contract.UuLocalDataSource
import com.github.syafiqq.data.datasource.remote.firebase.contract.AppProfileRemoteDataSource
import com.github.syafiqq.data.datasource.remote.firebase.toDomain
import com.github.syafiqq.domain.usecase.uu.UpdateUuRepositoryUseCase
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
class UpdateUuRepositoryUseCaseTest : BaseTest() {
    @Inject
    lateinit var clearAppUseCase: ClearAppUseCase

    @Inject
    lateinit var appProfileRemoteDataSource: AppProfileRemoteDataSource

    @Inject
    lateinit var appProfileCacheDataSource: AppProfileCacheDataSource

    @Inject
    lateinit var uuLocalDataSource: UuLocalDataSource

    @Inject
    lateinit var updateUuRepositoryUseCase: UpdateUuRepositoryUseCase

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Before
    override fun setUp() {
        super.setUp()
        hiltRule.inject()
        clearAppUseCase.truncateSavedStorage()
    }

    @Test
    fun test_it_should_not_update_version() {
        runBlocking {
            val remoteVersion = appProfileRemoteDataSource.fetchVersion()

            assertThat(appProfileCacheDataSource.fetchVersion(), `is`(nullValue()))
            updateUuRepositoryUseCase.execute(remoteVersion.toDomain())
            assertThat(appProfileCacheDataSource.fetchVersion(), `is`(nullValue()))
        }
    }

    @Test
    fun test_it_should_fill_document_data() {
        runBlocking {
            val remoteVersion = appProfileRemoteDataSource.fetchVersion()

            assertThat(uuLocalDataSource.fetchUuYear(1), `is`(empty()))
            updateUuRepositoryUseCase.execute(remoteVersion.toDomain())
            assertThat(uuLocalDataSource.fetchUuYear(1), `is`(not(empty())))
        }
    }
}
