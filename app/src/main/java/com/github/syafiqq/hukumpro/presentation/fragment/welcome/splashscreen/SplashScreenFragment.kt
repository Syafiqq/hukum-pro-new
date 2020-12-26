package com.github.syafiqq.hukumpro.presentation.fragment.welcome.splashscreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.github.syafiqq.hukumpro.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashScreenFragment : Fragment() {
    private var vwContent: View? = null
    private var tvProgress: TextView? = null
    private var pbProgress: ProgressBar? = null

    companion object {
        fun newInstance() = SplashScreenFragment()
    }

    private val viewModel: SplashScreenFragmentViewModel by viewModels()

    private val loadingMessageObserver = Observer<String> { value ->
        tvProgress?.text = value
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_welcome_splashscreen, container, false)
        initView(root = view)
        initData()
        initEvent()
        initViewModel()
        return view
    }

    override fun onStart() {
        super.onStart()
        lifecycleScope.launchWhenStarted {
            viewModel.initAppData()
        }
    }

    private fun initView(root: View?) {
        vwContent = root?.findViewById(R.id.vw_container)
        pbProgress = root?.findViewById(R.id.pb_progress)
        tvProgress = root?.findViewById(R.id.tv_progress)

        pbProgress?.run {
            isIndeterminate = true
        }
    }

    private fun initData() {
    }

    private fun initEvent() {
    }

    private fun initViewModel() {
        viewModel.loadingMessage.observe(viewLifecycleOwner, loadingMessageObserver)
    }
}
