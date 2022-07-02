package com.devamsba.managebudget

import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.devamsba.managebudget.common.presentation.base.BaseFragment
import com.devamsba.managebudget.common.presentation.base.BaseViewModel
import com.devamsba.managebudget.common.presentation.fragment.home.HomeViewModel
import com.devamsba.managebudget.databinding.FragmentSplashBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay


@AndroidEntryPoint
class SplashFragment(override val layoutRes: Int = R.layout.fragment_splash) :
    BaseFragment<FragmentSplashBinding, HomeViewModel>() {
    override val viewModel: HomeViewModel by viewModels()
    override fun initDataBinding() {
        binding?.setVariable(BR._all, viewModel)

    }

    private fun initView(){
        hideActionBar()
        changeColorStatusBar(R.color.white)
        changeTextStatusBar()
        hideBottomNavigation()
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.lifecycleOwner = this
        initView()


        lifecycleScope.launchWhenCreated {

            binding?.let {

                val animFadeIn: Animation =
                    AnimationUtils.loadAnimation(requireContext(), R.anim.fade_in)
                it.imageViewSplashScreen.startAnimation(animFadeIn)
                delay(10000)
                val animFadeOut: Animation =
                    AnimationUtils.loadAnimation(requireContext(), R.anim.fade_out)
                it.imageViewSplashScreen.startAnimation(animFadeOut)

            }

            delay(1000)
            findNavController().navigate(SplashFragmentDirections.actionSplashFragmentToHomeFragment())
        }


    }


}


