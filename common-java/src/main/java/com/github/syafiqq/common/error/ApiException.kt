package com.github.syafiqq.common.error

open class ApiException(
    override val message: String?,
    override val code: String,
    override val cause: Throwable?,
) : DefinedException(message, code, cause)
