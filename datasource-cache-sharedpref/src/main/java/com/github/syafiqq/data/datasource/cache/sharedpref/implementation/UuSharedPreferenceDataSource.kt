package com.github.syafiqq.data.datasource.cache.sharedpref.implementation

import android.content.Context
import androidx.core.content.edit
import com.github.syafiqq.data.datasource.cache.sharedpref.contract.UuCacheDataSource
import com.github.syafiqq.data.datasource.cache.sharedpref.di.SharedPreferenceModule
import com.github.syafiqq.data.datasource.cache.sharedpref.entity.UuDocumentDownloadEntity
import com.github.syafiqq.data.datasource.cache.sharedpref.entity.UuOrderEntity
import com.github.syafiqq.data.datasource.cache.sharedpref.model.SharedPreferenceConfiguration
import com.github.syafiqq.data.datasource.cache.sharedpref.model.getSharedPreference
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import kotlinx.coroutines.coroutineScope
import javax.inject.Inject

open class UuSharedPreferenceDataSource @Inject constructor(
    val context: Context,
    @SharedPreferenceModule.UUCache val preferences: SharedPreferenceConfiguration
) : UuCacheDataSource {
    private fun orderJsonJsonAdapter(): JsonAdapter<List<UuOrderEntity>> {
        val moshi = Moshi.Builder().build()
        val type = Types.newParameterizedType(
            List::class.java,
            UuOrderEntity::class.java
        )
        return moshi.adapter(type)
    }

    private fun documentDownloadJsonAdapter(): JsonAdapter<List<UuDocumentDownloadEntity>> {
        val moshi = Moshi.Builder().build()
        val type = Types.newParameterizedType(
            List::class.java,
            UuDocumentDownloadEntity::class.java
        )
        return moshi.adapter(type)
    }

    override suspend fun storeUuOrder(orders: List<UuOrderEntity>) {
        coroutineScope {
            val jsonString = orderJsonJsonAdapter().toJson(orders)
            preferences.getSharedPreference(context).edit {
                putString(order, jsonString)
            }
        }
    }

    override suspend fun fetchUuOrder(): List<UuOrderEntity> {
        return coroutineScope {
            val jsonString = preferences.getSharedPreference(context).getString(order, null)
            val data = orderJsonJsonAdapter().fromJson(jsonString ?: "[]") ?: emptyList()
            data
        }
    }

    override suspend fun storeDocumentDownloadTracker(tracker: List<UuDocumentDownloadEntity>) {
        coroutineScope {
            val jsonString = documentDownloadJsonAdapter().toJson(tracker)
            preferences.getSharedPreference(context).edit {
                putString(documentDownloadTracker, jsonString)
            }
        }
    }

    override suspend fun fetchDocumentDownloadTracker(): List<UuDocumentDownloadEntity> {
        return coroutineScope {
            val jsonString =
                preferences.getSharedPreference(context).getString(documentDownloadTracker, null)
            val data = documentDownloadJsonAdapter().fromJson(jsonString ?: "[]") ?: emptyList()
            data
        }
    }

    private companion object PATH {
        const val order = "order"
        const val documentDownloadTracker = "document_download_tracker"
    }
}
