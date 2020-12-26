package com.github.syafiqq.common.error

open class ApiException(
    override val message: String,
    override val cause: Throwable?,
) : DefinedException(message, cause) {
    constructor(message: String?) : this(message ?: ErrorConstant.defaultErrorMessage, null)

    constructor(cause: kotlin.Throwable?) : this(
        cause?.toString() ?: ErrorConstant.defaultErrorMessage, cause
    )

    constructor() : this(null ?: ErrorConstant.defaultErrorMessage, null)
}
