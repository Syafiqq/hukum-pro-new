package com.github.syafiqq.domain.util

import com.github.syafiqq.domain.entity.uu.UuEntity
import com.github.syafiqq.domain.entity.uu.UuYearEntity

class UuYearListCollector {
    private val mapper: MutableMap<Int, MutableMap<Int, UuYearEntity>> = mutableMapOf()

    fun collect(uu: List<UuEntity>) {
        uu.forEach { u ->
            val category = u.category
            val year = u.year
            if (category == null || year == null) return@forEach

            if (!mapper.containsKey(category)) {
                mapper[category] = mutableMapOf()
            }

            if (!mapper[category]!!.containsKey(year)) {
                mapper[category]!![year] =
                    UuYearEntity(category = category, year = year, count = 0)
            }

            mapper[category]!![year]!!.count += 1
        }
    }

    val getCollector: List<UuYearEntity>
        get() = mapper.values.flatMap { it.values }
}
