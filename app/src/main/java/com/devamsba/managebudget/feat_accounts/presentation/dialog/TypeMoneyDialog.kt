package com.devamsba.managebudget.feat_accounts.presentation.dialog

import android.app.Application
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.devamsba.managebudget.R
import com.devamsba.managebudget.common.infra.App
import com.devamsba.managebudget.common.presentation.base.BaseActivity
import com.devamsba.managebudget.common.presentation.base.BaseDailog
import com.devamsba.managebudget.databinding.OneInputDialogBinding
import com.devamsba.managebudget.feat_accounts.domain.entity.AccountsEntity
import com.devamsba.managebudget.feat_accounts.presentation.MyBankAccountViewModel
import com.maltaisn.icondialog.IconDialog
import com.maltaisn.icondialog.IconDialogSettings
import com.maltaisn.icondialog.data.Icon
import com.maltaisn.icondialog.pack.IconPack
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class TypeMoneyDialog constructor(

    override val layout: Int = R.layout.one_input_dialog
) : BaseDailog<OneInputDialogBinding>(), IconDialog.Callback {


    @Inject
    lateinit var application: Application

    val viewModel:
            MyBankAccountViewModel by viewModels()

    lateinit var iconDialog: IconDialog

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dialog?.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )

        dialog?.window?.setGravity(Gravity.BOTTOM)
        dialog?.window?.setBackgroundDrawableResource(android.R.color.transparent)

        // If dialog is already added to fragment manager, get it. If not, create a new instance.
        iconDialog =
            childFragmentManager.findFragmentByTag(ICON_DIALOG_TAG) as IconDialog?
                ?: IconDialog.newInstance(IconDialogSettings())

        //  insertData()

        setOnClickListener()
        viewDataBinding?.accountEditText?.requestFocus()
        // Show Soft Keyboard
        viewDataBinding?.accountEditText?.let { (activity as BaseActivity<*, *>).showSoftInput(it) }

    }


    private fun setOnClickListener() {
        viewDataBinding?.apply {
            saveTypeTextView.setOnClickListener {
                validationInput()

            }

            imageViewIconSelection.setOnClickListener {
                openIconsDialog()
            }

            closeDialogOfLanguageTextView.setOnClickListener {
                dismiss()
            }
        }
    }

    private fun openIconsDialog() {
        iconDialog.show(childFragmentManager, ICON_DIALOG_TAG)

    }

    override val iconDialogIconPack: IconPack?
        get() = (application as App).iconPack

    override fun onIconDialogIconsSelected(dialog: IconDialog, icons: List<Icon>) {
        // Show a toast with the list of selected icon IDs.
        Toast.makeText(
            requireContext(),
            "Icons selected: ${
                icons.map {
                    it.id
                    viewDataBinding?.imageViewIconSelection?.setImageDrawable(it.drawable)

                }
            }",
            Toast.LENGTH_SHORT
        ).show()


    }

    companion object {
        private const val ICON_DIALOG_TAG = "icon-dialog"
    }

    private fun validationInput() {
        Log.e("TAG", "validationInput: ${viewDataBinding!!.accountEditText.text}")
        if (viewDataBinding!!.accountEditText.text!!.trim().isEmpty()) {
            Log.e("TAG", "validationInput: if ${viewDataBinding!!.accountEditText.text}")
            viewDataBinding!!.accountEditText.error = "This field should be not empty"
            viewDataBinding!!.accountEditText.background =
                ResourcesCompat.getDrawable(resources, R.drawable.bg_error_edit_text, null)

        } else {
            Log.e("TAG", "validationInput: else ${viewDataBinding!!.accountEditText.text}")

            storeDataToLocalDb()
        }


    }

    private fun storeDataToLocalDb() {
        val typeOfEditText = viewDataBinding!!.accountEditText.text.toString()
        val type = AccountsEntity(name = typeOfEditText)
        viewModel.insertAccounts(type)
        (activity as BaseActivity<*, *>).hideSoftInput()

        findNavController().navigateUp()
    }
}