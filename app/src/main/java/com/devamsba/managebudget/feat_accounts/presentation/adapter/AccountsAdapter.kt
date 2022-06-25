package com.devamsba.managebudget.feat_accounts.presentation.adapter

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import com.devamsba.managebudget.BR
import com.devamsba.managebudget.R
import com.devamsba.managebudget.common.presentation.Listener
import com.devamsba.managebudget.common.presentation.base.BaseAdapter
import com.devamsba.managebudget.common.presentation.base.BaseViewHolder
import com.devamsba.managebudget.feat_accounts.domain.entity.AccountsEntity


class AccountsAdapter(listener: Listener<AccountsEntity>)
    : BaseAdapter<AccountsEntity>(DiffCallBack(), listener) {

    class DiffCallBack: DiffUtil.ItemCallback<AccountsEntity>(){
        override fun areItemsTheSame(oldItem: AccountsEntity, newItem: AccountsEntity): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: AccountsEntity, newItem: AccountsEntity): Boolean {
            return oldItem == newItem
        }

    }

    override fun getItemViewType(position: Int) = if (getItem(position) is AccountsEntity){
        R.layout.item_list_of_accounts
    }else{
        R.layout.empty_data_layout
    }

}