/*
 * Created by Andreas Oen on 1/16/21 4:18 PM
 * Copyright (c) 2021 . All rights reserved.
 * Last modified 1/16/21 3:55 PM
 * github: https://github.com/oandrz
 */

package com.dre.loyalty.features.invoice.presentation.screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dre.loyalty.R
import com.dre.loyalty.core.extension.observe
import com.dre.loyalty.core.extension.viewModel
import com.dre.loyalty.core.platform.BaseFragment
import com.dre.loyalty.core.view.VerticalSpaceDecoration
import com.dre.loyalty.databinding.FragmentPagerInvoiceListBinding
import com.dre.loyalty.features.invoice.presentation.entity.Invoice
import com.dre.loyalty.features.invoice.presentation.item.InvoiceListItem
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.adapters.ItemAdapter

class InvoiceListPagerFragment : BaseFragment() {

    private var binding: FragmentPagerInvoiceListBinding? = null

    private lateinit var vm: InvoiceListPagerViewModel

    private val invoiceListItemAdapter: ItemAdapter<InvoiceListItem> by lazy {
        ItemAdapter<InvoiceListItem>()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent.inject(this)
        vm = viewModel(viewModelFactory) {
            observe(invoiceList, ::renderInvoice)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPagerInvoiceListBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindList()
        val position = arguments?.getInt(ARGS_TYPE) ?: -1
        vm.init(position)
    }

    override fun onDetach() {
        binding = null
        super.onDetach()
    }

    private fun bindList() {
        binding?.rvListInvoice?.run {
            addItemDecoration(VerticalSpaceDecoration(resources.getDimensionPixelSize(R.dimen.space_8dp)))
            layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
            adapter = FastAdapter.with(invoiceListItemAdapter)
        }
    }

    private fun renderInvoice(invoices: List<Invoice>?) {
        invoiceListItemAdapter.add(
            invoices?.map {
                InvoiceListItem(it)
            } ?: emptyList()
        )
    }
    
    companion object {

        private const val ARGS_TYPE = "ARGS_TYPE"

        fun newInstance(position: Int): InvoiceListPagerFragment {
            val args = Bundle()
            args.putInt(ARGS_TYPE, position)
            val fragment = InvoiceListPagerFragment()
            fragment.arguments = args
            return fragment
        }
    }
}
