package com.devamsba.managebudget.common.presentation.base

import android.content.Context
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.DialogFragment

abstract class BaseDailog<T: ViewDataBinding> : DialogFragment(){
    private var baseActivity: BaseActivity<*, *>? = null
    private var mRootView : View? = null

    var viewDataBinding: T? = null
        private set

    @get:LayoutRes
    abstract val layout: Int

    override fun onAttach(context: Context) {
        super.onAttach(context)

        if (context is BaseActivity<*,*>){
            baseActivity = context
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setStyle(STYLE_NORMAL,
//        android.R.style.Theme_Material_Light_Dialog)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewDataBinding = DataBindingUtil.inflate(inflater, layout,container, false)
        dialog?.window?.apply {
            setGravity(Gravity.BOTTOM)
            setBackgroundDrawableResource(android.R.color.transparent)
        }

        mRootView = viewDataBinding?.root
        return mRootView
    }

    override fun onDetach() {
        super.onDetach()
        baseActivity = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewDataBinding?.lifecycleOwner = this
    }
}