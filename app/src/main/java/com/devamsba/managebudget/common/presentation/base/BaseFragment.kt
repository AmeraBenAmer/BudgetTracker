package com.devamsba.managebudget.common.presentation.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.devamsba.managebudget.common.infra.utils.OnOneOffClickListener

abstract class BaseFragment<T: ViewDataBinding, VM: BaseViewModel>: Fragment() {

    private lateinit var mViewDataBinding: T
    var mActivity: BaseActivity<T, VM>? = null

    @get:LayoutRes
    protected abstract val layoutRes: Int

    var baseActivity : BaseActivity<T, VM>? = null
    private set

     var binding: T? = null
     private set


    private var rootView: View? = null

    abstract val viewModel : VM


    abstract fun initDataBinding()

     override fun onCreateView(
         inflater: LayoutInflater,
         container: ViewGroup?,
         savedInstanceState: Bundle?
     ): View? {
         binding = DataBindingUtil.inflate(inflater, layoutRes, container, false)
         rootView = binding!!.root
         binding!!.lifecycleOwner = this

         initDataBinding()
         return rootView
     }

     override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
         super.onViewCreated(view, savedInstanceState)

         mViewDataBinding = binding!!
         mViewDataBinding.lifecycleOwner = this
         mViewDataBinding.executePendingBindings()
     }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
     override fun onAttach(context: Context) {
         super.onAttach(context)

         if (context is BaseActivity<*, *>){
             mActivity = context as BaseActivity<T, VM>?

         }
     }

     override fun onDetach() {
         super.onDetach()
         mActivity =null
     }

     fun hideKeyboard() = mActivity?.hideKeyboard()
     fun showKeyboard(view: View) = mActivity?.showSoftInput(view)
     fun showMessage(message: String) = mActivity?.showMessage(message)
    fun hideActionBar() = mActivity?.hideActionBar()
    fun changeColorStatusBar(color: Int) = mActivity?.changeColorStatusBar(color)
    fun changeTextStatusBar() = mActivity?.changeTextStatusBar()
     /**
      * prevent double click on view
      **/
     fun View.setSafeOnClickListener(onSafeClick: (View) -> Unit) {
         try {
             val safeClickListener = OnOneOffClickListener {
                 onSafeClick(it)
             }
             setOnClickListener(safeClickListener)
         } catch (E: Exception) {
         }
     }

}