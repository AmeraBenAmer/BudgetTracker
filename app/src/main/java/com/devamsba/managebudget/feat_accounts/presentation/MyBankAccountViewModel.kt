package com.devamsba.managebudget.feat_accounts.presentation

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.devamsba.managebudget.common.presentation.StateView
import com.devamsba.managebudget.common.presentation.base.BaseViewModel
import com.devamsba.managebudget.feat_accounts.domain.entity.AccountsEntity
import com.devamsba.managebudget.feat_accounts.domain.usecase.AccountsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MyBankAccountViewModel @Inject constructor(private val accountsUseCase: AccountsUseCase)
    : BaseViewModel() {

    private val _state = MutableStateFlow<StateView>(StateView.Init)
    val  state : StateFlow<StateView> get() = _state

    private val _accounts = MutableStateFlow(mutableListOf<AccountsEntity>())
    val accounts : StateFlow<List<AccountsEntity>> get() = _accounts


    override fun showLoading() {
        _state.value = StateView.IsLoading(true)
    }

    override fun hideLoading() {
        _state.value = StateView.IsLoading(false)
    }

    override fun showToast(message: String) {
        _state.value = StateView.ShowToast(message)
    }

    fun insertAccounts(accounts: AccountsEntity){
        Log.e("insertData", "insertData: here 2 ", )

        viewModelScope.launch {
            Log.e("insertData", "insertData: here 3 ")
            accountsUseCase.insertAccounts.invoke(accounts = accounts)
        }
    }

    fun deleteAccount(account: AccountsEntity){
        viewModelScope.launch {
            accountsUseCase.deleteAccounts.invoke(account = account)
        }
    }
    fun fetchAccounts(){
        viewModelScope.launch {
            accountsUseCase.fetchAccounts.invoke()
                .onStart {
                    showLoading()
                    Log.e("getData ", "insertData: onStrat ")

                }.catch {  error ->
                    hideLoading()
                    Log.e("getData ", "insertData: catch ")

                    showToast(error.message.toString())

                }.collect { result ->
                    Log.e("getData ", "insertData: ${result} ")

                    hideLoading()
                    _accounts.value = result as MutableList<AccountsEntity>
                }
        }
    }

}