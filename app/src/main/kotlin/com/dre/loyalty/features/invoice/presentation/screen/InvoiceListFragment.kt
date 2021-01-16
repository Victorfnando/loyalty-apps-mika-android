/*
 * Created by Andreas Oen on 1/16/21 4:18 PM
 * Copyright (c) 2021 . All rights reserved.
 * Last modified 1/16/21 4:17 PM
 * github: https://github.com/oandrz
 */

package com.dre.loyalty.features.invoice.presentation.screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.dre.loyalty.R
import com.dre.loyalty.core.platform.BaseFragment
import com.dre.loyalty.databinding.FragmentInvoiceListBinding
import com.dre.loyalty.features.invoice.presentation.adapter.InvoicePagingAdapter
import com.google.android.material.tabs.TabLayoutMediator

class InvoiceListFragment : BaseFragment() {

    private lateinit var titles: Array<String>

    private var binding: FragmentInvoiceListBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentInvoiceListBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        titles = resources.getStringArray(R.array.invoice_list_tab_title)
        bindToolbar()
        bindTabLayout()
    }

    override fun onDetach() {
        (activity as AppCompatActivity).setSupportActionBar(null)
        binding?.pager?.adapter = null
        binding = null
        super.onDetach()
    }

    private fun bindToolbar() {
        (activity as AppCompatActivity).run {
            setSupportActionBar(binding?.toolbarLayout?.toolbar)
            supportActionBar?.setTitle(R.string.invoice_list_title)
        }
    }

    private fun bindTabLayout() {
        binding?.run {
            pager.adapter = InvoicePagingAdapter(this@InvoiceListFragment)
            TabLayoutMediator(tabLayout, pager) { tab, position ->
                tab.text = titles[position]
            }.attach()
        }
    }

    companion object {
        fun newInstance(): InvoiceListFragment {
            return InvoiceListFragment()
        }
    }
}
