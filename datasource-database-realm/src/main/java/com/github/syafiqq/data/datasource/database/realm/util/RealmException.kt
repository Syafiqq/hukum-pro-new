package com.github.syafiqq.data.datasource.database.realm.util.error

import com.github.syafiqq.common.error.DefinedException
import com.github.syafiqq.common.error.ErrorCode

object RealmExceptionFactory {
    fun createNoDataException(cause: Throwable? = null) = DefinedException(
        null,
        ErrorCode.ERROR_DATA_NO_DATA_AVAILABLE.code,
        cause
    )
}
