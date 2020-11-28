package com.github.syafiqq.data.datasource.remote.firebase

import com.github.syafiqq.data.datasource.remote.firebase.entity.UuOrderEntity
import com.github.syafiqq.domain.entity.uu.UuOrderEntity as DomainUuOrderEntity

fun UuOrderEntity.toDomain(): DomainUuOrderEntity {
    return DomainUuOrderEntity(
        order = order,
        name = name,
        id = id
    )
}
