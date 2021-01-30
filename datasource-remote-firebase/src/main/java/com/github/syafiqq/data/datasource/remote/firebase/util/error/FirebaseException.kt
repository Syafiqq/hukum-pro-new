package com.github.syafiqq.data.datasource.remote.firebase.util.error

import com.github.syafiqq.common.error.ApiException
import com.github.syafiqq.common.error.ErrorCode

object NoDataException :
    ApiException(
        ErrorCode.ERROR_DATA_NO_DATA_AVAILABLE.name,
        ErrorCode.ERROR_DATA_NO_DATA_AVAILABLE.code,
        null
    )

class ParseDataException(cause: Throwable? = null) :
    ApiException(
        ErrorCode.ERROR_DATA_PARSE_DATA_FAILURE.name,
        ErrorCode.ERROR_DATA_PARSE_DATA_FAILURE.code,
        cause
    )
