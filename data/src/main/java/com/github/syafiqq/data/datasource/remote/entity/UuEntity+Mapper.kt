package com.github.syafiqq.data.datasource.remote.entity

import timber.log.Timber
import java.text.SimpleDateFormat
import java.util.*
import com.github.syafiqq.domain.entity.uu.UuEntity as DomainUuEntity

fun UuEntity.toDomain(): DomainUuEntity {
    return DomainUuEntity(
        seqId = _id,
        id = id,
        year = year,
        no = no,
        description = description,
        status = status,
        reference = reference,
        category = category,
        createdAt = toCreatedAt(),
        document = null
    )
}

private fun UuEntity.toCreatedAt(): Date? {
    val timestamp = this.date_created ?: return null

    val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())
    try {
        return sdf.parse(timestamp)
    } catch (throwable: Throwable) {
        Timber.e(throwable)
    }
    return null
}
