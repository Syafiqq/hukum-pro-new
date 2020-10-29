package com.github.syafiqq.data.test

import com.github.syafiqq.common.contract.data.Truncatable
import com.github.syafiqq.data.base.AbstractBaseTest
import com.github.syafiqq.data.datasource.cache.AppProfileCacheDataSource
import com.github.syafiqq.data.datasource.cache.UuCacheDataSource
import com.github.syafiqq.data.datasource.remote.UuRemoteDataSource
import com.github.syafiqq.data.di.DaggerRealDataComponent
import com.github.syafiqq.data.di.RealDataComponent
import io.realm.Realm
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

open class RealBaseTest : AbstractBaseTest<RealDataComponent>() {
    @Inject
    lateinit var uuRemoteDataSource: UuRemoteDataSource

    @Inject
    lateinit var uuCacheDataSource: UuCacheDataSource

    @Inject
    lateinit var uuProfileCacheDataSource: AppProfileCacheDataSource

    override fun setUp() {
        super.setUp()
        Realm.init(appContext)
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

    override fun setGraph() {
        appComponent = DaggerRealDataComponent.factory().create(appContext)
    }
}
