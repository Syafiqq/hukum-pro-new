package com.github.syafiqq.hukumpro.presentation.activity.welcome.splashscreen

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.github.syafiqq.hukumpro.R
import com.github.syafiqq.hukumpro.common.util.LogHelper
import com.github.syafiqq.hukumpro.presentation.fragment.welcome.splashscreen.SplashScreenFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashScreenActivity : AppCompatActivity() {
    private lateinit var vwContent: View

    private val viewModel: SplashScreenActivityViewModel by viewModels()

    private val hideHandler = Handler(Looper.getMainLooper())

    @SuppressLint("InlinedApi")
    private val hidePart2Runnable = Runnable {
        vwContent.systemUiVisibility =
            View.SYSTEM_UI_FLAG_LOW_PROFILE or
                    View.SYSTEM_UI_FLAG_FULLSCREEN or
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE or
                    View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY or
                    View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION or
                    View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome_splashscreen)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.vw_content, SplashScreenFragment.newInstance())
                .commitNow()
        }

        initViews()
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        LogHelper.v("CurrentLog - onPostCreate")
        super.onPostCreate(savedInstanceState)
        hide()
    }

    override fun onStart() {
        LogHelper.v("CurrentLog - onStart")
        super.onStart()
        lifecycleScope.launchWhenStarted {
        }
    }

    override fun onStop() {
        LogHelper.v("CurrentLog - onStop")
        super.onStop()
    }

    private fun initViews() {
        vwContent = findViewById(R.id.vw_content)
    }

    fun gotoMainMenu() {
        finish()
    }

    private fun hide() {
        supportActionBar?.hide()

        hideHandler.postDelayed(hidePart2Runnable, UI_ANIMATION_DELAY.toLong())
    }

    companion object {
        private const val UI_ANIMATION_DELAY = 300
    }
}
