package com.devamsba.managebudget.common.presentation.base

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.devamsba.managebudget.common.presentation.Listener

abstract class BaseViewHolder<T>(view: View) : RecyclerView.ViewHolder(view){
     abstract fun bind(item: T, listener: Listener<T>)
}
