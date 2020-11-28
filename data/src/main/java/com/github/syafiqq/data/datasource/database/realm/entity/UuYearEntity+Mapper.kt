package com.github.syafiqq.data.datasource.database.realm.entity

import com.github.syafiqq.domain.entity.uu.UuYearEntity as DomainUuYearEntity

fun UuYearEntity.toDomain(): DomainUuYearEntity {
    return DomainUuYearEntity(
        category = category,
        year = year,
        count = count ?: 0
    )
}

fun DomainUuYearEntity.toData(): UuYearEntity {
    return UuYearEntity(
        category = category,
        year = year,
        count = count
    ).apply {
        id = "${this@toData.year}_${this@toData.category}"
    }
}

