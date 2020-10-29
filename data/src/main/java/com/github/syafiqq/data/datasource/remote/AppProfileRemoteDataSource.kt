package com.github.syafiqq.data.datasource.remote

import com.github.syafiqq.domain.entity.repoversion.RepositoryVersionEntity

interface AppProfileRemoteDataSource {
    suspend fun fetchVersion(): RepositoryVersionEntity
}
