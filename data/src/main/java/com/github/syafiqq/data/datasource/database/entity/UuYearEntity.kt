package com.github.syafiqq.data.datasource.database.entity

import io.realm.RealmObject
import io.realm.annotations.Index
import io.realm.annotations.PrimaryKey

open class UuYearEntity(
    @Index var category: Int? = null,
    @Index var year: Int? = null,
    var count: Int? = null,
) : RealmObject() {
    @PrimaryKey
    var id: String? = null
    override fun toString(): String {
        return "UuYearEntity(category=$category, year=$year, count=$count, id='$id')"
    }
}
