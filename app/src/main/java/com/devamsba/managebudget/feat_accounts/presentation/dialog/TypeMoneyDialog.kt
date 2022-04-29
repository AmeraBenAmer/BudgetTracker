package com.devamsba.managebudget.feat_accounts.presentation.dialog

import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.devamsba.managebudget.R
import com.devamsba.managebudget.common.data.typeMoney.entity.TypeMoneyEntity
import com.devamsba.managebudget.databinding.TypeMoneyDialogBinding
import com.devamsba.managebudget.common.presentation.base.BaseDailog
import com.devamsba.managebudget.feat_accounts.presentation.MyBankAccountViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class TypeMoneyDialog constructor(override val layout: Int = R.layout.type_money_dialog
):BaseDailog<TypeMoneyDialogBinding>() {


      val viewModel:
              MyBankAccountViewModel by viewModels()



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dialog?.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)

        dialog?.window?.setGravity(Gravity.BOTTOM)
        dialog?.window?.setBackgroundDrawableResource(android.R.color.transparent)


      //  insertData()

        setOnClickListener()

    }

    private fun setOnClickListener(){
        viewDataBinding?.apply {
            saveTypeTextView.setOnClickListener {
                insertData()
            }

            closeDialogOfLanguageTextView.setOnClickListener {
                dismiss()
            }
        }


    }

    private fun insertData(){
        val typeOfEditText = viewDataBinding!!.incomeTypeEditText.text.toString()
        val type  = TypeMoneyEntity(type = typeOfEditText)
        Log.e("insertData", "insertData: here 1 ", )
        Log.e("insertData", "insertData: here 1 ${type} ", )
        viewModel.insertTypeMoney(type)
        dismiss()
    }
}