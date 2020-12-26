package com.github.syafiqq.data.datasource.cache.sharedpref.util.error

class NoSharedPreferenceInstanceException(cause: Throwable? = null) :
    RuntimeException("No Shared Preference Instance Found", cause)
