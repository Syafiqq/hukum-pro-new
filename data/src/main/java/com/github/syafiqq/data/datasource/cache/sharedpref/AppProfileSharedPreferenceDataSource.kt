package com.github.syafiqq.data.datasource.cache.sharedpref

import android.content.Context
import androidx.core.content.edit
import com.github.syafiqq.data.datasource.cache.AppProfileCacheDataSource
import com.github.syafiqq.data.datasource.cache.entity.toData
import com.github.syafiqq.data.datasource.cache.entity.toDomain
import com.github.syafiqq.data.datasource.cache.sharedpref.di.SharedPreferenceModule
import com.github.syafiqq.data.datasource.cache.sharedpref.model.SharedPreferenceConfiguration
import com.github.syafiqq.data.datasource.cache.sharedpref.model.getSharedPreference
import com.github.syafiqq.data.datasource.cache.sharedpref.util.adapter.AppProfileDateAdapter
import com.github.syafiqq.domain.entity.repoversion.RepositoryVersionEntity
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import com.github.syafiqq.data.datasource.cache.entity.RepositoryVersionEntity as DataRepositoryVersionEntity

open class AppProfileSharedPreferenceDataSource @Inject constructor(
    val context: Context,
    @SharedPreferenceModule.AppProfileCache val preferences: SharedPreferenceConfiguration
) : AppProfileCacheDataSource {
    private fun versionJsonAdapter(): JsonAdapter<DataRepositoryVersionEntity> {
        val moshi = Moshi.Builder()
            .add(AppProfileDateAdapter())
            .build()
        return moshi.adapter(DataRepositoryVersionEntity::class.java)
    }

    override suspend fun storeVersion(version: RepositoryVersionEntity) {
        withContext(Dispatchers.IO) {
            preferences.getSharedPreference(context).edit {
                putString(PATH.version, versionJsonAdapter().toJson(version.toData()))
            }
        }
    }

    override suspend fun fetchVersion(): RepositoryVersionEntity? {
        val result =
            preferences.getSharedPreference(context).getString(version, null) ?: return null
        return versionJsonAdapter().fromJson(result)?.toDomain()
    }

    private companion object PATH {
        const val version = "version"
        const val hasUpdate = "has_update"
    }
}
