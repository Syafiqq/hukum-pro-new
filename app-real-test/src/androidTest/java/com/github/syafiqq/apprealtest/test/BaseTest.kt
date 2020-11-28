package com.github.syafiqq.apprealtest.test

import com.github.syafiqq.apprealtest.AbstractBaseTest
import com.github.syafiqq.apprealtest.di.AppComponent
import com.github.syafiqq.apprealtest.di.DaggerAppComponent
import com.github.syafiqq.common.contract.data.Truncatable
import com.github.syafiqq.data.datasource.cache.sharedpref.contract.AppProfileCacheDataSource
import com.github.syafiqq.data.datasource.cache.sharedpref.contract.UuCacheDataSource
import com.github.syafiqq.data.datasource.database.realm.contract.UuLocalDataSource
import io.realm.Realm
import kotlinx.coroutines.runBlocking
import timber.log.Timber
import javax.inject.Inject

open class BaseTest : AbstractBaseTest<AppComponent>() {
    @Inject
    lateinit var baseUuLocalDataSource: UuLocalDataSource

    @Inject
    lateinit var baseUuCacheDataSource: UuCacheDataSource

    @Inject
    lateinit var baseAppProfileCacheDataSource: AppProfileCacheDataSource

    override fun setUp() {
        super.setUp()
        Realm.init(appContext)
        Timber.plant(Timber.DebugTree())
        appComponent.inject(this)
        truncateSavedStorage()
    }

    private fun truncateSavedStorage() {
        runBlocking {
            (baseUuLocalDataSource as? Truncatable)?.truncate()
            (baseUuCacheDataSource as? Truncatable)?.truncate()
            (baseAppProfileCacheDataSource as? Truncatable)?.truncate()
        }
    }

    override fun setGraph() {
        appComponent = DaggerAppComponent.factory().create(applicationContext = appContext)
    }
}
