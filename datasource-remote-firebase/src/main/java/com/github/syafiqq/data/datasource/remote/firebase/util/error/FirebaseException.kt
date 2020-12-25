package com.github.syafiqq.data.datasource.remote.firebase.util.error

import com.github.syafiqq.common.error.DefinedException

class NoDataErrorException(cause: Throwable? = null) : DefinedException("Data Not Found", cause)

class ParseDataException(cause: Throwable? = null) : DefinedException("Unable to parse data", cause)
