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
import com.github.syafiqq.hukumpro.R
import timber.log.Timber

class SplashScreenFragmentViewModel @ViewModelInject constructor(
    val checkLocalVersionUseCase: CheckLocalVersionUseCase,
    val updateUuRepositoryUseCase: UpdateUuRepositoryUseCase,
    val updateUuOrderUseCase: UpdateUuOrderUseCase,
    val updateRepositoryVersionUseCase: UpdateRepositoryVersionUseCase,
) : ViewModel() {
    private val _error: MutableLiveData<Exception> = MutableLiveData()
    private val _loadingMessage: MutableLiveData<Int> = MutableLiveData()
    val error: LiveData<Exception>
        get() {
            return _error
        }
    val loadingMessage: LiveData<Int>
        get() {
            return _loadingMessage
        }

    private fun actionNoUpdateNeeded() {
        _loadingMessage.postValue(R.string.version_up_to_date)
    }

    private fun actionUpdateSuccess() {
        _loadingMessage.postValue(R.string.version_update_success)
    }

    private fun actionDownloadData() {
        _loadingMessage.postValue(R.string.action_downloading)
    }

    private fun actionCheckLocalVersion() {
        _loadingMessage.postValue(R.string.version_check)
    }

    private fun actionUpdateFailed() {
        _loadingMessage.postValue(R.string.version_update_failed)
    }

    suspend fun initAppData() {
        try {
            actionCheckLocalVersion()
            val versionTo = checkLocalVersion()
            if (versionTo == null) {
                actionNoUpdateNeeded()
                return
            }

            actionDownloadData()
            updateUuRepositoryUseCase.execute(versionTo.to)
            updateUuOrderUseCase.execute()
            updateRepositoryVersionUseCase.execute(versionTo.to)
            actionUpdateSuccess()
        } catch (e: Exception) {
            actionUpdateFailed()
            Timber.e(e)
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
