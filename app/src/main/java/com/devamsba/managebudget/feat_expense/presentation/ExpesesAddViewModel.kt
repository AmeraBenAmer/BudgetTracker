package com.devamsba.managebudget.feat_expense.presentation

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.devamsba.managebudget.common.infra.Either
import com.devamsba.managebudget.common.infra.left
import com.devamsba.managebudget.common.infra.right
import com.devamsba.managebudget.common.presentation.StateView
import com.devamsba.managebudget.common.presentation.base.BaseViewModel
import com.devamsba.managebudget.feat_accounts.domain.entity.AccountsEntity
import com.devamsba.managebudget.feat_accounts.domain.usecase.AccountsUseCase
import com.devamsba.managebudget.feat_categries.domain.entity.CategoriesEntity
import com.devamsba.managebudget.feat_categries.domain.usecase.CategoryUseCase
import com.devamsba.managebudget.feat_expense.domain.entity.ExpenseEntity
import com.devamsba.managebudget.feat_expense.domain.usecase.ExpenseUseCase
import com.devamsba.managebudget.feat_history.domain.entity.HistoryEntity
import com.devamsba.managebudget.feat_history.domain.usecase.HistoryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExpesesAddViewModel @Inject constructor(
    private val accountsUseCase: AccountsUseCase,
    private val categoryUseCase: CategoryUseCase,
    private val expenseUseCase: ExpenseUseCase,
    private val historyUseCase: HistoryUseCase
) :
    BaseViewModel() {
    //
    private val _state = MutableStateFlow<StateView>(StateView.Init)
    val stateAccounts: StateFlow<StateView> get() = _state

    private val _accounts = MutableStateFlow(mutableListOf<AccountsEntity>())
    val accounts: StateFlow<List<AccountsEntity>> get() = _accounts

    val stateCategories: StateFlow<StateView> get() = _state

    private val _categories = MutableStateFlow(mutableListOf<CategoriesEntity>())
    val categories: StateFlow<List<CategoriesEntity>> get() = _categories


    override fun showLoading() {
        _state.value = StateView.IsLoading(true)
    }

    override fun hideLoading() {
        _state.value = StateView.IsLoading(false)
    }

    override fun showToast(message: String) {
        _state.value = StateView.ShowToast(message = message)
    }


    fun fetchAccounts() {
        viewModelScope.launch {
            accountsUseCase.fetchAccounts.invoke()
                .onStart {
                    showLoading()
                    Log.e("getData ", "insertData: onStrat ")

                }.catch { error ->
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

    fun fetchCategories(type: String) {
        viewModelScope.launch {
            categoryUseCase.fetchCategory.invoke(type)
                .onStart {
                    showLoading()
                    Log.e("getData ", "insertData: onStrat ")

                }.catch { error ->
                    hideLoading()
                    Log.e("getData ", "insertData: catch ")

                    showToast(error.message.toString())

                }.collect { result ->
                    Log.e("getData ", "insertData: ${result} ")

                    hideLoading()
                    _categories.value = result as MutableList<CategoriesEntity>
                }
        }
    }

    fun insertExpense(entity: ExpenseEntity) {
        viewModelScope.launch {
            Log.e("insertData", "insertData: here 2 ")

            expenseUseCase.insertExpense.invoke(expense = entity)
        }
    }


    suspend fun insertHistory(historyEntity: HistoryEntity): Either<Unit, Exception> =

        try {
            historyUseCase.insertHistory.invoke(historyEntity).right()

        } catch (e: Exception) {
            e.left()
        }


}