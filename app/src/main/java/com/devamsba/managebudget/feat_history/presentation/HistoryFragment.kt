package com.devamsba.managebudget.feat_history.presentation

import androidx.fragment.app.viewModels
import com.devamsba.managebudget.BR
import com.devamsba.managebudget.R
import com.devamsba.managebudget.databinding.HistoryFragmentLayoutBinding
import com.devamsba.managebudget.common.presentation.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HistoryFragment(override val layoutRes: Int = R.layout.history_fragment_layout)
    : BaseFragment<HistoryFragmentLayoutBinding, HistoryViewModel>(){
    override val viewModel: HistoryViewModel by  viewModels()

    override fun initDataBinding() {
        binding!!.setVariable(BR._all, viewModel)
    }

}