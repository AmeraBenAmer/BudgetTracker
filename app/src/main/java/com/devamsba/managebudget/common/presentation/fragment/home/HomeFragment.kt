package com.devamsba.managebudget.common.presentation.fragment.home

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.annotation.RequiresApi
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.devamsba.managebudget.BR
import com.devamsba.managebudget.R
import com.devamsba.managebudget.common.data.income.entity.IncomeEntity
import com.devamsba.managebudget.databinding.HomeFragmentLayoutBinding
import com.devamsba.managebudget.common.presentation.base.BaseFragment
import com.devamsba.managebudget.common.presentation.fragment.add_home.HomeAddViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class HomeFragment(override val layoutRes: Int = R.layout.home_fragment_layout)
    : BaseFragment<HomeFragmentLayoutBinding, HomeAddViewModel>(){
    override val viewModel: HomeAddViewModel by viewModels()

    override fun initDataBinding() {
        binding!!.setVariable(BR._all, viewModel)
    }


    @RequiresApi(Build.VERSION_CODES.M)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initDesignViews()

        val en = IncomeEntity(id = 1 ,title = "First", amount = 22.2, date ="12,3,1312", isNotify = false , currency = 1)
        viewModel.insertIncome(en)
        viewModel.fetchIncomes()
        this.lifecycleScope.launchWhenStarted {
            viewModel.incomes.collect { it ->
                Log.e("onViewCreated", "onViewCreated: ${it.toString()} ",)
            }
        }
    }

    private fun initDesignViews(){
        hideActionBar()
        changeColorStatusBar(R.color.white)
        changeTextStatusBar()
    }

}