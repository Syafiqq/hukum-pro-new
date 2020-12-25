package com.github.syafiqq.hukumpro.presentation.fragment.welcome.splashscreen

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.github.syafiqq.domain.entity.repoversion.RepositoryVersionUpdateAvailable
import com.github.syafiqq.domain.entity.repoversion.RepositoryVersionUpdateRequired
import com.github.syafiqq.domain.usecase.repoversion.CheckLocalVersionUseCase

class SplashScreenFragmentViewModel @ViewModelInject constructor(
    var checkLocalVersionUseCase: CheckLocalVersionUseCase
) : ViewModel() {
    suspend fun checkVersion() {
        try {
            val versionState = checkLocalVersionUseCase.execute()
            when (versionState) {
                is RepositoryVersionUpdateRequired -> {
                }
                is RepositoryVersionUpdateAvailable -> {
                }
                else -> {
                }
            }
        } catch (e: Throwable) {

        }
    }
}
