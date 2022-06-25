package com.devamsba.managebudget.common.presentation

interface Listener<T> {
    fun onTap(entity: T)
    fun onItemClicked(t : T)

}