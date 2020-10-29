package com.github.syafiqq.data.repository.util.error

sealed class RepositoryException(
    override val message: String?,
    override val cause: Throwable?,
) : Throwable(message, cause)

class NullDataException(cause: Throwable? = null) :
    RepositoryException("Attempt to operate non null data on null variable", cause)
