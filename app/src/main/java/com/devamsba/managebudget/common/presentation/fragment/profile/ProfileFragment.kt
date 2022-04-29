package com.devamsba.managebudget.common.presentation.fragment.profile

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.devamsba.managebudget.BR
import com.devamsba.managebudget.R
import com.devamsba.managebudget.common.data.userSettings.ThemeSettings
import com.devamsba.managebudget.databinding.ProfileFragmentLayoutBinding
import com.devamsba.managebudget.common.domain.datastore.UserSettingManager
import com.devamsba.managebudget.common.presentation.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

@AndroidEntryPoint
class ProfileFragment(override val layoutRes: Int = R.layout.profile_fragment_layout) :
    BaseFragment<ProfileFragmentLayoutBinding, ProfileViewModel>() {
    override val viewModel: ProfileViewModel by viewModels()

    @Inject
    lateinit var userSettingManager: UserSettingManager

    private var theme: ThemeSettings = ThemeSettings.Auto
    lateinit var arrayAdapter: ArrayAdapter<String>

    override fun initDataBinding() {
        binding?.setVariable(BR._all, viewModel)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initDesignViews()

        listenToTheme()
        changeThemeOfApp()

        listenToNotification()
        changeNotificationOnOffService()


       // initLanguageData()





        setOnClickListener()
    }

    private fun setOnClickListener() {
        binding!!.apply {
            languageLayout.setOnClickListener {
                findNavController().navigate(
                    ProfileFragmentDirections.actionProfileFragmentToLanguageDialog())
            }
            myAccountsLayout.setOnClickListener {
                findNavController().navigate(
                    ProfileFragmentDirections.actionProfileFragmentToMyBankAccountsFragment())
            }
        }
    }



    private fun changeNotificationOnOffService() {
        binding?.switchedNotificationOnOff?.setOnCheckedChangeListener { _, isChecked ->
            lifecycleScope.launchWhenStarted {
                userSettingManager.setNotificationSetting(isChecked)
            }
        }
    }

    private fun listenToNotification() {
        lifecycleScope.launchWhenStarted {
            userSettingManager.notification.collect {
                binding?.switchedNotificationOnOff?.isChecked = it
                //   TODO("show toast to detect change")
            }
        }
    }

    private fun changeThemeOfApp() {
        binding?.switchedDarkModeOnOff?.setOnCheckedChangeListener { _, isChecked ->
            theme = if (isChecked)
                ThemeSettings.Dark
            else
                ThemeSettings.Light
            lifecycleScope.launchWhenStarted {
                userSettingManager.setThemeMode(theme)
            }
        }
    }

    private fun listenToTheme() {
        lifecycleScope.launchWhenStarted {
            userSettingManager.themeMode.collect {
                setThemeMode(it)
                when (it) {
                    ThemeSettings.Light -> binding?.switchedDarkModeOnOff?.isChecked = false
                    ThemeSettings.Dark -> binding?.switchedDarkModeOnOff?.isChecked = true
                }
            }
        }
    }

    private fun setThemeMode(theme: ThemeSettings) {
        val mode = when (theme) {
            ThemeSettings.Light -> AppCompatDelegate.MODE_NIGHT_NO
            ThemeSettings.Dark -> AppCompatDelegate.MODE_NIGHT_YES
            else -> AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM
        }

        AppCompatDelegate.setDefaultNightMode(mode)
    }

    private fun initLanguageData() {
        val languageList = resources.getStringArray(R.array.list_of_language)
        arrayAdapter = ArrayAdapter(requireContext(), R.layout.dropdown_item, languageList)
        binding?.textViewLanguage?.setAdapter(arrayAdapter)
    }

    private fun initDesignViews() {
        hideActionBar()
        changeColorStatusBar(R.color.white)
        changeTextStatusBar()
    }
}