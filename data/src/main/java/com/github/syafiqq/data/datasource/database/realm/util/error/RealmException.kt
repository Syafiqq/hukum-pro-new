package com.github.syafiqq.data.datasource.database.realm.util.error

sealed class RealmException(
    override val message: String?,
    override val cause: Throwable?,
) : Throwable(message, cause)

class NoDataErrorException(cause: Throwable? = null) :
    RealmException("Data Not Found", cause)
