package com.github.syafiqq.data.datasource.remote.entity

data class RepositoryVersionEntity(
    var milis: Long? = null,
    var timestamp: String? = null,
    var detail: RepositoryVersionDetailEntity? = null
)

data class RepositoryVersionDetailEntity(
    var filenames: List<String> = mutableListOf()
)
