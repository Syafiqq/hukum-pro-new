package com.github.syafiqq.hukumpro.presentation.fragment.welcome.splashscreen

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.github.syafiqq.domain.usecase.repoversion.CheckLocalVersionUseCase

class SplashScreenFragmentViewModel @ViewModelInject constructor(
    var checkLocalVersionUseCase: CheckLocalVersionUseCase
) : ViewModel()
