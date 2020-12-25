package com.github.syafiqq.hukumpro.presentation.fragment.welcome.splashscreen

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.github.syafiqq.domain.entity.repoversion.RepositoryVersionUpdateRequired
import com.github.syafiqq.domain.usecase.repoversion.CheckLocalVersionUseCase

class SplashScreenFragmentViewModel @ViewModelInject constructor(
    var checkLocalVersionUseCase: CheckLocalVersionUseCase
) : ViewModel() {
    val _error: MutableLiveData<Exception> = MutableLiveData()
    val error: LiveData<Exception>
        get() {
            return _error
        }

    suspend fun checkVersion() {
        try {
            when (checkLocalVersionUseCase.execute()) {
                is RepositoryVersionUpdateRequired -> {
                }
                else -> {
                }
            }
        } catch (e: Exception) {

        }
    }
}
