package com.github.syafiqq.hukumpro.common.error

import com.github.syafiqq.common.error.ErrorCode
import com.github.syafiqq.hukumpro.R
import com.github.syafiqq.hukumpro.common.provider.AndroidResourceProvider

object ViewModelErrorCodeMapper {
    fun mapError(provider: AndroidResourceProvider, code: String): String? {
        return when (code) {
            ErrorCode.ERROR_SYSTEM_NO_SHARED_PREFERENCES_INSTANCE.code -> provider.getString(R.string.error_system_no_cache_storage)
            ErrorCode.ERROR_SYSTEM_NO_LOCAL_STORAGE_AVAILABLE.code -> provider.getString(R.string.error_system_no_local_storage)
            ErrorCode.ERROR_DATA_INVALID_REPOSITORY_VERSION.code -> provider.getString(R.string.error_data_invalid_repository)
            ErrorCode.ERROR_DATA_NO_DATA_AVAILABLE.code -> provider.getString(R.string.error_data_not_found)
            ErrorCode.ERROR_DATA_PARSE_DATA_FAILURE.code -> provider.getString(R.string.error_data_parse_data_failure)
            ErrorCode.ERROR_UNKNOWN.code -> provider.getString(R.string.error_unknown)
            else -> null
        }
    }
}
