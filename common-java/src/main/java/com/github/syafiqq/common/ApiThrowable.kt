package com.github.syafiqq.common

open class ApiThrowable(
    override val message: String?,
    override val cause: Throwable?,
    open val retryAble: Boolean = false
) : Throwable(message, cause) {
    constructor(message: String?) : this(message, null)

    constructor(cause: kotlin.Throwable?) : this(cause?.toString(), cause)

    constructor() : this(null, null)
}
