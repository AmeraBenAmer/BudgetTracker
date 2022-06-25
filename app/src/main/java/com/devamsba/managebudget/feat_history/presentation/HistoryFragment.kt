package com.devamsba.managebudget.feat_history.presentation

import android.app.DatePickerDialog
import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.devamsba.managebudget.BR
import com.devamsba.managebudget.R
import com.devamsba.managebudget.common.presentation.CustomPieChartRenderer
import com.devamsba.managebudget.common.presentation.Listener
import com.devamsba.managebudget.common.presentation.StateView
import com.devamsba.managebudget.common.presentation.adapter.HistoryAdapter
import com.devamsba.managebudget.common.presentation.base.BaseFragment
import com.devamsba.managebudget.databinding.HistoryFragmentLayoutBinding
import com.devamsba.managebudget.feat_expense.domain.entity.TotalExpenseByCategory
import com.devamsba.managebudget.feat_history.domain.entity.HistoryEntity
import com.devamsba.managebudget.feat_incoms.domain.entity.TotalIncomesByCategory
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.formatter.PercentFormatter
import com.github.mikephil.charting.formatter.ValueFormatter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import java.text.NumberFormat
import java.util.*


@AndroidEntryPoint
class HistoryFragment(override val layoutRes: Int = R.layout.history_fragment_layout) :
    BaseFragment<HistoryFragmentLayoutBinding, HistoryViewModel>(),
    Listener<HistoryEntity> {

    override val viewModel: HistoryViewModel by viewModels()
    private var summationIncomes = 0.0
    private var summationExpenses = 0.0

    private var incomesGrouped: List<TotalIncomesByCategory> = arrayListOf()
    private var expensesGrouped: List<TotalExpenseByCategory> = arrayListOf()
    override fun initDataBinding() {
        binding!!.setVariable(BR._all, viewModel)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fetchIncomesTotalAmountGroupedByCategory()
        fetchExpensesTotalAmountGroupedByCategory()
        fetchHistory()

        setOnClickListener()
        observer()
        setFilterByDialog()
    }

    private fun fetchHistory() {
        viewModel.fetchHistory()
    }

    private fun setOnClickListener() {
        binding?.apply {

            expensesTextView.setOnClickListener {
                incomesPieChart.visibility = View.GONE

                if (!expensesPieChart.isVisible) {
                    expensesPieChart.visibility = View.VISIBLE
                }

                binding?.expensesPieChart?.animateY(1400, Easing.EaseInOutQuad)

                expensesTextView.setBackgroundDrawable(
                    AppCompatResources.getDrawable(
                        requireContext(),
                        R.drawable.bg_expenses_layout
                    )
                )
                incomesTextView.setBackgroundDrawable(
                    AppCompatResources.getDrawable(
                        requireContext(),
                        R.drawable.bg_gray_layout
                    )
                )
            }


            incomesTextView.setOnClickListener {
                expensesPieChart.visibility = View.GONE

                if (!incomesPieChart.isVisible) {
                    incomesPieChart.visibility = View.VISIBLE
                }

                binding?.incomesPieChart?.animateY(1400, Easing.EaseInOutQuad)

                expensesTextView.setBackgroundDrawable(
                    AppCompatResources.getDrawable(
                        requireContext(),
                        R.drawable.bg_gray_layout
                    )
                )
                incomesTextView.setBackgroundDrawable(
                    AppCompatResources.getDrawable(
                        requireContext(),
                        R.drawable.bg_expenses_layout
                    )
                )
            }
        }
    }

    private fun fetchExpensesTotalAmountGroupedByCategory() {
        viewModel.getSummationExpensesGroupedByCategory()
    }

    private fun fetchIncomesTotalAmountGroupedByCategory() {
        viewModel.getSummationIncomesGroupedByCategory()
    }

    private fun observer() {
        observeExpenses()
        observeState()

        observeHistory()

        observeIncomes()
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        val historyAdapter = HistoryAdapter(this)
        binding?.historyOfTransactionRecyclerView?.apply {
            adapter = historyAdapter
            layoutManager = LinearLayoutManager(requireActivity())
        }
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

    private fun observeExpenses() {
        viewModel.expensesSummationGroupedByCategory.flowWithLifecycle(
            lifecycle,
            Lifecycle.State.STARTED
        )
            .onEach { handleExpenses(it) }
            .launchIn(lifecycleScope)
    }

    private fun observeIncomes() {
        viewModel.incomesSummationGroupedByCategory.flowWithLifecycle(
            lifecycle,
            Lifecycle.State.STARTED
        )
            .onEach { handleIncomes(it) }
            .launchIn(lifecycleScope)
    }

    private fun handleIncomes(amountIncomes: List<TotalIncomesByCategory>) {
        incomesGrouped = amountIncomes
        amountIncomes.forEach { income ->
            summationIncomes = +income.totalAmount
        }

        Log.e("summationIncomes", "handleIncomes: $amountIncomes")
        Log.e("summationIncomes", "handleIncomes: $summationIncomes")

        if (amountIncomes.isNotEmpty()) {
            Log.e(
                "summationIncomes",
                "handleIncomes: ${amountIncomes[0].totalAmount} || ${amountIncomes[0].id_fk_category}",
            )

            val avrage: Double = amountIncomes[0].totalAmount / summationIncomes * 100
            Log.e("summationIncomes", "handleIncomes: ${avrage} ")
        }
        setupPieChart()


    }

    private fun handleExpenses(amountExpense: List<TotalExpenseByCategory>) {
        expensesGrouped = amountExpense

        amountExpense.forEach { expenses ->
            summationExpenses = +expenses.totalAmount

        }
        Log.e("summationIncomes", "handleIncomes: $summationIncomes")

        if (amountExpense.isNotEmpty()) {
            Log.e(
                "summationIncomes",
                "handleIncomes: ${amountExpense[0].totalAmount} || ${amountExpense[0].id_fk_category}",
            )

            val avrage: Double = amountExpense[0].totalAmount / summationIncomes * 100
            Log.e("summationIncomes", "handleIncomes: ${avrage} ")
        }
        setupExpensesPieChart()


    }


    private fun setFilterByDialog() {
        binding?.filterByTextView?.setOnClickListener {

            val cal: Calendar = Calendar.getInstance()
            val year = cal.get(Calendar.YEAR)
            val month = cal.get(Calendar.MONTH)
            val day = cal.get(Calendar.DAY_OF_MONTH)

            val mDateSetListener =
                DatePickerDialog.OnDateSetListener { _, year, month, day ->

                    cal.set(Calendar.MONTH, month)
                    cal.set(Calendar.YEAR, year)
                    val MONTHS = arrayOf(
                        "Jan", "Feb", "Mar", "Apr", "May", "Jun",
                        "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"
                    )
                    binding?.filterByTextView?.text = "${MONTHS[month]}  ${year}"
                    Log.e("setFilterByDialog", "setFilterByDialog: ${month}")
                    viewModel.filterByMonth(month = month + 1, year = year)
                }
            val dialog = DatePickerDialog(
                requireActivity(),
                android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                mDateSetListener,
                year, month, day
            )
            dialog.datePicker.findViewById<View>(
                resources.getIdentifier(
                    "day",
                    "id",
                    "android"
                )
            ).visibility = View.GONE

            dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            dialog.show()

        }
    }

    private fun setupExpensesPieChart() {
        binding?.expensesPieChart?.apply {
            renderer = CustomPieChartRenderer(this, 8f)
            setUsePercentValues(true)
            description?.text = ""
            //hollow pie chart
            isDrawHoleEnabled = false
            setTouchEnabled(false)
            //adding padding
            setExtraOffsets(20f, 20f, 20f, 20f)
            isRotationEnabled = false
            legend?.orientation = Legend.LegendOrientation.VERTICAL
            legend?.isEnabled = true
            legend?.isWordWrapEnabled = true

            setDrawEntryLabels(false)
            setEntryLabelColor(Color.BLACK)
            setEntryLabelTextSize(10f)
            setEntryLabelTypeface(Typeface.DEFAULT_BOLD)


            setDataExpensesToPieChart()
        }
    }

    private fun setupPieChart() {
        binding?.incomesPieChart?.apply {
            renderer = CustomPieChartRenderer(this, 8f)
            setUsePercentValues(true)
            description?.text = ""
            //hollow pie chart
            isDrawHoleEnabled = false
            setTouchEnabled(false)
            //adding padding
            setExtraOffsets(20f, 20f, 20f, 20f)
            isRotationEnabled = false
            legend?.orientation = Legend.LegendOrientation.VERTICAL
            legend?.isEnabled = true
            legend?.isWordWrapEnabled = true
            setDrawEntryLabels(false)
            setEntryLabelColor(Color.BLACK)
            setEntryLabelTextSize(10f)
            setEntryLabelTypeface(Typeface.DEFAULT_BOLD)


            setDataToPieChart()
        }
    }

    private fun setDataToPieChart() {
        val dataEntries = ArrayList<PieEntry>()
        Log.e("setData", "setDataToPieChart: $incomesGrouped")

        if (incomesGrouped.isNotEmpty()) {
            incomesGrouped.forEach { data ->
                val avrage: Double = data.totalAmount / summationIncomes * 100

                dataEntries.add(PieEntry(avrage.toFloat(), data.id_fk_category))

            }
        }


        val colors: ArrayList<Int> = ArrayList()
        colors.add(Color.parseColor("#4DD0E1"))
        colors.add(Color.parseColor("#FFF176"))
        colors.add(Color.parseColor("#FF8A65"))

        val dataSet = PieDataSet(dataEntries, "")
        val data = PieData(dataSet)

        // In Percentage
        data.setValueFormatter(PercentFormatter())
        dataSet.sliceSpace = 3f
        dataSet.colors = colors
        binding?.incomesPieChart?.data = data
        data.setValueTextSize(15f)
 //        binding?.expensesPieChart?.animateY(1400, Easing.EaseInOutQuad)

        // Value lines
        dataSet.valueLinePart1Length = 0.6f
        dataSet.valueLinePart2Length = 0.3f
        dataSet.valueLineWidth = 2f
        dataSet.valueLinePart1OffsetPercentage = 115f  // Line starts outside of chart
        dataSet.isUsingSliceColorAsValueLineColor = true

        // Value text appearance
        dataSet.yValuePosition = PieDataSet.ValuePosition.OUTSIDE_SLICE
        dataSet.valueTextSize = 14f
        dataSet.valueTypeface = Typeface.DEFAULT_BOLD

        // Value formatting
        dataSet.valueFormatter = object : ValueFormatter() {
            private val formatter = NumberFormat.getPercentInstance()

            override fun getFormattedValue(value: Float) =
                formatter.format(value / 100f)
        }
        binding?.incomesPieChart?.setUsePercentValues(true)

        dataSet.selectionShift = 3f

        //create hole in center
        binding?.incomesPieChart?.holeRadius = 50f
        binding?.incomesPieChart?.transparentCircleRadius = 40f
        binding?.incomesPieChart?.isDrawHoleEnabled = true
        binding?.incomesPieChart?.setHoleColor(Color.WHITE)


        //add text in center
        binding?.incomesPieChart?.setDrawCenterText(true)
        binding?.incomesPieChart?.centerText = "Incomes"



        binding?.incomesPieChart?.invalidate()

    }

    private fun setDataExpensesToPieChart() {
        val dataEntries = ArrayList<PieEntry>()
        Log.e("setData", "setDataToPieChart: $expensesGrouped")

        if (expensesGrouped.isNotEmpty()) {
            expensesGrouped.forEach { data ->
                val avrage: Double = data.totalAmount / summationExpenses * 100

                dataEntries.add(PieEntry(avrage.toFloat(), data.id_fk_category))

            }
        }


        val colors: ArrayList<Int> = ArrayList()
        colors.add(Color.parseColor("#4DD0E1"))
        colors.add(Color.parseColor("#FFF176"))
        colors.add(Color.parseColor("#FF8A65"))

        val dataSet = PieDataSet(dataEntries, "")
        val data = PieData(dataSet)

        // In Percentage
        data.setValueFormatter(PercentFormatter())
        dataSet.sliceSpace = 3f
        dataSet.colors = colors
        binding?.expensesPieChart?.data = data
        data.setValueTextSize(15f)
 //        binding?.expensesPieChart?.animateY(1400, Easing.EaseInOutQuad)

        // Value lines
        dataSet.valueLinePart1Length = 0.6f
        dataSet.valueLinePart2Length = 0.3f
        dataSet.valueLineWidth = 2f
        dataSet.valueLinePart1OffsetPercentage = 115f  // Line starts outside of chart
        dataSet.isUsingSliceColorAsValueLineColor = true

        // Value text appearance
        dataSet.yValuePosition = PieDataSet.ValuePosition.OUTSIDE_SLICE
        dataSet.valueTextSize = 16f
        dataSet.valueTypeface = Typeface.DEFAULT_BOLD

        // Value formatting
        dataSet.valueFormatter = object : ValueFormatter() {
            private val formatter = NumberFormat.getPercentInstance()

            override fun getFormattedValue(value: Float) =
                formatter.format(value / 100f)
        }
        binding?.expensesPieChart?.setUsePercentValues(true)

        dataSet.selectionShift = 3f

        //create hole in center
        binding?.expensesPieChart?.holeRadius = 50f
        binding?.expensesPieChart?.transparentCircleRadius = 40f
        binding?.expensesPieChart?.isDrawHoleEnabled = true
        binding?.expensesPieChart?.setHoleColor(Color.WHITE)


        //add text in center
        binding?.expensesPieChart?.setDrawCenterText(true)
        binding?.expensesPieChart?.centerText = "expenses"



        binding?.expensesPieChart?.invalidate()

    }

    override fun onTap(entity: HistoryEntity) {
        TODO("Not yet implemented")
    }

    override fun onItemClicked(t: HistoryEntity) {
        TODO("Not yet implemented")
    }
}

