package com.github.syafiqq.hukumpro.common.util

import com.github.syafiqq.common.error.ApiException
import com.github.syafiqq.common.error.DefinedException
import com.github.syafiqq.hukumpro.common.error.*
import com.github.syafiqq.hukumpro.common.provider.AndroidResourceProvider

object ViewModelErrorHandler {
    fun handleDefaultError(
        provider: AndroidResourceProvider,
        e: Exception,
        onRetry: suspend () -> Unit,
        onComplete: suspend () -> Unit
    ): Exception {
        return when (e) {
            is DefinedException -> ViewCompletableError(
                ViewModelErrorCodeMapper.mapError(
                    provider,
                    e.code
                ) ?: e.message,
                e,
                callback = onComplete
            )
            is ApiException -> ViewRetryableError(
                ViewModelErrorCodeMapper.mapError(
                    provider,
                    e.code
                ) ?: e.message,
                e
            ) {
                when (it) {
                    is Cancel -> onComplete()
                    is Retry -> onRetry()
                }
            }
            else -> e
        }
    }
}
