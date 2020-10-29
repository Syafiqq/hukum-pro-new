package com.github.syafiqq.domain.entity.repoversion

import java.util.*

data class RepositoryVersionEntity(
    var milis: Long? = null,
    var timestamp: Date? = null,
    var filenames: List<String> = mutableListOf()
)
