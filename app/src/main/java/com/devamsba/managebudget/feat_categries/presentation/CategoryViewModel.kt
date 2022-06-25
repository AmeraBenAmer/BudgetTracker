package com.devamsba.managebudget.feat_categries.presentation

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.devamsba.managebudget.common.presentation.StateView
import com.devamsba.managebudget.common.presentation.base.BaseViewModel
import com.devamsba.managebudget.feat_accounts.domain.entity.AccountsEntity
import com.devamsba.managebudget.feat_categries.domain.entity.CategoriesEntity
import com.devamsba.managebudget.feat_categries.domain.usecase.CategoryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class CategoryViewModel @Inject constructor(private val categoryUseCase: CategoryUseCase): BaseViewModel() {

    private val _state = MutableStateFlow<StateView>(StateView.Init)
    val  state : StateFlow<StateView> get() = _state

    private val _category = MutableStateFlow(mutableListOf<CategoriesEntity>())
    val category : StateFlow<List<CategoriesEntity>> get() = _category


    override fun showLoading() {
        _state.value = StateView.IsLoading(true)
    }

    override fun hideLoading() {
        _state.value = StateView.IsLoading(false)
    }

    override fun showToast(message: String) {
        _state.value = StateView.ShowToast(message)
    }

    fun insertCategory(category: CategoriesEntity){
        Log.e("insertData", "insertData: here 2 ", )

        viewModelScope.launch {
            Log.e("insertData", "insertData: here 3 ")
            categoryUseCase.insertCategory.invoke(category = category)
        }
    }
    fun insertAllCategory(category: ArrayList<CategoriesEntity>){
        Log.e("insertData", "insertData: here 2 ", )

        viewModelScope.launch {
            Log.e("insertData", "insertData: here 3 ")
            categoryUseCase.insertCategory.invoke(category = category)
        }
    }

    fun deletecategory(category: CategoriesEntity){
        viewModelScope.launch {
            categoryUseCase.deleteCategory.invoke(category = category)
        }
    }
    fun fetchCategory(type: String){
        viewModelScope.launch {
            categoryUseCase.fetchCategory.invoke(type)
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
                    _category.value = result as MutableList<CategoriesEntity>
                }
        }
    }

}
