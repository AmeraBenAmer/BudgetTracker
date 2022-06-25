package com.devamsba.managebudget

import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import com.devamsba.managebudget.common.data.userSettings.LanguageSettings
import com.devamsba.managebudget.common.domain.datastore.UserSettingManager
import com.devamsba.managebudget.common.infra.utils.LocalizationUtils
import com.devamsba.managebudget.common.presentation.base.BaseActivity
import com.devamsba.managebudget.common.presentation.base.BaseViewModel
import com.devamsba.managebudget.databinding.ActivityMainBinding
import com.devamsba.managebudget.feat_incoms.presentation.IncomesAddViewModel
import com.wwdablu.soumya.lottiebottomnav.FontBuilder
import com.wwdablu.soumya.lottiebottomnav.ILottieBottomNavCallback
import com.wwdablu.soumya.lottiebottomnav.MenuItem
import com.wwdablu.soumya.lottiebottomnav.MenuItemBuilder
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

       // intiNavBottom()
    }


    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(newBase)
        LocalizationUtils.applyLanguage(newBase, language = languageSettings)
    }

    fun initLanguage() {
        lifecycleScope.launchWhenStarted {
            withContext(Dispatchers.IO) {
                userSettingManager.language.catch { exception ->
                    throw exception
                }.collect { data ->
                    when (data) {
                        LanguageSettings.En -> languageSettings = "en"
                        LanguageSettings.AR -> languageSettings = "ar"
                    }
                }
            }
        }
    }

    /*  private fun intiNavBottom(){
        //Set font item
        //Set font item
        var fontItem = FontBuilder.create("Dashboard")
            .selectedTextColor(Color.BLACK)
            .unSelectedTextColor(Color.GRAY)
            .selectedTextSize(16) //SP
            .unSelectedTextSize(12) //SP
            .setTypeface(Typeface.createFromAsset(assets, "coffeesugar.ttf"))
            .build()

        //Menu Dashboard

        //Menu Dashboard
        val item1 = MenuItemBuilder.create(
            "home.json", MenuItem.Source.Assets,
            fontItem!!, "dash"
        )
            .pausedProgress(1f)
            .loop(false)
            .build()

        //Example Spannable String (at Menu Gifts)

        //Example Spannable String (at Menu Gifts)
        val spannableString = SpannableString("Gifts")
        spannableString.setSpan(
            ForegroundColorSpan(Color.RED),
            0,
            1,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        //Menu Gifts

        //Menu Gifts
        fontItem = FontBuilder.create(fontItem!!).setTitle(spannableString).build()
        val item2 = MenuItemBuilder.createFrom(item1, fontItem)
            .selectedLottieName("gift.json")
            .unSelectedLottieName("gift.json")
            .loop(true)
            .build()

        //Menu Mail

        //Menu Mail
        fontItem = FontBuilder.create(fontItem).setTitle("Mail").build()
        val item3 = MenuItemBuilder.createFrom(item1, fontItem)
            .selectedLottieName("message_cupid.json")
            .unSelectedLottieName("message.json")
            .pausedProgress(0.75f)
            .build()

        //Menu Settings

        //Menu Settings
        fontItem = FontBuilder.create(fontItem).setTitle("Settings").build()
        val item4 = MenuItemBuilder.createFrom(item1, fontItem)
            .selectedLottieName("settings.json")
            .unSelectedLottieName("settings.json")
            .build()

        var list = arrayListOf<MenuItem>(item1, item2, item3, item4)
        binding?.bottomNav.apply {
            setCallback(this@MainActivity)
            setMenuItemList(list)
            selectedIndex = 0
        }
    }
*/
}