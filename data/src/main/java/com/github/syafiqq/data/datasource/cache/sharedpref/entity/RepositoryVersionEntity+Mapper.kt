package com.github.syafiqq.data.datasource.cache.sharedpref.entity

import com.github.syafiqq.domain.entity.repoversion.RepositoryVersionEntity as DomainRepositoryVersionEntity

fun RepositoryVersionEntity.toDomain(): DomainRepositoryVersionEntity {
    return DomainRepositoryVersionEntity(
        milis,
        timestamp,
        filenames
    )
}

fun DomainRepositoryVersionEntity.toData(): RepositoryVersionEntity {
    return RepositoryVersionEntity(
        milis,
        timestamp,
        filenames
    )
}
