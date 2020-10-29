package com.github.syafiqq.data.datasource.database.entity

import com.github.syafiqq.domain.entity.uu.UuEntity as DomainUuEntity

fun UuEntity.toDomain(): DomainUuEntity {
    return DomainUuEntity(
        seqId = seqId,
        id = id,
        year = year,
        no = no,
        description = description,
        status = status,
        reference = reference,
        category = category,
        createdAt = createdAt,
        document = null
    )
}

fun DomainUuEntity.toData(): UuEntity {
    return UuEntity(
        seqId = seqId,
        year = year,
        no = no,
        description = description,
        status = status,
        reference = reference,
        category = category,
        createdAt = createdAt,
    ).apply {
        id = this@toData.id
    }
}
