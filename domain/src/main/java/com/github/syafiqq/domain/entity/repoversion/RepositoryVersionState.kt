package com.github.syafiqq.domain.entity.repoversion

sealed class RepositoryVersionState
data class RepositoryVersionUpdateRequired(val to: RepositoryVersionEntity) :
    RepositoryVersionState()

data class RepositoryVersionUpdateAvailable(val to: RepositoryVersionEntity) :
    RepositoryVersionState()

object RepositoryVersionUpToDate : RepositoryVersionState()
