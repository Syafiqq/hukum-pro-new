package com.github.syafiqq.hukumpro.common.error

sealed class ViewRetryableOptions
object Cancel : ViewRetryableOptions()
object Retry : ViewRetryableOptions()

class ViewRetryableError(
    override var message: String,
    override var error: Throwable,
    var callback: suspend (ViewRetryableOptions) -> Unit
) : RuntimeException(message, error), ViewError
