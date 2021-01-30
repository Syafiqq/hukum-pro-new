package com.github.syafiqq.common.error

enum class ErrorCode(val code: String) {
    // SYSTEM
    ERROR_SYSTEM_NO_SHARED_PREFERENCES_INSTANCE("01-001"),
    ERROR_SYSTEM_NO_LOCAL_STORAGE_AVAILABLE("01-002"),

    // DATA
    ERROR_DATA_INVALID_REPOSITORY_VERSION("02-001"),
    ERROR_DATA_NO_DATA_AVAILABLE("02-002"),
    ERROR_DATA_PARSE_DATA_FAILURE("02-003"),

    // API
    ERROR_API_FIREBASE_PREFIXED("03-FR-"),

    ERROR_UNKNOWN("00-000")
}

object ExceptionFactory {
    fun createDefinedException(code: ErrorCode, cause: Throwable? = null) =
        DefinedException(message = null, code = code.code, cause = cause)

    fun createApiException(code: ErrorCode, cause: Throwable? = null) =
        ApiException(message = null, code = code.code, cause = cause)
}
