package com.devamsba.managebudget.common.presentation.adapter

import android.util.Log
import androidx.recyclerview.widget.DiffUtil
import com.devamsba.managebudget.R
import com.devamsba.managebudget.common.presentation.Listener
import com.devamsba.managebudget.common.presentation.base.BaseAdapter
import com.devamsba.managebudget.feat_history.domain.entity.HistoryEntity


class HistoryAdapter(listener: Listener<HistoryEntity>) :
    BaseAdapter<HistoryEntity>(DiffCallBack(), listener) {

    class DiffCallBack : DiffUtil.ItemCallback<HistoryEntity>() {
        override fun areItemsTheSame(oldItem: HistoryEntity, newItem: HistoryEntity): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: HistoryEntity, newItem: HistoryEntity): Boolean {
            return oldItem == newItem
        }

    }

    override fun getItemViewType(position: Int) = if (getItem(position) is HistoryEntity){
        Log.e("getItemViewType", "getItemViewType:  HEre  if", )
        R.layout.item_list_of_transaction
    }else{
        Log.e("getItemViewType", "getItemViewType:  HEre  else", )
        R.layout.empty_data_layout
    }



}