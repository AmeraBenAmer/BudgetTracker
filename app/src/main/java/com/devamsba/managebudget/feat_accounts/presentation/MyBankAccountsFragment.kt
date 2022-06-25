package com.devamsba.managebudget.feat_accounts.presentation

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.devamsba.managebudget.BR
import com.devamsba.managebudget.R
import com.devamsba.managebudget.common.infra.showMessage
import com.devamsba.managebudget.common.presentation.Listener
import com.devamsba.managebudget.common.presentation.StateView
import com.devamsba.managebudget.common.presentation.base.BaseFragment
import com.devamsba.managebudget.databinding.MyBankAccountsFragmentBinding
import com.devamsba.managebudget.feat_accounts.domain.entity.AccountsEntity
import com.devamsba.managebudget.feat_accounts.presentation.adapter.AccountsAdapter
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach


@AndroidEntryPoint
class MyBankAccountsFragment constructor(
    override val layoutRes: Int = R.layout.my_bank_accounts_fragment
) : BaseFragment<MyBankAccountsFragmentBinding, MyBankAccountViewModel>(),
    Listener<AccountsEntity> {
    override val viewModel: MyBankAccountViewModel by viewModels()
    override fun initDataBinding() {
        binding?.setVariable(BR._all, viewModel)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setOnClickListener()
        setupRecyclerView()
        observe()
        fetchAccounts()

    }

    private fun fetchAccounts() {
        viewModel.fetchAccounts()
    }

    private fun observe() {
        observeState()
        observeAccounts()
    }

    private fun setupRecyclerView() {
        val accountsAdapter = AccountsAdapter(this)
        binding?.recyclerView?.apply {
            adapter = accountsAdapter
        }
    }

    private fun showAlertDialogToConfirmActionDelete(account: AccountsEntity) {
        MaterialAlertDialogBuilder(requireContext())
            .setTitle(resources.getString(R.string.title_delete_dialog))
            .setMessage(resources.getString(R.string.supporting_text_delete_dialog))

            .setNegativeButton(resources.getString(R.string.txt_decline)) { dialog, which ->
                // Respond to negative button press
                dialog.dismiss()
            }
            .setPositiveButton(resources.getString(R.string.txt_accept)) { dialog, which ->
                deleteAccountFromDb(account)
            }
            .show()
    }

    private fun deleteAccountFromDb(account: AccountsEntity) {
        viewModel.deleteAccount(account)
    }

    private fun observeState() {
        viewModel.state.flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
            .onEach { handleState(it) }
            .launchIn(lifecycleScope)
    }

    private fun handleState(state: StateView) {
        when (state) {
            is StateView.ShowToast -> requireActivity().showMessage(state.message)
            is StateView.IsLoading -> handleLoading(state.isLoading)
            is StateView.Init -> Unit
        }
    }

    private fun observeAccounts() {
        viewModel.accounts.flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
            .onEach { handleAccounts(it) }
            .launchIn(lifecycleScope)
    }

    private fun handleAccounts(accounts: List<AccountsEntity>) {
        binding?.recyclerView?.adapter?.let { adapter ->
            if (adapter is AccountsAdapter)
                accounts?.let(adapter::submitList)
        }

    }


    private fun handleLoading(isLoading: Boolean) {
        if (isLoading) {
            binding?.loadingBar?.visibility = View.VISIBLE
        } else {
            binding?.loadingBar?.visibility = View.GONE
        }
    }

    private fun setOnClickListener() {
        binding?.addMyAccountsFabButton?.setOnClickListener {
            findNavController().navigate(
                MyBankAccountsFragmentDirections.actionMyBankAccountsFragmentToTypeMoneyDialog()
            )
        }
    }

    override fun onTap(entity: AccountsEntity) {
        Log.e("Fragment", "onTap: account = $entity")
    }

    override fun onItemClicked(account: AccountsEntity) {
        Log.e("Fragment", "onItemClicked: here $account")
        showAlertDialogToConfirmActionDelete(account)
    }

}