package com.devamsba.managebudget

import android.content.Context
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import com.devamsba.managebudget.common.data.userSettings.LanguageSettings
import com.devamsba.managebudget.databinding.ActivityMainBinding
import com.devamsba.managebudget.common.domain.datastore.UserSettingManager
import com.devamsba.managebudget.common.infra.utils.LocalizationUtils
import com.devamsba.managebudget.common.presentation.base.BaseActivity
import com.devamsba.managebudget.common.presentation.base.BaseViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.withContext
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity(override val layout: Int = R.layout.activity_main) :
    BaseActivity<ActivityMainBinding, BaseViewModel>() {
    private lateinit var appBarConfiguration: AppBarConfiguration

    @Inject
    lateinit var userSettingManager: UserSettingManager

    override fun initDataBinding() {
        binding.setVariable(BR._all, viewModel)
    }

    override val viewModel: BaseViewModel by viewModels()

    var languageSettings: String = "ar"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.lifecycleOwner = this

        initLanguage()
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        with(navHostFragment.navController) {
            appBarConfiguration = AppBarConfiguration(graph)
            setupActionBarWithNavController(this, appBarConfiguration)

        }

        NavigationUI.setupWithNavController(binding.bottomNavigation, navHostFragment.navController)
    }

    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(newBase)
        LocalizationUtils.applyLanguage(newBase, language = languageSettings)
    }
    fun initLanguage(){
        lifecycleScope.launchWhenStarted {
            withContext(Dispatchers.IO){
                userSettingManager.language.catch { exception ->
                     throw exception
                }.collect { data ->
                    when(data){
                        LanguageSettings.En -> languageSettings = "en"
                        LanguageSettings.AR -> languageSettings = "ar"
                    }
                }
            }
        }
    }
}