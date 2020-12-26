package com.github.syafiqq.hukumpro.common.error

class ViewCompletableError(
    override var message: String,
    override var error: Throwable,
    var callback: suspend () -> Unit
) : RuntimeException(message, error), ViewError
