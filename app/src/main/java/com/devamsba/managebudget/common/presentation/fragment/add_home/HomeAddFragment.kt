package com.devamsba.managebudget.common.presentation.fragment.add_home

import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import androidx.annotation.RequiresApi
import androidx.fragment.app.viewModels
import com.devamsba.managebudget.BR
import com.devamsba.managebudget.R
import com.devamsba.managebudget.common.data.income.entity.IncomeEntity
import com.devamsba.managebudget.databinding.HomeAddFragmentLayoutBinding
import com.devamsba.managebudget.common.presentation.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeAddFragment(override val layoutRes: Int = R.layout.home_add_fragment_layout, )
    : BaseFragment<HomeAddFragmentLayoutBinding, HomeAddViewModel>(){
    override val viewModel: HomeAddViewModel by viewModels()

    override fun initDataBinding() {
        binding!!.setVariable(BR._all, viewModel)
    }


    @RequiresApi(Build.VERSION_CODES.M)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initDesignViews()
        initCurrencyData()
        initTypeData()

        val en = IncomeEntity(id = 1 ,title = "First", amount = 22.2, date ="12,3,1312", isNotify = false , currency = 1)
        viewModel.insertIncome(en)
//        addViewModel.fetchIncomes()
//        this.lifecycleScope.launchWhenStarted {
//            addViewModel.incomes.collect { it ->
//                Log.e("onViewCreated", "onViewCreated: ${it.toString()} ",)
//            }
//        }


    }

    private fun initTypeData(){

    }
    private fun initCurrencyData(){
        val currencyList = resources.getStringArray(R.array.list_of_currency)
        val arrayAdapter = ArrayAdapter(requireContext(), R.layout.dropdown_item, currencyList)
        binding?.currencyEditText?.setAdapter(arrayAdapter)
    }
    private fun initDesignViews(){
        hideActionBar()
        changeColorStatusBar(R.color.white)
        changeTextStatusBar()
    }

}