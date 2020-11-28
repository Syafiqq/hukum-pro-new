package com.github.syafiqq.data.datasource.cache.sharedpref.entity

import com.github.syafiqq.domain.entity.uu.UuOrderEntity as DomainUuOrderEntity

fun UuOrderEntity.toDomain(): DomainUuOrderEntity {
    return DomainUuOrderEntity(
        order = order,
        name = name,
        id = id
    )
}

fun DomainUuOrderEntity.toData(): UuOrderEntity {
    return UuOrderEntity(
        order = order,
        name = name,
        id = id
    )
}
