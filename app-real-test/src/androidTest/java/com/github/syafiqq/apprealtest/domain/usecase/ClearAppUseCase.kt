package com.github.syafiqq.apprealtest.domain.usecase

import android.content.Context
import com.github.syafiqq.common.contract.data.Truncatable
import com.github.syafiqq.data.datasource.cache.sharedpref.contract.AppProfileCacheDataSource
import com.github.syafiqq.data.datasource.cache.sharedpref.contract.UuCacheDataSource
import com.github.syafiqq.data.datasource.database.realm.contract.UuLocalDataSource
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

class ClearAppUseCase @Inject constructor(
    var appContext: Context,
    var baseUuLocalDataSource: UuLocalDataSource,
    var baseUuCacheDataSource: UuCacheDataSource,
    var baseAppProfileCacheDataSource: AppProfileCacheDataSource,
) {
    fun truncateSavedStorage() {
        runBlocking {
            (baseUuLocalDataSource as? Truncatable)?.truncate()
            (baseUuCacheDataSource as? Truncatable)?.truncate()
            (baseAppProfileCacheDataSource as? Truncatable)?.truncate()
        }
    }
}
