package com.devamsba.managebudget.feat_expense.presentation

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import androidx.annotation.RequiresApi
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.devamsba.managebudget.BR
import com.devamsba.managebudget.R
import com.devamsba.managebudget.common.domain.validator.EmptyValidator
import com.devamsba.managebudget.common.infra.Either
import com.devamsba.managebudget.common.infra.showMessage
import com.devamsba.managebudget.common.presentation.StateView
import com.devamsba.managebudget.common.presentation.base.BaseFragment
import com.devamsba.managebudget.databinding.ExpesesAddFragmentLayoutBinding
import com.devamsba.managebudget.feat_accounts.domain.entity.AccountsEntity
import com.devamsba.managebudget.feat_categries.domain.entity.CategoriesEntity
import com.devamsba.managebudget.feat_expense.domain.entity.ExpenseEntity
import com.devamsba.managebudget.feat_history.domain.entity.HistoryEntity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import java.time.LocalDateTime
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.*

@AndroidEntryPoint
class ExpesesAddFragment(override val layoutRes: Int = R.layout.expeses_add_fragment_layout) :
    BaseFragment<ExpesesAddFragmentLayoutBinding, ExpesesAddViewModel>() {
    lateinit var amount: String
    lateinit var currency: String
    lateinit var title: String
    lateinit var account: String
    lateinit var category: String

    override val viewModel: ExpesesAddViewModel by viewModels()

    override fun initDataBinding() {
        binding!!.setVariable(BR._all, viewModel)
    }


    @RequiresApi(Build.VERSION_CODES.M)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initDesignViews()
        initCurrencyData()
        initTypeData()
        fetchAccounts()
        fetchCategories()
        observe()

        setOnClickListener()

//        val en = IncomeEntity(id = 1 ,title = "First", amount = 22.2, date ="12,3,1312", isNotify = false , currency = 1)
//        viewModel.insertIncome(en)
//        addViewModel.fetchIncomes()
//        this.lifecycleScope.launchWhenStarted {
//            addViewModel.incomes.collect { it ->
//                Log.e("onViewCreated", "onViewCreated: ${it.toString()} ",)
//            }
//        }


    }

    private fun setOnClickListener() {
        binding?.apply {
            saveButton.setOnClickListener {
                validation()
                insertExpenseData()
            }
        }
    }

    private fun insertExpenseData() {

        Log.e("current time ", "insertIncomesData: ${Calendar.getInstance().time}")
        Log.e("current zoned ", "insertIncomesData: ${ZonedDateTime.now()}")
        val expenseEntity = ExpenseEntity(
            title = title,
            amount = amount.toDouble(),
            accountName = account,
            currency = currency,
            dateTime = Calendar.getInstance().time.toString(),
            isNotify = false,
            idFKCategory = category,

        )

        viewModel.insertExpense(expenseEntity)
        var history = "you add $amount $currency to $account account as $category Category"
        Log.e("insertIncomesData", "insertIncomesData: ${history} ")

        lifecycleScope.launchWhenStarted {
            val current = LocalDateTime.now()
            Log.e("insertIncomesData", "insertIncomesData: Year:  ${current.year} | Month =  ${current.monthValue}")

            val formatter = DateTimeFormatter.ofPattern("EEEE,   dd-MMM-yyyy  hh:mm  a")
            val formatted = current.format(formatter)
            val result = viewModel.insertHistory(
                HistoryEntity(
                    actionTitle = history,
                    amount = amount.toDouble(),
                    dateTime = formatted,
                    type = "Expenses",
                    year = current.year,
                    month = current.monthValue
                )
            )
            when (result) {
                is Either.Left -> requireActivity().showMessage("error")
                is Either.Right -> requireActivity().showMessage("Successful")

            }
        }

        findNavController().navigateUp()
    }


    private fun fetchAccounts() {
        viewModel.fetchAccounts()
    }

    private fun fetchCategories() {
        viewModel.fetchCategories(type = "Expenses")
    }

    private fun observe() {
        observeState()
        observeAccounts()
        observeCategories()
    }

    private fun observeState() {
        viewModel.stateAccounts.flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
            .onEach { handleState(it) }
            .launchIn(lifecycleScope)
    }

    private fun observeAccounts() {
        viewModel.accounts.flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
            .onEach { handleAccounts(it) }
            .launchIn(lifecycleScope)
    }

    private fun observeCategories() {
        viewModel.categories.flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
            .onEach { handleCategories(it) }
            .launchIn(lifecycleScope)
    }

    private fun handleAccounts(accounts: List<AccountsEntity>) {


        val nameAccountList = arrayListOf<String>()
        accounts.forEach { account ->
            nameAccountList.add(account.name)
        }

        val arrayAdapter = ArrayAdapter(requireContext(), R.layout.dropdown_item, nameAccountList)
        binding?.accountEditText?.setAdapter(arrayAdapter)
    }

    private fun handleCategories(categories: List<CategoriesEntity>) {


        val nameCategoriesList = arrayListOf<String>()
        categories.forEach { account ->
            nameCategoriesList.add(account.name)
        }

        val arrayAdapter =
            ArrayAdapter(requireContext(), R.layout.dropdown_item, nameCategoriesList)
        binding?.categoryEditText?.setAdapter(arrayAdapter)
    }

    private fun handleState(state: StateView) {
        when (state) {
            is StateView.ShowToast -> requireActivity().showMessage(state.message)
            //   is StateView.IsLoading -> handleLoading(state.isLoading)
            is StateView.Init -> Unit
        }
    }

    private fun initTypeData() {

    }

    private fun initCurrencyData() {
        val currencyList = resources.getStringArray(R.array.list_of_currency)
        val arrayAdapter = ArrayAdapter(requireContext(), R.layout.dropdown_item, currencyList)
        binding?.currencyEditText?.setAdapter(arrayAdapter)
    }

    private fun initDesignViews() {
        hideActionBar()
        changeColorStatusBar(R.color.white)
        changeTextStatusBar()
    }


    private fun validation() {
        validationAmount()
        validationCurrency()
        validationTitle()
        validationAccount()
        validationCategory()
    }

    private fun validationAmount() {
        amount = binding?.incomeAmountEditText?.text.toString()
        val amountEmptyValidation = EmptyValidator(amount).validate()
        binding?.layoutOfEditTextIncomeAmount?.error =
            if (!amountEmptyValidation.isSuccess) getString(amountEmptyValidation.message) else null
    }

    private fun validationCurrency() {
        currency = binding?.currencyEditText?.text.toString()
        val currencyEmptyValidation = EmptyValidator(currency).validate()
        binding?.layoutOfEditTextCurrency?.error =
            if (!currencyEmptyValidation.isSuccess) getString(currencyEmptyValidation.message) else null
    }

    private fun validationTitle() {
        title = binding?.titleEditText?.text.toString()
        val titleEmptyValidation = EmptyValidator(title).validate()
        binding?.layoutOfEditTextTitle?.error =
            if (!titleEmptyValidation.isSuccess) getString(titleEmptyValidation.message) else null
    }

    private fun validationAccount() {
        account = binding?.accountEditText?.text.toString()
        val accountEmptyValidation = EmptyValidator(account).validate()
        binding?.layoutOfEditTextAccounts?.error =
            if (!accountEmptyValidation.isSuccess) getString(accountEmptyValidation.message) else null
    }

    private fun validationCategory() {
        category = binding?.categoryEditText?.text.toString()
        val categoryEmptyValidation = EmptyValidator(category).validate()
        binding?.layoutOfEditTextCategory?.error =
            if (!categoryEmptyValidation.isSuccess) getString(categoryEmptyValidation.message) else null
    }

    private fun validationDate() {
        val date = binding?.dateEditText?.text.toString()
        val dateEmptyValidation = EmptyValidator(date).validate()
        binding?.layoutOfEditTextDate?.error =
            if (!dateEmptyValidation.isSuccess) getString(dateEmptyValidation.message) else null
    }


}