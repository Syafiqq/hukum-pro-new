package com.github.syafiqq.data.datasource.cache.sharedpref.entity

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UuOrderEntity(
    var order: Int? = null,
    var name: String? = null,
    var id: String? = null
)
