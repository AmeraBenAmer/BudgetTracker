package com.devamsba.managebudget.common.presentation

sealed class StateView {

    object Init: StateView()
    data class ShowToast(val message: String): StateView()
    data class IsLoading(val isLoading: Boolean): StateView()
}