package com.github.syafiqq.data.datasource.database.realm.util.error

import com.github.syafiqq.common.error.DefinedException
import com.github.syafiqq.common.error.ErrorCode

object NoDataException :
    DefinedException(
        ErrorCode.ERROR_DATA_NO_DATA_AVAILABLE.name,
        ErrorCode.ERROR_DATA_NO_DATA_AVAILABLE.code,
        null
    )
