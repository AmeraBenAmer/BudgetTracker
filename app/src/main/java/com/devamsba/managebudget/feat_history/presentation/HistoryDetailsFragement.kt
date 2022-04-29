package com.devamsba.managebudget.feat_history.presentation

import com.devamsba.managebudget.R
import com.devamsba.managebudget.databinding.HistoryDetailsFragmentLayoutBinding
import com.devamsba.managebudget.common.presentation.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HistoryDetailsFragement(override val layoutRes: Int = R.layout.history_details_fragment_layout)
    : BaseFragment<HistoryDetailsFragmentLayoutBinding, HistoryViewModel>(){
    override val viewModel: HistoryViewModel
        get() = TODO("Not yet implemented")

    override fun initDataBinding() {
        TODO("Not yet implemented")
    }

}