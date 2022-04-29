package com.devamsba.managebudget.feat_accounts.presentation

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.devamsba.managebudget.BR
import com.devamsba.managebudget.R
import com.devamsba.managebudget.databinding.MyBankAccountsFragmentBinding
import com.devamsba.managebudget.common.presentation.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MyBankAccountsFragment constructor(
    override val layoutRes: Int = R.layout.my_bank_accounts_fragment
) : BaseFragment<MyBankAccountsFragmentBinding, MyBankAccountViewModel>() {
    override val viewModel: MyBankAccountViewModel by viewModels()
    override fun initDataBinding() {
        binding?.setVariable(BR._all, viewModel)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setOnClickListener()
    }

    private fun setOnClickListener() {
        binding?.addMyAccountsFabButton?.setOnClickListener {
            findNavController().navigate(
                MyBankAccountsFragmentDirections.actionMyBankAccountsFragmentToTypeMoneyDialog()
            )
        }
    }

}