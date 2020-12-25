package com.github.syafiqq.apprealtest.test.domain.usecase.uu

import com.github.syafiqq.apprealtest.test.BaseTest
import com.github.syafiqq.data.datasource.database.realm.contract.UuLocalDataSource
import com.github.syafiqq.data.datasource.database.realm.entity.UuDocumentEntity
import com.github.syafiqq.data.datasource.database.realm.entity.UuEntity
import com.github.syafiqq.data.datasource.database.realm.entity.UuYearEntity
import com.github.syafiqq.data.datasource.database.realm.util.error.NoDataErrorException
import com.github.syafiqq.domain.usecase.uu.DeleteExistingUuRepositoryUseCase
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
class DeleteExistingUuRepositoryUseCaseTest : BaseTest() {
    @Inject
    lateinit var clearAppUseCase: ClearLocalDataUseCase

    @Inject
    lateinit var uuLocalDataSource: UuLocalDataSource

    @Inject
    lateinit var deleteExistingUuRepositoryUseCase: DeleteExistingUuRepositoryUseCase

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
    fun test_it_should_clear_all_data_completely() {
        runBlocking {
            uuLocalDataSource.removeAllUuDocument()
            uuLocalDataSource.removeAllUu()
            uuLocalDataSource.removeAllUuYear()

            val uuDocumentEntity = UuDocumentEntity().apply { id = "1" }
            val uuEntity = UuEntity().apply { id = "1" }
            val uuYearEntity = UuYearEntity(category = 1)

            assertThat(uuDocumentEntity, `is`(notNullValue()))
            assertThat(uuEntity, `is`(notNullValue()))
            assertThat(uuYearEntity, `is`(notNullValue()))

            uuLocalDataSource.storeUuDocument(listOf(uuDocumentEntity))
            uuLocalDataSource.storeUu(listOf(uuEntity))
            uuLocalDataSource.storeUuYear(listOf(uuYearEntity))

            assertThat(uuLocalDataSource.fetchUuDocument("1"), `is`(notNullValue()))
            assertThat(uuLocalDataSource.fetchUu("1"), `is`(notNullValue()))
            assertThat(uuLocalDataSource.fetchUuYear(1), `is`(not(empty())))

            deleteExistingUuRepositoryUseCase.execute()

            assertThat(uuLocalDataSource.fetchUuYear(1), `is`(empty()))
            try {
                assertThat(uuLocalDataSource.fetchUuDocument("1"), `is`(nullValue()))
            } catch (e: Throwable) {
                assertThat(e, `is`(instanceOf(NoDataErrorException::class.java)))
            }
            try {
                assertThat(uuLocalDataSource.fetchUu("1"), `is`(nullValue()))
            } catch (e: Throwable) {
                assertThat(e, `is`(instanceOf(NoDataErrorException::class.java)))
            }
        }
    }
}
