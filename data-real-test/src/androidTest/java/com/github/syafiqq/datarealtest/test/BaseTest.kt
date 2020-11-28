package com.github.syafiqq.datarealtest.test

import android.content.Context
import androidx.test.platform.app.InstrumentationRegistry
import com.github.syafiqq.common.contract.data.Truncatable
import com.github.syafiqq.data.datasource.cache.sharedpref.contract.AppProfileCacheDataSource
import com.github.syafiqq.data.datasource.cache.sharedpref.contract.UuCacheDataSource
import com.github.syafiqq.data.datasource.remote.firebase.contract.UuRemoteDataSource
import com.github.syafiqq.datarealtest.di.AppComponent
import io.realm.Realm
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

open class BaseTest {
    protected lateinit var appContext: Context
    protected lateinit var appComponent: AppComponent

    @Inject
    lateinit var uuRemoteDataSource: UuRemoteDataSource

    @Inject
    lateinit var uuCacheDataSource: UuCacheDataSource

    @Inject
    lateinit var uuProfileCacheDataSource: AppProfileCacheDataSource

    override fun setUp() {
        appContext = InstrumentationRegistry.getInstrumentation().targetContext
        Realm.init(appContext)
        setGraph()
        appComponent.inject(this)
        truncateSavedStorage()
    }

    private fun truncateSavedStorage() {
        runBlocking {
            (uuRemoteDataSource as? Truncatable)?.truncate()
            (uuCacheDataSource as? Truncatable)?.truncate()
            (uuProfileCacheDataSource as? Truncatable)?.truncate()
        }
    }

    fun setGraph() {
        appComponent = DaggerDataComponent.factory().create(appContext)
    }
}
