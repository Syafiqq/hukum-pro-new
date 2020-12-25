package com.github.syafiqq.data.datasource.database.realm.util.error

import com.github.syafiqq.common.error.DefinedException

class NoDataErrorException(cause: Throwable? = null) :
    DefinedException("Data Not Found", cause)
