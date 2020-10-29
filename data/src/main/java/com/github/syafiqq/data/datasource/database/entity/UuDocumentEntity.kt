package com.github.syafiqq.data.datasource.database.entity

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class UuDocumentEntity(
    var reference: String? = null,
    var document: String? = null
) : RealmObject() {
    @PrimaryKey
    var id: String? = null
    override fun toString(): String {
        return "UuDocumentEntity(reference=$reference, document=$document, id=$id)"
    }
}
