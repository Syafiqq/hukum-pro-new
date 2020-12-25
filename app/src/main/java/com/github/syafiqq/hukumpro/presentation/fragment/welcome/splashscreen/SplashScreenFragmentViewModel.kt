package com.github.syafiqq.hukumpro.presentation.fragment.welcome.splashscreen

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.github.syafiqq.domain.entity.repoversion.RepositoryVersionUpdateRequired
import com.github.syafiqq.domain.usecase.repoversion.CheckLocalVersionUseCase
import com.github.syafiqq.domain.usecase.repoversion.UpdateRepositoryVersionUseCase

class SplashScreenFragmentViewModel @ViewModelInject constructor(
    var checkLocalVersionUseCase: CheckLocalVersionUseCase,
    var updateRepositoryVersionUseCase: UpdateRepositoryVersionUseCase
) : ViewModel() {
    val _error: MutableLiveData<Exception> = MutableLiveData()
    val error: LiveData<Exception>
        get() {
            return _error
        }

    private fun actionNoUpdateNeeded() {

    }

    suspend fun checkVersion() {
        try {
            val localVersion = checkLocalVersion()
            if (localVersion == null) {
                actionNoUpdateNeeded()
                return
            }

        } catch (e: Exception) {

        }
    }

    private suspend fun checkLocalVersion(): RepositoryVersionUpdateRequired? {
        return when (val result = checkLocalVersionUseCase.execute()) {
            is RepositoryVersionUpdateRequired -> {
                result
            }
            else -> {
                null
            }
        }
    }
}
