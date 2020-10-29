package com.github.syafiqq.data.datasource.cache.sharedpref

import android.content.Context
import androidx.core.content.edit
import com.github.syafiqq.data.datasource.cache.UuCacheDataSource
import com.github.syafiqq.data.datasource.cache.entity.toData
import com.github.syafiqq.data.datasource.cache.entity.toDomain
import com.github.syafiqq.data.datasource.cache.sharedpref.di.SharedPreferenceModule
import com.github.syafiqq.data.datasource.cache.sharedpref.model.SharedPreferenceConfiguration
import com.github.syafiqq.data.datasource.cache.sharedpref.model.getSharedPreference
import com.github.syafiqq.domain.entity.uu.UuDocumentDownloadEntity
import com.github.syafiqq.domain.entity.uu.UuOrderEntity
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import com.github.syafiqq.data.datasource.cache.entity.UuDocumentDownloadEntity as DataUuDocumentDownloadEntity
import com.github.syafiqq.data.datasource.cache.entity.UuOrderEntity as DataUuOrderEntity

open class UuSharedPreferenceDataSource @Inject constructor(
    val context: Context,
    @SharedPreferenceModule.UUCache val preferences: SharedPreferenceConfiguration
) : UuCacheDataSource {
    private fun orderJsonJsonAdapter(): JsonAdapter<List<DataUuOrderEntity>> {
        val moshi = Moshi.Builder().build()
        val type = Types.newParameterizedType(
            List::class.java,
            DataUuOrderEntity::class.java
        )
        return moshi.adapter(type)
    }

    private fun documentDownloadJsonAdapter(): JsonAdapter<List<DataUuDocumentDownloadEntity>> {
        val moshi = Moshi.Builder().build()
        val type = Types.newParameterizedType(
            List::class.java,
            DataUuDocumentDownloadEntity::class.java
        )
        return moshi.adapter(type)
    }

    override suspend fun storeUuOrder(orders: List<UuOrderEntity>) {
        withContext(Dispatchers.IO) {
            val data = orders.map(UuOrderEntity::toData)
            val jsonString = orderJsonJsonAdapter().toJson(data)
            preferences.getSharedPreference(context).edit {
                putString(order, jsonString)
            }
        }
    }

    override suspend fun fetchUuOrder(): List<UuOrderEntity> {
        return withContext(Dispatchers.IO) {
            val jsonString = preferences.getSharedPreference(context).getString(order, null)
            val data = orderJsonJsonAdapter().fromJson(jsonString ?: "[]") ?: emptyList()
            data.map(DataUuOrderEntity::toDomain)
        }
    }

    override suspend fun storeDocumentDownloadTracker(tracker: List<UuDocumentDownloadEntity>) {
        withContext(Dispatchers.IO) {
            val data = tracker.map(UuDocumentDownloadEntity::toData)
            val jsonString = documentDownloadJsonAdapter().toJson(data)
            preferences.getSharedPreference(context).edit {
                putString(documentDownloadTracker, jsonString)
            }
        }
    }

    override suspend fun fetchDocumentDownloadTracker(): List<UuDocumentDownloadEntity> {
        return withContext(Dispatchers.IO) {
            val jsonString =
                preferences.getSharedPreference(context).getString(documentDownloadTracker, null)
            val data = documentDownloadJsonAdapter().fromJson(jsonString ?: "[]") ?: emptyList()
            data.map(DataUuDocumentDownloadEntity::toDomain)
        }
    }

    private companion object PATH {
        const val order = "order"
        const val documentDownloadTracker = "document_download_tracker"
    }
}
