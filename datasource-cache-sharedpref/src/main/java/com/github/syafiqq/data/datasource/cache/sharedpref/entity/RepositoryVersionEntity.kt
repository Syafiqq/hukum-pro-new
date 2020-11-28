package com.github.syafiqq.data.datasource.cache.sharedpref.entity

import com.squareup.moshi.JsonClass
import java.util.*

@JsonClass(generateAdapter = true)
data class RepositoryVersionEntity(
    var milis: Long? = null,
    var timestamp: Date? = null,
    var filenames: List<String> = mutableListOf()
)
