package com.github.syafiqq.data.datasource.remote.firebase.util.error

import com.github.syafiqq.common.error.DefinedThrowable

class NoDataErrorException(cause: Throwable? = null) : DefinedThrowable("Data Not Found", cause)

class ParseDataException(cause: Throwable? = null) : DefinedThrowable("Unable to parse data", cause)
