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
import timber.log.Timber

class SplashScreenFragmentViewModel @ViewModelInject constructor(
    val checkLocalVersionUseCase: CheckLocalVersionUseCase,
    val updateUuRepositoryUseCase: UpdateUuRepositoryUseCase,
    val updateUuOrderUseCase: UpdateUuOrderUseCase,
    val updateRepositoryVersionUseCase: UpdateRepositoryVersionUseCase,
) : ViewModel() {
    private val _error: MutableLiveData<Exception> = MutableLiveData()
    private val _loadingMessage: MutableLiveData<String> = MutableLiveData()
    val error: LiveData<Exception>
        get() {
            return _error
        }
    val loadingMessage: LiveData<String>
        get() {
            return _loadingMessage
        }

    private fun actionNoUpdateNeeded() {

    }

    private fun actionUpdateSuccess() {

    }

    private fun actionDownloadData() {

    }

    private fun actionCheckLocalVersion() {

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
