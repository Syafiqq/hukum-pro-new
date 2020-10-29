package com.github.syafiqq.domain.entity.uu

import java.util.*

data class UuEntity(
    var seqId: Int? = null,
    var id: String? = null,
    var year: Int? = null,
    var no: String? = null,
    var description: String? = null,
    var status: String? = null,
    var reference: String? = null,
    var category: Int? = null,
    var createdAt: Date? = null,
    var document: String? = null,
)
