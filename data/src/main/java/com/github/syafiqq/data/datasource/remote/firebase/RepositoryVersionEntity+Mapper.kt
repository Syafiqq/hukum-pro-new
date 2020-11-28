package com.github.syafiqq.data.datasource.remote.firebase

import com.github.syafiqq.data.datasource.remote.firebase.entity.RepositoryVersionEntity
import com.github.syafiqq.data.datasource.remote.firebase.util.FirebaseConstants
import timber.log.Timber
import java.text.SimpleDateFormat
import java.util.*
import com.github.syafiqq.domain.entity.repoversion.RepositoryVersionEntity as DomainRepositoryVersionEntity

fun RepositoryVersionEntity.toDomain(): DomainRepositoryVersionEntity {
    return DomainRepositoryVersionEntity(
        milis = milis,
        timestamp = toTimestampDate(),
        filenames = detail?.filenames ?: emptyList()
    )
}

private fun RepositoryVersionEntity.toTimestampDate(): Date? {
    val timestamp = this.timestamp ?: return null

    val sdf = SimpleDateFormat(FirebaseConstants.VERSION_DATE_FORMAT, Locale.getDefault())
    try {
        return sdf.parse(timestamp)
    } catch (throwable: Throwable) {
        Timber.e(throwable)
    }
    return null
}
