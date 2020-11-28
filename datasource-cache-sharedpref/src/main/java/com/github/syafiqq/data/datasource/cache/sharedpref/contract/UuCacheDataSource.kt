package com.github.syafiqq.data.datasource.cache.sharedpref.contract

import com.github.syafiqq.data.datasource.cache.sharedpref.entity.UuDocumentDownloadEntity
import com.github.syafiqq.data.datasource.cache.sharedpref.entity.UuOrderEntity

interface UuCacheDataSource {
    suspend fun storeUuOrder(orders: List<UuOrderEntity>)
    suspend fun fetchUuOrder(): List<UuOrderEntity>
    suspend fun storeDocumentDownloadTracker(tracker: List<UuDocumentDownloadEntity>)
    suspend fun fetchDocumentDownloadTracker(): List<UuDocumentDownloadEntity>
}
