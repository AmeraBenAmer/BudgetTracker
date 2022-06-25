package com.devamsba.managebudget.feat_history.presentation

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.devamsba.managebudget.common.presentation.StateView
import com.devamsba.managebudget.common.presentation.base.BaseViewModel
import com.devamsba.managebudget.feat_expense.domain.entity.TotalExpenseByCategory
import com.devamsba.managebudget.feat_expense.domain.usecase.ExpenseUseCase
import com.devamsba.managebudget.feat_history.domain.entity.HistoryEntity
import com.devamsba.managebudget.feat_history.domain.usecase.HistoryUseCase
import com.devamsba.managebudget.feat_incoms.domain.entity.TotalIncomesByCategory
import com.devamsba.managebudget.feat_incoms.domain.usecase.IncomesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class HistoryViewModel @Inject constructor(
    private val historyUseCase: HistoryUseCase,
    private val incomesUseCase: IncomesUseCase,
    private val expenseUseCase: ExpenseUseCase
) : BaseViewModel() {

    private val _state = MutableStateFlow<StateView>(StateView.Init)
    val stateHistory: StateFlow<StateView> get() = _state

    private val _incomesSummationGroupedByCategory =
        MutableStateFlow(mutableListOf<TotalIncomesByCategory>())
    val incomesSummationGroupedByCategory: StateFlow<List<TotalIncomesByCategory>> get() = _incomesSummationGroupedByCategory


    private val _expensesSummationGroupedByCategory =
        MutableStateFlow(mutableListOf<TotalExpenseByCategory>())
    val expensesSummationGroupedByCategory: StateFlow<List<TotalExpenseByCategory>> get() = _expensesSummationGroupedByCategory

    private val _history = MutableStateFlow(mutableListOf<HistoryEntity>())
    val history: StateFlow<List<HistoryEntity>> get() = _history

    override fun showLoading() {
        _state.value = StateView.IsLoading(true)
    }

    override fun hideLoading() {
        _state.value = StateView.IsLoading(false)
    }

    override fun showToast(message: String) {
        _state.value = StateView.ShowToast(message = message)
    }

    fun getSummationIncomesGroupedByCategory() {
        viewModelScope.launch {
            incomesUseCase.FetchIncomes.fetchTotalAmountGroupedBy()
                .onStart {
                    showLoading()
                    Log.e("getData ", "insertData: onStrat ")

                }.catch { error ->
                    hideLoading()
                    Log.e("getData ", "insertData: catch ")

                    showToast(error.message.toString())

                }.collect { result ->
                    Log.e("getData ", "_incomesSummationhere: ${result} ")

                    hideLoading()
                    _incomesSummationGroupedByCategory.value =
                        result as MutableList<TotalIncomesByCategory>
                }
        }
    }

    fun getSummationExpensesGroupedByCategory() {
        viewModelScope.launch {
            expenseUseCase.fetchExpense.fetchTotalAmountGroupedBy()
                .onStart {
                    showLoading()
                    Log.e("getData ", "insertData: onStart ")

                }.catch { error ->
                    hideLoading()
                    Log.e("getData ", "insertData: catch ")

                    showToast(error.message.toString())

                }.collect { result ->
                    Log.e("getData ", "_expensesSummation: ${result} ")

                    hideLoading()
                    _expensesSummationGroupedByCategory.value =
                        result as MutableList<TotalExpenseByCategory>
                }
        }
    }

    fun fetchHistory() {
        viewModelScope.launch {
            historyUseCase.fetchHistory.invoke()
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
                    _history.value = result as MutableList<HistoryEntity>
                }
        }
    }

    fun filterByMonth(month: Int, year: Int) {
        viewModelScope.launch {
            historyUseCase.fetchHistory.filterHistoryByDate(month, year)
                .onStart {
                    showLoading()
                    Log.e("getData ", "insertData: onStrat ")

                }.catch { error ->
                    hideLoading()
                    Log.e("getData ", "insertData: catch ")

                    showToast(error.message.toString())

                }.collect { result ->
                    Log.e("setFilterByDialog ", "insertData: ${result} ")

                    hideLoading()
                    _history.value = result as MutableList<HistoryEntity>
                }
        }
    }

}