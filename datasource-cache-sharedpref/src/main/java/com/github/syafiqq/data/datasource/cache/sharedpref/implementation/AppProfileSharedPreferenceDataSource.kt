package com.github.syafiqq.data.datasource.cache.sharedpref.implementation

import android.content.Context
import androidx.core.content.edit
import com.github.syafiqq.data.datasource.cache.sharedpref.contract.AppProfileCacheDataSource
import com.github.syafiqq.data.datasource.cache.sharedpref.di.SharedPreferenceModule
import com.github.syafiqq.data.datasource.cache.sharedpref.entity.RepositoryVersionEntity
import com.github.syafiqq.data.datasource.cache.sharedpref.model.SharedPreferenceConfiguration
import com.github.syafiqq.data.datasource.cache.sharedpref.model.getSharedPreference
import com.github.syafiqq.data.datasource.cache.sharedpref.util.adapter.AppProfileDateAdapter
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import kotlinx.coroutines.coroutineScope
import javax.inject.Inject

open class AppProfileSharedPreferenceDataSource @Inject constructor(
    val context: Context,
    @SharedPreferenceModule.AppProfileCache val preferences: SharedPreferenceConfiguration
) : AppProfileCacheDataSource {
    private fun versionJsonAdapter(): JsonAdapter<RepositoryVersionEntity> {
        val moshi = Moshi.Builder()
            .add(AppProfileDateAdapter())
            .build()
        return moshi.adapter(RepositoryVersionEntity::class.java)
    }

    override suspend fun storeVersion(version: RepositoryVersionEntity) {
        coroutineScope {
            preferences.getSharedPreference(context).edit {
                putString(PATH.version, versionJsonAdapter().toJson(version))
            }
        }
    }

    override suspend fun fetchVersion(): RepositoryVersionEntity? {
        return coroutineScope {
            val result = preferences.getSharedPreference(context).getString(version, null)
                ?: return@coroutineScope null
            versionJsonAdapter().fromJson(result)
        }
    }

    private companion object PATH {
        const val version = "version"
    }
}
