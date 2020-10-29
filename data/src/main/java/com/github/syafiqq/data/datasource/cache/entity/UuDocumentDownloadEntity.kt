package com.github.syafiqq.data.datasource.cache.entity

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UuDocumentDownloadEntity(
    var id: String? = null,
    var downloadId: Int? = null,
)
