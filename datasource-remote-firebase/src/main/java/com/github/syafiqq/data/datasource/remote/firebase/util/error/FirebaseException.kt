package com.github.syafiqq.data.datasource.remote.firebase.util.error

import com.github.syafiqq.common.ApiThrowable

sealed class FirebaseException(
    override val message: String?,
    override val cause: Throwable?,
    override val retryable: Boolean = false
) : ApiThrowable(message, cause, retryable)

class NoDataErrorException(cause: Throwable? = null) :
    FirebaseException("Data Not Found", cause, false)

class ParseDataException(cause: Throwable? = null) :
    FirebaseException("Unable to parse data", cause, false)
