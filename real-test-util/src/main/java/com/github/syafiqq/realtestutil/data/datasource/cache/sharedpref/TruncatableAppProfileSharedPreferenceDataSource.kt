package com.github.syafiqq.realtestutil.data.datasource.cache.sharedpref

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import com.github.syafiqq.common.contract.data.Truncatable
import com.github.syafiqq.data.datasource.cache.sharedpref.di.SharedPreferenceModule
import com.github.syafiqq.data.datasource.cache.sharedpref.implementation.AppProfileSharedPreferenceDataSource
import com.github.syafiqq.data.datasource.cache.sharedpref.model.SharedPreferenceConfiguration
import com.github.syafiqq.data.datasource.cache.sharedpref.model.getSharedPreference
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class TruncatableAppProfileSharedPreferenceDataSource @Inject constructor(
    context: Context,
    @SharedPreferenceModule.AppProfileCache preferences: SharedPreferenceConfiguration
) : AppProfileSharedPreferenceDataSource(context, preferences), Truncatable {
    override suspend fun truncate() {
        withContext(Dispatchers.IO) {
            preferences.getSharedPreference(context).edit(true, SharedPreferences.Editor::clear)
        }
    }
}
