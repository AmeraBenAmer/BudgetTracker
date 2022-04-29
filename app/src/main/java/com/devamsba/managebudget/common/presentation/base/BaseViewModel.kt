package com.devamsba.managebudget.common.presentation.base

import androidx.lifecycle.ViewModel

abstract class BaseViewModel :ViewModel(){
    abstract fun showLoading()
    abstract fun hideLoading()
    abstract fun showToast(message: String)
}