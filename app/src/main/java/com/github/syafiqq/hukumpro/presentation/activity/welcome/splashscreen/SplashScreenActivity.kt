package com.github.syafiqq.hukumpro.presentation.activity.welcome.splashscreen

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.github.syafiqq.hukumpro.R
import com.github.syafiqq.hukumpro.presentation.fragment.welcome.splashscreen.SplashScreenFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashScreenActivity : AppCompatActivity() {
    val viewModel: SplashScreenActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome_splashscreen)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.root, SplashScreenFragment.newInstance())
                .commitNow()
        }
    }
}
