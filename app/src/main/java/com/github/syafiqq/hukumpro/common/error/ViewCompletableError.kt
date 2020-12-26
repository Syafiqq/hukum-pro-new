package com.github.syafiqq.hukumpro.common.error

class ViewCompletableError(
    override var message: String,
    override var error: Throwable,
    var callback: () -> Void
) : RuntimeException(message, error), ViewError
