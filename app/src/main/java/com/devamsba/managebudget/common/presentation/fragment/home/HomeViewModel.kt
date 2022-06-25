package com.devamsba.managebudget.common.presentation.fragment.home

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.devamsba.managebudget.common.presentation.StateView
import com.devamsba.managebudget.common.presentation.base.BaseViewModel
import com.devamsba.managebudget.feat_expense.domain.usecase.ExpenseUseCase
import com.devamsba.managebudget.feat_history.domain.entity.HistoryEntity
import com.devamsba.managebudget.feat_history.domain.usecase.HistoryUseCase
import com.devamsba.managebudget.feat_incoms.domain.usecase.IncomesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(
    private val historyUseCase: HistoryUseCase,
    private val incomesUseCase: IncomesUseCase,
    private val expenseUseCase: ExpenseUseCase
) : BaseViewModel() {

    private val _state = MutableStateFlow<StateView>(StateView.Init)
    val stateHistory: StateFlow<StateView> get() = _state

    private val _incomesSummation = MutableStateFlow(0.0)
    val incomesSummation: StateFlow<Double> get() = _incomesSummation


    private val _expensesSummation = MutableStateFlow(0.0)
    val expensesSummation: StateFlow<Double> get() = _expensesSummation


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
    fun getSummationIncomes() {
        viewModelScope.launch {
            incomesUseCase.FetchIncomes.fetchTotalAmount()
                .onStart {
                    showLoading()
                    Log.e("getData ", "insertData: onStrat ")

                }.catch { error ->
                    hideLoading()
                    Log.e("getData ", "insertData: catch ")

                    showToast(error.message.toString())

                }.collect { result ->
                    Log.e("getData ", "_incomesSummation: ${result} ")

                    hideLoading()
                    _incomesSummation.value = result ?: 0.0
                }
        }
    }
    fun getSummationExpenses() {
        viewModelScope.launch {
            expenseUseCase.fetchExpense.fetchTotalAmount()
                .onStart {
                    showLoading()
                    Log.e("getData ", "insertData: onStrat ")

                }.catch { error ->
                    hideLoading()
                    Log.e("getData ", "insertData: catch ")

                    showToast(error.message.toString())

                }.collect { result ->
                    Log.e("getData ", "_expensesSummation: ${result} ")

                    hideLoading()
                    _expensesSummation.value = result ?: 0.0
                }
        }
    }

}