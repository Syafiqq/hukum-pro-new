package com.github.syafiqq.data.datasource.cache.sharedpref.util.adapter

import com.squareup.moshi.*
import java.text.SimpleDateFormat
import java.util.*

class AppProfileDateAdapter : JsonAdapter<Date>() {
    @FromJson
    override fun fromJson(reader: JsonReader): Date? {
        return if (reader.peek() != JsonReader.Token.NULL) {
            formatter.parse(reader.nextString())
        } else {
            reader.nextNull<Any>()
            null
        }
    }

    @ToJson
    override fun toJson(writer: JsonWriter, value: Date?) {
        value?.let { writer.value(formatter.format(it)) }
    }

    private val formatter = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())

}
