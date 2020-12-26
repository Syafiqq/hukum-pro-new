package com.github.syafiqq.hukumpro.common.provider

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class AndroidResourceProviderImpl @Inject constructor(
    @ApplicationContext private val context: Context
) : AndroidResourceProvider {
    override fun getString(stringResId: Int) {
        context.getString(stringResId)
    }

    override fun getString(stringResId: Int, vararg formatArgs: Any) {
        context.getString(stringResId, formatArgs)
    }
}
