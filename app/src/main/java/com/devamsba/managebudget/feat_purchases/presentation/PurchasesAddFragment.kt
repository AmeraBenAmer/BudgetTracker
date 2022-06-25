package com.devamsba.managebudget.feat_purchases.presentation

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.devamsba.managebudget.BR
import com.devamsba.managebudget.R
import com.devamsba.managebudget.common.presentation.Listener
import com.devamsba.managebudget.common.presentation.base.BaseFragment
import com.devamsba.managebudget.databinding.PurchasesAddFragmentLayoutBinding
import com.devamsba.managebudget.feat_purchases.domain.entity.PurchasesEntity
import com.devamsba.managebudget.feat_purchases.presentation.adapter.InsertPurchasesAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PurchasesAddFragment(override val layoutRes: Int = R.layout.purchases_add_fragment_layout) :
    BaseFragment<PurchasesAddFragmentLayoutBinding, PurchasesViewModel>(),
    Listener<PurchasesEntity> {
    override val viewModel: PurchasesViewModel by viewModels()

    private lateinit var insertPurchasesAdapter: InsertPurchasesAdapter


    override fun initDataBinding() {
        binding!!.setVariable(BR._all, viewModel)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()


      /*  binding?.recyclerViewInsertPurchases?.adapter.let { adapter ->
            if (adapter is InsertPurchasesAdapter)
                adapter.submitList(
                    mutableListOf(
                        PurchasesEntity(
                            id = 1,
                            dateTime = "12,34,3434",
                            "hghh",
                            20.0,
                            "dfhds",
                            8
                        )
                    )
                )
        }*/

    }


    private fun initRecyclerView() {
        insertPurchasesAdapter = InsertPurchasesAdapter(this)
        binding?.apply {
            recyclerViewInsertPurchases.apply {
                adapter = insertPurchasesAdapter
                layoutManager = LinearLayoutManager(requireActivity())
            }

        }
//        insertPurchasesAdapter.submitList(
//            mutableListOf(
//                PurchasesEntity(
//                    id = 1,
//                    dateTime = "12,34,3434",
//                    "hghh",
//                    20.0,
//                    "dfhds",
//                    8
//                )
//            )
//        )
    }

    override fun onTap(entity: PurchasesEntity) {
        TODO("Not yet implemented")
    }

    override fun onItemClicked(t: PurchasesEntity) {
        TODO("Not yet implemented")
    }

}