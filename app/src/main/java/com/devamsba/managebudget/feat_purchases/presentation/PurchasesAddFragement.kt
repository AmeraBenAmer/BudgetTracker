package com.devamsba.managebudget.feat_purchases.presentation

import com.devamsba.managebudget.R
import com.devamsba.managebudget.databinding.PurchasesAddFragmentLayoutBinding
import com.devamsba.managebudget.common.presentation.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PurchasesAddFragement(override val layoutRes: Int = R.layout.purchases_add_fragment_layout)
    : BaseFragment<PurchasesAddFragmentLayoutBinding, PurchasesViewModel>(){
    override val viewModel: PurchasesViewModel
        get() = TODO("Not yet implemented")

    override fun initDataBinding() {
        TODO("Not yet implemented")
    }

}