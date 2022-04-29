package com.devamsba.managebudget.feat_purchases.presentation

import androidx.fragment.app.viewModels
import com.devamsba.managebudget.BR
import com.devamsba.managebudget.R
import com.devamsba.managebudget.databinding.PurchasesFragmentLayoutBinding
import com.devamsba.managebudget.common.presentation.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PurchasesFragment(override val layoutRes: Int = R.layout.purchases_fragment_layout)
    : BaseFragment<PurchasesFragmentLayoutBinding, PurchasesViewModel>(){
    override val viewModel: PurchasesViewModel by viewModels()

    override fun initDataBinding() {
        binding!!.setVariable(BR._all, viewModel)
    }

}