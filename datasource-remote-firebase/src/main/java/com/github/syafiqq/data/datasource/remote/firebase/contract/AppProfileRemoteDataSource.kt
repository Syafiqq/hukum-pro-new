package com.github.syafiqq.data.datasource.remote.firebase.contract

import com.github.syafiqq.data.datasource.remote.firebase.entity.RepositoryVersionEntity

interface AppProfileRemoteDataSource {
    suspend fun fetchVersion(): RepositoryVersionEntity
}
