package com.github.syafiqq.hukumpro.common.provider

interface AndroidResourceProvider {
    fun getString(stringResId: Int)
    fun getString(stringResId: Int, vararg formatArgs: Any)
}
