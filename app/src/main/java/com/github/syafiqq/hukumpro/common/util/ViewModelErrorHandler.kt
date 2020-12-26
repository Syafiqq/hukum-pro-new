package com.github.syafiqq.hukumpro.common.util

import com.github.syafiqq.common.error.ApiException
import com.github.syafiqq.common.error.DefinedException
import com.github.syafiqq.hukumpro.common.error.Cancel
import com.github.syafiqq.hukumpro.common.error.Retry
import com.github.syafiqq.hukumpro.common.error.ViewCompletableError
import com.github.syafiqq.hukumpro.common.error.ViewRetryableError

object ViewModelErrorHandler {
    fun handleDefaultError(
        e: Exception,
        onRetry: suspend () -> Unit,
        onComplete: suspend () -> Unit
    ): Exception {
        return when (e) {
            is DefinedException -> ViewCompletableError(e.message, e, callback = onComplete)
            is ApiException -> ViewRetryableError(e.message, e) {
                when (it) {
                    is Cancel -> onComplete()
                    is Retry -> onRetry()
                }
            }
            else -> e
        }
    }
}
