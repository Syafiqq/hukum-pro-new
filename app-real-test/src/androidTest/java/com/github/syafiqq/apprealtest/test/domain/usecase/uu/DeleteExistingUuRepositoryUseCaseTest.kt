package com.github.syafiqq.apprealtest.test.domain.usecase.uu

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.github.syafiqq.apprealtest.test.BaseTest
import com.github.syafiqq.data.datasource.database.UuLocalDataSource
import com.github.syafiqq.data.datasource.database.entity.UuDocumentEntity
import com.github.syafiqq.data.datasource.database.entity.UuEntity
import com.github.syafiqq.data.datasource.database.entity.UuYearEntity
import com.github.syafiqq.data.datasource.database.realm.util.error.NoDataErrorException
import com.github.syafiqq.domain.usecase.uu.DeleteExistingUuRepositoryUseCase
import kotlinx.coroutines.runBlocking
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import javax.inject.Inject

@RunWith(AndroidJUnit4::class)
class DeleteExistingUuRepositoryUseCaseTest : BaseTest() {
    @Inject
    lateinit var uuLocalDataSource: UuLocalDataSource

    @Inject
    lateinit var deleteExistingUuRepositoryUseCase: DeleteExistingUuRepositoryUseCase

    @Before
    override fun setUp() {
        super.setUp()
        appComponent.inject(this)
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
