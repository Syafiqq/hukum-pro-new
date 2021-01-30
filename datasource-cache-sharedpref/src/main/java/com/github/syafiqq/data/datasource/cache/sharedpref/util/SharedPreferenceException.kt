package com.github.syafiqq.data.datasource.cache.sharedpref.util.error

import com.github.syafiqq.common.error.DefinedException
import com.github.syafiqq.common.error.ErrorCode

object SharedPreferenceExceptionFactory {
    fun createNoSharedPreferenceInstance(cause: Throwable? = null) = DefinedException(
        null,
        ErrorCode.ERROR_DATA_NO_DATA_AVAILABLE.code,
        cause
    )
}
