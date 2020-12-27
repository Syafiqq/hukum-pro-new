package com.github.syafiqq.common.error

open class DefinedException(
    override val message: String,
    val code: String,
    override val cause: Throwable?,
) : RuntimeException(message, cause)
