package com.github.syafiqq.realtestutil.domain.contract.repository

interface LocalStorageRepositoryInterface {
    suspend fun removeLocalDatabase()
    suspend fun removeLocalCache()
    suspend fun removeAll()
}
