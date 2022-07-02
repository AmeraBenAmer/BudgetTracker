package com.devamsba.managebudget.common.presentation.base

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.devamsba.managebudget.BR
import com.devamsba.managebudget.common.presentation.Listener

abstract class BaseAdapter<T>(
    diffCallBack: DiffUtil.ItemCallback<T>, private val listener: Listener<T>
) :
    ListAdapter<T, BaseAdapter<T>.ViewHolder<T>>(diffCallBack) {

    var noData = false


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder<T> {

        Log.e("onCreateViewHolder", "onCreateViewHolder: ", )
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding =
            DataBindingUtil.inflate<ViewDataBinding>(layoutInflater, viewType, parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder<T>, position: Int) {

        return holder.bind(getItem(position), listener)
    }


    inner class ViewHolder<T>(private val binding: ViewDataBinding) :
        BaseViewHolder<T>(binding.root) {
        override fun bind(item: T, listener: Listener<T>) {
            binding.setVariable(BR.item, item)
            binding.setVariable(BR.clickOnItem, listener)
            binding.executePendingBindings()
        }
    }

}