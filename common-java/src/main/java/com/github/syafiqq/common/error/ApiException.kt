package com.github.syafiqq.common.error

open class ApiException(
    override val message: String?,
    override val cause: Throwable?,
) : DefinedException(message, cause) {
    constructor(message: String?) : this(message, null)

    constructor(cause: kotlin.Throwable?) : this(cause?.toString(), cause)

    constructor() : this(null, null)
}
