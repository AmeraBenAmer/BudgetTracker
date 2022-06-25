package com.devamsba.managebudget.common.presentation.base

import android.content.Context
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.annotation.ColorRes
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.devamsba.managebudget.common.infra.utils.OnOneOffClickListener
import java.lang.Exception
import android.app.Activity
import androidx.core.view.WindowInsetsCompat


abstract class BaseActivity<out T: ViewDataBinding, VM : BaseViewModel>: AppCompatActivity() {
    abstract fun initDataBinding()

    @get:LayoutRes
    protected abstract val layout :Int

    abstract val viewModel :VM

    val binding by lazy{
        DataBindingUtil.setContentView(this, layout) as T
    }

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        initDataBinding()
    }


    fun hideKeyboard(){

        val view = this.currentFocus
        if (view != null){
            val  inputManager = this.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputManager.hideSoftInputFromWindow(view.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
        }
    }

    open fun showSoftInput(view: View) {
        /*val inputMethodManager =
            this.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.toggleSoftInput(0, InputMethodManager.RESULT_SHOWN)*/

//        val insetsController = ViewCompat.getWindowInsetsController(view)
//        insetsController?.show(WindowInsetsCompat.Type.ime())
//        val imm: InputMethodManager? =
//            this.getSystemService(Context.INPUT_METHOD_SERVICE)  as InputMethodManager?
//        imm?.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT)
        val inputMethodManager = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0)
       // inputMethodManager.showSoftInput(view, InputMethodManager.SHOW_FORCED)
    }
    open fun hideSoftInput() {
        val view = this.currentFocus
        if (view != null){
            val imm =
                getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
           // imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0)
        }

//        val inputMethodManager = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
//        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }
    fun View.setSafeOnClickListener(onSafeClick: (View) -> Unit){
        try {
            val safeClickListener = OnOneOffClickListener{view ->
                onSafeClick(view)
            }
            setOnClickListener(safeClickListener)

        }catch (e: Exception){
            showMessage("Please try again , ${e.message}")
        }
    }

    fun hideActionBar(){
        supportActionBar?.hide()
    }

    fun changeColorStatusBar(@ColorRes color : Int){
        val window = this.window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.statusBarColor = this.resources.getColor(color)
        ViewCompat.getWindowInsetsController(window.decorView)?.apply {
            // Light text == dark status bar
            isAppearanceLightStatusBars = false
        }

    }

    fun changeTextStatusBar(){
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR

    }

    fun showMessage(message: String){
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }
}