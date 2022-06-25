package com.devamsba.managebudget.common.presentation.fragment.home

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.devamsba.managebudget.BR
import com.devamsba.managebudget.R
import com.devamsba.managebudget.common.presentation.Listener
import com.devamsba.managebudget.common.presentation.StateView
import com.devamsba.managebudget.common.presentation.adapter.HistoryAdapter
import com.devamsba.managebudget.common.presentation.base.BaseFragment
import com.devamsba.managebudget.databinding.HomeFragmentLayoutBinding
import com.devamsba.managebudget.feat_history.domain.entity.HistoryEntity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class HomeFragment(override val layoutRes: Int = R.layout.home_fragment_layout) :
    BaseFragment<HomeFragmentLayoutBinding, HomeViewModel>(),
    Listener<HistoryEntity> {
    override val viewModel: HomeViewModel by viewModels()

    override fun initDataBinding() {
        binding!!.setVariable(BR._all, viewModel)
    }


    @RequiresApi(Build.VERSION_CODES.M)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initDesignViews()
        setOnClickListener()
        setupRecyclerView()
        observe()
        fetchHistory()
        fetchIncomesTotalAmount()
        fetchExpensesTotalAmount()

    }

    private fun fetchExpensesTotalAmount() {
        viewModel.getSummationExpenses()
    }

    private fun fetchIncomesTotalAmount() {
        viewModel.getSummationIncomes()
    }

    private fun fetchHistory() {
        viewModel.fetchHistory()
    }

    private fun setupRecyclerView() {
        val historyAdapter = HistoryAdapter(this)
        binding?.historyOfTransactionRecyclerView?.apply {
            adapter = historyAdapter
            layoutManager = LinearLayoutManager(requireActivity())
        }
    }

    private fun observe() {
        observeState()
        observeHistory()
        observeIncomes()
        observeExpenses()
    }

    private fun observeState() {
        viewModel.stateHistory.flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
            .onEach { handleState(it) }
            .launchIn(lifecycleScope)

    }

    private fun observeHistory() {
        viewModel.history.flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
            .onEach { handleHistory(it) }
            .launchIn(lifecycleScope)
    }

    private fun observeExpenses() {
        viewModel.expensesSummation.flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
            .onEach { handleExpenses(it) }
            .launchIn(lifecycleScope)
    }

    private fun observeIncomes() {
        viewModel.incomesSummation.flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
            .onEach { handleIncomes(it) }
            .launchIn(lifecycleScope)
    }

    private fun handleExpenses(totalAmount: Double) {
        binding?.expensesAmountTextView?.text = totalAmount.toString()
    }

    private fun handleIncomes(totalAmount: Double) {
        binding?.incomeAmountTextView?.text = totalAmount.toString()
    }

    private fun handleHistory(history: List<HistoryEntity>) {
        Log.e("handleHistory", "handleHistory: ${history} ")
        binding?.historyOfTransactionRecyclerView?.adapter?.let { adapter ->
            if (adapter is HistoryAdapter)
                history?.let(adapter::submitList)
        }

    }

    private fun handleState(state: StateView) {
        when (state) {
            is StateView.ShowToast -> Toast.makeText(
                requireContext(),
                state.message,
                Toast.LENGTH_LONG
            ).show()
            //  is StateView.IsLoading -> handleLoading(state.isLoading)
            is StateView.Init -> Unit
        }
    }

    //    private fun handleLoading(isLoading: Boolean) {
//        if (isLoading) {
//            binding?.loadingBar?.visibility = View.VISIBLE
//        } else {
//            binding?.loadingBar?.visibility = View.GONE
//        }
//    }
    private fun setOnClickListener() {
        binding?.apply {

            addIncomeFloatingActionBar.setOnClickListener {
                findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToIncomesAddFragment())
            }

            addExpensesFloatingActionBar.setOnClickListener {
                findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToExpesesAddFragment())
            }
            showFloatingActionButton?.setOnClickListener {
                if (groupOfFloatingActionButton.isVisible) {
                    groupOfFloatingActionButton.visibility = View.INVISIBLE
                    showFloatingActionButton.setImageDrawable(
                        AppCompatResources.getDrawable(
                            requireContext(),
                            R.drawable.ic_arrow_up
                        )
                    )

                } else {
                    groupOfFloatingActionButton.visibility = View.VISIBLE
                    showFloatingActionButton.setImageDrawable(
                        AppCompatResources.getDrawable(
                            requireContext(),
                            R.drawable.ic_arrow_down
                        )
                    )

                }
            }


        }

    }

    private fun initDesignViews() {
        hideActionBar()
        changeColorStatusBar(R.color.white)
        changeTextStatusBar()
    }

    override fun onTap(entity: HistoryEntity) {
        TODO("Not yet implemented")
    }

    override fun onItemClicked(t: HistoryEntity) {
        TODO("Not yet implemented")
    }

}