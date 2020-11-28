package com.github.syafiqq.data.datasource.database.realm.entity

import io.realm.RealmObject
import io.realm.annotations.Index
import io.realm.annotations.PrimaryKey
import java.util.*

open class UuEntity(
    @Index var seqId: Int? = null,
    @Index var year: Int? = null,
    var no: String? = null,
    var description: String? = null,
    var status: String? = null,
    var reference: String? = null,
    @Index var category: Int? = null,
    var createdAt: Date? = null,
) : RealmObject() {
    @PrimaryKey
    var id: String? = null
    override fun toString(): String {
        return "UuEntity(seqId=$seqId, id=$id, year=$year, no=$no, description=$description, status=$status, reference=$reference, category=$category, createdAt=$createdAt)"
    }
}
