package com.devamsba.managebudget.common.presentation.dialog

import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.core.app.ActivityCompat.recreate
import androidx.lifecycle.lifecycleScope
import com.devamsba.managebudget.R
import com.devamsba.managebudget.common.data.userSettings.LanguageSettings
import com.devamsba.managebudget.databinding.LanguageDialogBinding
import com.devamsba.managebudget.common.domain.datastore.UserSettingManager
import com.devamsba.managebudget.common.infra.utils.LocalizationUtils
import com.devamsba.managebudget.common.presentation.base.BaseDailog
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

@AndroidEntryPoint
class LanguageDialog constructor(override val layout: Int = R.layout.language_dialog) :
    BaseDailog<LanguageDialogBinding>() {


    @Inject
    lateinit var userSettingManager: UserSettingManager

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dialog?.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)

        dialog?.window?.setGravity(Gravity.BOTTOM)
        dialog?.window?.setBackgroundDrawableResource(android.R.color.transparent)

        listenToLanguage()
        changeLanguageSetting()

    }

    //languageLayout
    private fun changeLanguageSetting() {
        viewDataBinding?.radioBtnArabic?.setOnClickListener {
            onRadioButtonClicked(it)
        }
        viewDataBinding?.radioBtnEnglish?.setOnClickListener {
            onRadioButtonClicked(it)
        }
    }

    private fun setAppLanguage(languageSettings: LanguageSettings) {
        lifecycleScope.launchWhenStarted {
            userSettingManager.setLanguageSetting(languageSettings)
        }
        setLocalLanguage(languageSettings)

    }

    private fun listenToLanguage() {
        lifecycleScope.launchWhenStarted {
            userSettingManager.language.collect { languageSettings ->
                setLocalLanguage(languageSettings)

                when (languageSettings) {
                    LanguageSettings.AR -> {
                        viewDataBinding?.radioBtnArabic!!.isChecked = true
                    }
                    else -> {
                        viewDataBinding?.radioBtnEnglish!!.isChecked = true

                    }

                }
            }
        }
    }

    private fun setLocalLanguage(languageSettings: LanguageSettings) {
        when (languageSettings) {
            LanguageSettings.AR -> {
                changeLanguageConfigration("ar")
            }
            else -> {
                changeLanguageConfigration("en")
            }
        }
    }

    private fun changeLanguageConfigration(language: String) {

        LocalizationUtils.applyLanguage(requireContext(), language)
    }


    private fun onRadioButtonClicked(view: View) {

        var checked = (view as RadioButton).isChecked

        var languagePref = "en"

        when (view) {

            viewDataBinding?.radioBtnEnglish -> {
                if (checked) {
                    languagePref = "en"
                    setAppLanguage(LanguageSettings.En)
                }
            }

            viewDataBinding?.radioBtnArabic -> {
                if (checked) {
                    languagePref = "ar"
                    setAppLanguage(LanguageSettings.AR)
                }
            }
        }

        if (languagePref.isNotEmpty()) {
            //changeLanguageConfigration(languagePref)
            recreate(requireActivity())
        }
    }


}
