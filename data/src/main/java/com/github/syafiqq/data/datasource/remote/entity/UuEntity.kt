package com.github.syafiqq.data.datasource.remote.entity

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UuEntity(
    var _id: Int? = null,
    var id: String? = null,
    var year: Int? = null,
    var no: String? = null,
    var description: String? = null,
    var status: String? = null,
    var reference: String? = null,
    var category: Int? = null,
    var date_created: String? = null,
)
