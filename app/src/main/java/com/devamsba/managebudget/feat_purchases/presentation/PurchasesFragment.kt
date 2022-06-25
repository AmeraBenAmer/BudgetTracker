package com.devamsba.managebudget.feat_purchases.presentation

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.devamsba.managebudget.BR
import com.devamsba.managebudget.R
import com.devamsba.managebudget.common.presentation.base.BaseFragment
import com.devamsba.managebudget.databinding.PurchasesFragmentLayoutBinding
import com.devamsba.managebudget.feat_purchases.presentation.adapter.InsertPurchasesAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PurchasesFragment(override val layoutRes: Int = R.layout.purchases_fragment_layout) :
    BaseFragment<PurchasesFragmentLayoutBinding, PurchasesViewModel>() {
    override val viewModel: PurchasesViewModel by viewModels()

    override fun initDataBinding() {
        binding!!.setVariable(BR._all, viewModel)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setOnClickListener()
    }

    private fun setOnClickListener() {
        binding?.apply {
            addPurchasesFloatingButton.setOnClickListener {
                findNavController().navigate(PurchasesFragmentDirections.actionPurchasesFragmentToPurchasesAddFragement())
            }
        }
    }

}