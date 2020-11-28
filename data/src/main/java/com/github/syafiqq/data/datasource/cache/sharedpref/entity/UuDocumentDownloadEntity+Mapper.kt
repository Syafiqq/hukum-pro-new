package com.github.syafiqq.data.datasource.cache.sharedpref.entity

import com.github.syafiqq.domain.entity.uu.UuDocumentDownloadEntity as DomainUuDocumentDownloadEntity

fun UuDocumentDownloadEntity.toDomain(): DomainUuDocumentDownloadEntity {
    return DomainUuDocumentDownloadEntity(
        id = id,
        downloadId = downloadId
    )
}

fun DomainUuDocumentDownloadEntity.toData(): UuDocumentDownloadEntity {
    return UuDocumentDownloadEntity(
        id = id,
        downloadId = downloadId
    )
}
