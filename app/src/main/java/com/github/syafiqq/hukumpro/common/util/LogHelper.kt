package com.github.syafiqq.hukumpro.common.util

import com.github.syafiqq.common.error.DefinedException
import com.github.syafiqq.hukumpro.common.error.ViewError
import timber.log.Timber

object LogHelper {
    fun v(message: String?, vararg args: Any?) {
        Timber.v(message, args)
    }

    /** Log a verbose exception and a message with optional format args.  */
    fun v(t: Throwable?, message: String?, vararg args: Any?) {
        Timber.v(t?.extractException(), message, args)
    }

    /** Log a verbose exception.  */
    fun v(t: Throwable?) {
        Timber.v(t?.extractException())
    }

    /** Log a debug message with optional format args.  */
    fun d(message: String?, vararg args: Any?) {
        Timber.d(message, *args)
    }

    /** Log a debug exception and a message with optional format args.  */
    fun d(t: Throwable?, message: String?, vararg args: Any?) {
        Timber.d(t?.extractException(), message, *args)
    }

    /** Log a debug exception.  */
    fun d(t: Throwable?) {
        Timber.d(t?.extractException())
    }

    /** Log an info message with optional format args.  */
    fun i(message: String?, vararg args: Any?) {
        Timber.i(message, *args)
    }

    /** Log an info exception and a message with optional format args.  */
    fun i(t: Throwable?, message: String?, vararg args: Any?) {
        Timber.i(t?.extractException(), message, *args)
    }

    /** Log an info exception.  */
    fun i(t: Throwable?) {
        Timber.i(t?.extractException())
    }

    /** Log a warning message with optional format args.  */
    fun w(message: String?, vararg args: Any?) {
        Timber.w(message, *args)
    }

    /** Log a warning exception and a message with optional format args.  */
    fun w(t: Throwable?, message: String?, vararg args: Any?) {
        Timber.w(t?.extractException(), message, *args)
    }

    /** Log a warning exception.  */
    fun w(t: Throwable?) {
        Timber.w(t?.extractException())
    }

    /** Log an error message with optional format args.  */
    fun e(message: String?, vararg args: Any?) {
        Timber.e(message, *args)
    }

    /** Log an error exception and a message with optional format args.  */
    fun e(t: Throwable?, message: String?, vararg args: Any?) {
        Timber.e(t?.extractException(), message, *args)
    }

    /** Log an error exception.  */
    fun e(t: Throwable?) {
        Timber.e(t?.extractException())
    }

    /** Log an assert message with optional format args.  */
    fun wtf(message: String?, vararg args: Any?) {
        Timber.wtf(message, *args)
    }

    /** Log an assert exception and a message with optional format args.  */
    fun wtf(t: Throwable?, message: String?, vararg args: Any?) {
        Timber.wtf(t?.extractException(), message, *args)
    }

    /** Log an assert exception.  */
    fun wtf(t: Throwable?) {
        Timber.wtf(t?.extractException())
    }
}

private fun Throwable.extractException(): Throwable {
    if (this is DefinedException && this.cause != null) {
        return this.cause!!
    } else if (this is ViewError) {
        return this.error
    }
    return this
}
