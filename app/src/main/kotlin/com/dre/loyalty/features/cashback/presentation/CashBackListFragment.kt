/*
 * Created by Andreas Oen on 1/3/21 2:32 PM
 * Copyright (c) 2021 . All rights reserved.
 * Last modified 1/3/21 2:32 PM
 * github: https://github.com/oandrz
 */

package com.dre.loyalty.features.cashback.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dre.loyalty.R
import com.dre.loyalty.core.platform.BaseFragment
import com.dre.loyalty.core.view.VerticalDividerDecoration
import com.dre.loyalty.databinding.FragmentCasbackListBinding
import com.dre.loyalty.features.cashback.presentation.entity.CashBack
import com.dre.loyalty.features.cashback.presentation.item.CashBackItem
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.adapters.ItemAdapter

class CashBackListFragment : BaseFragment() {

    private var binding: FragmentCasbackListBinding? = null

    private val cashBackItem: ItemAdapter<CashBackItem> by lazy {
        ItemAdapter<CashBackItem>()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCasbackListBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindToolbar()
        bindList()
    }

    override fun onDetach() {
        binding = null
        super.onDetach()
    }

    private fun bindToolbar() {
        (activity as AppCompatActivity).run {
            setSupportActionBar(binding?.toolbarLayout?.toolbar)
            supportActionBar?.title = resources.getText(R.string.cashbacklist_screen_title)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
    }

    private fun bindList() {
        cashBackItem.add(
            listOf(
                CashBackItem(CashBack("10", 10000L, "12 Desember 2020")),
                CashBackItem(CashBack("20", 20000L, "20 Desember 2020")),
                CashBackItem(CashBack("30", 30000L, "21 Desember 2020")),
                CashBackItem(CashBack("40", 40000L, "22 Desember 2020")),
                CashBackItem(CashBack("50", 50000L, "12 Desember 2020")),
                CashBackItem(CashBack("60", 60000L, "20 Desember 2020")),
                CashBackItem(CashBack("70", 70000L, "21 Desember 2020")),
                CashBackItem(CashBack("80", 80000L, "22 Desember 2020")),
                CashBackItem(CashBack("10", 10000L, "12 Desember 2020")),
                CashBackItem(CashBack("20", 20000L, "20 Desember 2020")),
                CashBackItem(CashBack("30", 30000L, "21 Desember 2020")),
                CashBackItem(CashBack("40", 40000L, "22 Desember 2020")),
                CashBackItem(CashBack("50", 50000L, "12 Desember 2020")),
                CashBackItem(CashBack("60", 60000L, "20 Desember 2020")),
                CashBackItem(CashBack("70", 70000L, "21 Desember 2020")),
                CashBackItem(CashBack("80", 80000L, "22 Desember 2020"))
            )
        )
        binding?.rvCashback?.run {
            layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
            addItemDecoration(
                VerticalDividerDecoration(requireContext(), RecyclerView.VERTICAL)
            )
            adapter = FastAdapter.with(cashBackItem)
        }

    }

    companion object {
        fun newInstance(): CashBackListFragment {
            return CashBackListFragment()
        }
    }
}
