package com.github.syafiqq.data.datasource.cache.sharedpref.util.error

import com.github.syafiqq.common.error.DefinedException

class NoSharedPreferenceInstanceException(cause: Throwable? = null) :
    DefinedException("No Shared Preference Instance Found", cause)
