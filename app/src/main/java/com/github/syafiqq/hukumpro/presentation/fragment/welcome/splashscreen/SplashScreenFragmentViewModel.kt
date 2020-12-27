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
import com.github.syafiqq.hukumpro.common.provider.AndroidResourceProvider
import com.github.syafiqq.hukumpro.common.util.LogHelper
import com.github.syafiqq.hukumpro.common.util.ViewModelErrorHandler

class SplashScreenFragmentViewModel @ViewModelInject constructor(
    val checkLocalVersionUseCase: CheckLocalVersionUseCase,
    val updateUuRepositoryUseCase: UpdateUuRepositoryUseCase,
    val updateUuOrderUseCase: UpdateUuOrderUseCase,
    val updateRepositoryVersionUseCase: UpdateRepositoryVersionUseCase,
    val androidResourceProvider: AndroidResourceProvider,
) : ViewModel() {
    private val _error: MutableLiveData<Exception> = MutableLiveData()
    private val _loadingMessage: MutableLiveData<String> = MutableLiveData()

    // @formatter:off
    val error: LiveData<Exception> get() { return _error }
    val loadingMessage: LiveData<String> get() { return _loadingMessage }
    // @formatter:on

    private fun actionNoUpdateNeeded() {
        _loadingMessage.postValue(androidResourceProvider.getString(R.string.version_up_to_date))
    }

    private fun actionUpdateSuccess() {
        _loadingMessage.postValue(androidResourceProvider.getString(R.string.version_update_success))
    }

    private fun actionDownloadData() {
        _loadingMessage.postValue(androidResourceProvider.getString(R.string.action_downloading))
    }

    private fun actionCheckLocalVersion() {
        _loadingMessage.postValue(androidResourceProvider.getString(R.string.version_check))
    }

    private fun actionUpdateFailed() {
        _loadingMessage.postValue(androidResourceProvider.getString(R.string.version_update_failed))
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
            LogHelper.e(e)
            actionUpdateFailed()
            _error.postValue(
                ViewModelErrorHandler.handleDefaultError(
                    e,
                    onRetry = ::initAppData,
                    onComplete = {},
                )
            )
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
