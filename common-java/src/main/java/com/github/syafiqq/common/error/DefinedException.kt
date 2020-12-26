package com.github.syafiqq.common.error

open class DefinedException(
    override val message: String,
    override val cause: Throwable?,
) : RuntimeException(message, cause) {
    constructor(message: String?) : this(message ?: ErrorConstant.defaultErrorMessage, null)

    constructor(cause: kotlin.Throwable?) : this(
        cause?.toString() ?: ErrorConstant.defaultErrorMessage, cause
    )

    constructor() : this(ErrorConstant.defaultErrorMessage, null)
}
