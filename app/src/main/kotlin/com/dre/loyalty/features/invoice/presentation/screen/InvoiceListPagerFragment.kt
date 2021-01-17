/*
 * Created by Andreas Oen on 1/16/21 4:18 PM
 * Copyright (c) 2021 . All rights reserved.
 * Last modified 1/16/21 3:55 PM
 * github: https://github.com/oandrz
 */

package com.dre.loyalty.features.invoice.presentation.screen

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dre.loyalty.R
import com.dre.loyalty.core.extension.observe
import com.dre.loyalty.core.extension.viewModel
import com.dre.loyalty.core.functional.Event
import com.dre.loyalty.core.navigation.Navigator
import com.dre.loyalty.core.platform.BaseFragment
import com.dre.loyalty.core.view.VerticalSpaceDecoration
import com.dre.loyalty.core.view.sheet.SheetListModal
import com.dre.loyalty.core.view.sheet.SheetListState
import com.dre.loyalty.databinding.FragmentPagerInvoiceListBinding
import com.dre.loyalty.features.camera.CameraActivity
import com.dre.loyalty.features.invoice.presentation.entity.Invoice
import com.dre.loyalty.features.invoice.presentation.item.InvoiceListItem
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.adapters.ItemAdapter
import javax.inject.Inject

class InvoiceListPagerFragment : BaseFragment() {

    @Inject
    lateinit var navigator: Navigator

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
            observe(sortOrder, ::showSortSheet)
            observe(buttonUploadClicked, ::showCamera)
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
        bindHeader()
        val position = arguments?.getInt(ARGS_TYPE) ?: -1
        vm.init(position)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == CameraActivity.REQUEST_CODE_CAMERA) {
            val uri = data?.extras?.getString(CameraActivity.EXTRA_URI)
            uri?.let {
                navigator.showUploadInvoice(requireContext(), Uri.parse(it))
            }
        }
    }

    override fun onDetach() {
        binding = null
        super.onDetach()
    }

    private fun bindHeader() {
        binding?.btnUploadInvoice?.setOnClickListener {
            vm.handleButtonUploadClicked()
        }
        binding?.btnSort?.setOnClickListener {
            vm.handleSortClicked()
        }
    }

    private fun bindList() {
        binding?.rvListInvoice?.run {
            addItemDecoration(VerticalSpaceDecoration(resources.getDimensionPixelSize(R.dimen.space_8dp)))
            layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
            adapter = FastAdapter.with(invoiceListItemAdapter)
        }
    }

    private fun renderInvoice(invoices: List<Invoice>?) {
        invoiceListItemAdapter.set(
            invoices?.map {
                InvoiceListItem(it)
            } ?: emptyList()
        )
    }

    private fun showSortSheet(event: Event<SheetListState>?) {
        event?.getIfNotHandled()?.let { state ->
            val sheet = SheetListModal.newInstance(state)
            sheet.onItemClickListener = {
                vm.handleSelectedSort(it)
            }
            sheet.show(requireActivity().supportFragmentManager, SheetListModal.TAG)
        }
    }

    private fun showCamera(event: Event<Boolean>?) {
        event?.getIfNotHandled()?.let {
            startActivityForResult(CameraActivity.callingIntent(requireContext()), CameraActivity.REQUEST_CODE_CAMERA)
        }
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
