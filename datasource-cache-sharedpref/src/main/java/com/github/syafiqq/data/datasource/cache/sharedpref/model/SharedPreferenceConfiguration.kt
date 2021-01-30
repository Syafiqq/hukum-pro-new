package com.github.syafiqq.data.datasource.cache.sharedpref.model

import android.content.Context
import android.content.SharedPreferences
import com.github.syafiqq.data.datasource.cache.sharedpref.util.error.SharedPreferenceExceptionFactory

class SharedPreferenceConfiguration(
    val path: String,
    val mode: Int,
)

fun SharedPreferenceConfiguration.getSharedPreference(context: Context): SharedPreferences {
    return context.getSharedPreferences(path, mode)
        ?: throw SharedPreferenceExceptionFactory.createNoSharedPreferenceInstance()
}
