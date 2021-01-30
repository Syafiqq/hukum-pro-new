package com.github.syafiqq.data.datasource.cache.sharedpref.util.error

import com.github.syafiqq.common.error.DefinedException
import com.github.syafiqq.common.error.ErrorCode

object NoSharedPreferenceInstanceException : DefinedException(
    ErrorCode.ERROR_SYSTEM_NO_SHARED_PREFERENCES_INSTANCE.name,
    ErrorCode.ERROR_SYSTEM_NO_SHARED_PREFERENCES_INSTANCE.code,
    null
)
