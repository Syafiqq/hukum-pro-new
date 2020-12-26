package com.github.syafiqq.hukumpro.common.provider

interface AndroidResourceProvider {
    fun getString(stringResId: Int): String
    fun getString(stringResId: Int, vararg formatArgs: Any): String
}
