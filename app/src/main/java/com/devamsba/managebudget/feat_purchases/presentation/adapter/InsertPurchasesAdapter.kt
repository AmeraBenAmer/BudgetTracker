package com.devamsba.managebudget.feat_purchases.presentation.adapter

import androidx.recyclerview.widget.DiffUtil
import com.devamsba.managebudget.common.presentation.Listener
import com.devamsba.managebudget.common.presentation.base.BaseAdapter
import com.devamsba.managebudget.feat_purchases.domain.entity.PurchasesEntity

class InsertPurchasesAdapter(private val listener: Listener<PurchasesEntity>) :
    BaseAdapter<PurchasesEntity>(InsertPurchasesAdapter.DiffCallBack(), listener) {

    class DiffCallBack : DiffUtil.ItemCallback<PurchasesEntity>() {
        override fun areItemsTheSame(oldItem: PurchasesEntity, newItem: PurchasesEntity): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: PurchasesEntity,
            newItem: PurchasesEntity
        ): Boolean {
            return oldItem == newItem
        }

    }


}