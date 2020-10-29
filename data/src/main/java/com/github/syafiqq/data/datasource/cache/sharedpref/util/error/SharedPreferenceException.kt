package com.github.syafiqq.data.datasource.cache.sharedpref.util.error

sealed class SharedPreferenceException(
    override val message: String?,
    override val cause: Throwable?,
) : Throwable(message, cause)

class NoSharedPreferenceInstanceException(cause: Throwable? = null) :
    SharedPreferenceException("No Shared Preference Instance Found", cause)
