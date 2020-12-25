package com.github.syafiqq.hukumpro.presentation.fragment.welcome.splashscreen

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.github.syafiqq.domain.entity.repoversion.RepositoryVersionUpdateRequired
import com.github.syafiqq.domain.usecase.repoversion.CheckLocalVersionUseCase
import com.github.syafiqq.domain.usecase.repoversion.UpdateRepositoryVersionUseCase
import com.github.syafiqq.domain.usecase.uu.UpdateUuOrderUseCase
import com.github.syafiqq.domain.usecase.uu.UpdateUuRepositoryUseCase

class SplashScreenFragmentViewModel @ViewModelInject constructor(
    val checkLocalVersionUseCase: CheckLocalVersionUseCase,
    val updateUuRepositoryUseCase: UpdateUuRepositoryUseCase,
    val updateUuOrderUseCase: UpdateUuOrderUseCase,
    val updateRepositoryVersionUseCase: UpdateRepositoryVersionUseCase,
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
            val versionTo = checkLocalVersion()
            if (versionTo == null) {
                actionNoUpdateNeeded()
                return
            }

            updateUuRepositoryUseCase.execute(versionTo.to)
            updateUuOrderUseCase.execute()
            updateRepositoryVersionUseCase.execute(versionTo.to)
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
