package com.github.syafiqq.data.datasource.cache.sharedpref.model

import android.content.Context
import android.content.SharedPreferences
import com.github.syafiqq.data.datasource.cache.sharedpref.util.error.NoSharedPreferenceInstanceException

class SharedPreferenceConfiguration(
    val path: String,
    val mode: Int,
)

fun SharedPreferenceConfiguration.getSharedPreference(context: Context): SharedPreferences {
    return context.getSharedPreferences(path, mode) ?: throw NoSharedPreferenceInstanceException
}
