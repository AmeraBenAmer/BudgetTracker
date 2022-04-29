package com.devamsba.managebudget.common.presentation.fragment.add_home

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.devamsba.managebudget.common.data.BaseEntity
import com.devamsba.managebudget.common.data.income.entity.IncomeEntity
import com.devamsba.managebudget.domain.usecase.InsertUseCase
import com.devamsba.managebudget.common.presentation.StateView
import com.devamsba.managebudget.common.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeAddViewModel @Inject constructor(private val baseUseCase: BaseUseCase)
    : BaseViewModel() {

    private val _state = MutableStateFlow<StateView>(StateView.Init)
    val state: StateFlow<StateView> get() = _state

    private val _incomes = MutableStateFlow(listOf<BaseEntity>())
    val incomes: StateFlow<List<BaseEntity>> get() = _incomes

    override fun showLoading() {
        _state.value = StateView.IsLoading(true)
    }

    override fun hideLoading() {
        _state.value = StateView.IsLoading(false)
    }

    override fun showToast(message: String) {
        _state.value = StateView.ShowToast(message = message)
    }


    fun fetchIncomes(){
        viewModelScope.launch {
            baseUseCase.invokeGet()
                .onStart {
                    showLoading()
                }
                .catch { e->
                    hideLoading()
                    showToast(e.message.toString())
                }
                .collect { result ->
                    hideLoading()
                    _incomes.value = result

                }
        }
    }

    fun insertIncome(entity: IncomeEntity){
        viewModelScope.launch {
            Log.e("insertData", "insertData: here 2 ", )

            (baseUseCase as InsertUseCase).invoke(entity = entity)
        }
    }

}