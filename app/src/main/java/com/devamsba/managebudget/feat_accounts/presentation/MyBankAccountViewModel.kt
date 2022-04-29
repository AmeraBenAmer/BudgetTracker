package com.devamsba.managebudget.feat_accounts.presentation

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.devamsba.managebudget.common.data.BaseEntity
import com.devamsba.managebudget.common.data.typeMoney.entity.TypeMoneyEntity
import com.devamsba.managebudget.domain.usecase.InsertUseCase
import com.devamsba.managebudget.common.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MyBankAccountViewModel @Inject constructor(private val baseUseCase: BaseUseCase)
    : BaseViewModel() {
    override fun showLoading() {
        TODO("Not yet implemented")
    }

    override fun hideLoading() {
        TODO("Not yet implemented")
    }

    override fun showToast(message: String) {
        TODO("Not yet implemented")
    }

    fun insertTypeMoney(entity: TypeMoneyEntity){
        Log.e("insertData", "insertData: here 2 ", )

        viewModelScope.launch {
            Log.e("insertData", "insertData: here 3 ", )
            (baseUseCase as InsertUseCase).invoke(entity = entity)
        }
    }
    fun insertAllTypeMoney(entity: ArrayList<TypeMoneyEntity>){
        viewModelScope.launch {
            baseUseCase.invokeAll(entity as ArrayList<BaseEntity>)
        }
    }
}