package com.github.syafiqq.data.datasource.remote.firebase.util.error

import com.github.syafiqq.common.error.ApiException
import com.github.syafiqq.common.error.ErrorCode

object FirebaseExceptionFactory {
    fun createFirebaseError(ofCode: Int, cause: Throwable? = null) = ApiException(
        null,
        "${ErrorCode.ERROR_API_FIREBASE_PREFIXED.code}${ofCode}",
        cause
    )

    fun createNoDataException(cause: Throwable? = null) = ApiException(
        null,
        ErrorCode.ERROR_DATA_NO_DATA_AVAILABLE.code,
        cause
    )

    fun createParseDataException(cause: Throwable? = null) = ApiException(
        null,
        ErrorCode.ERROR_DATA_PARSE_DATA_FAILURE.code,
        cause
    )
}
