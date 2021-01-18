/*
 * Created by Andreas Oen on 1/18/21 4:12 PM
 * Copyright (c) 2021 . All rights reserved.
 * Last modified 1/18/21 4:12 PM
 * github: https://github.com/oandrz
 */

package com.dre.loyalty.features.invoicedetail.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dre.loyalty.R
import com.dre.loyalty.core.extension.observe
import com.dre.loyalty.core.extension.viewModel
import com.dre.loyalty.core.functional.Event
import com.dre.loyalty.core.navigation.Navigator
import com.dre.loyalty.core.platform.BaseFragment
import com.dre.loyalty.core.view.VerticalDividerDecoration
import com.dre.loyalty.databinding.FragmentInvoiceDetailBinding
import com.dre.loyalty.features.invoice.presentation.entity.Invoice
import com.dre.loyalty.features.invoicedetail.presentation.entity.VerticalFieldLabelState
import com.dre.loyalty.features.invoicedetail.presentation.item.BannerImageItem
import com.dre.loyalty.features.invoicedetail.presentation.item.DividerItem
import com.dre.loyalty.features.invoicedetail.presentation.item.VerticalFieldLabelItem
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.adapters.ItemAdapter
import javax.inject.Inject

class InvoiceDetailFragment : BaseFragment() {

    @Inject
    lateinit var navigator: Navigator

    private var binding: FragmentInvoiceDetailBinding? = null

    private val bannerImageAdapter: ItemAdapter<BannerImageItem> by lazy {
        ItemAdapter<BannerImageItem>()
    }

    private val dividerAdapter: ItemAdapter<DividerItem> by lazy {
        ItemAdapter<DividerItem>()
    }

    private val verticalFieldLabelAdapter: ItemAdapter<VerticalFieldLabelItem> by lazy {
        ItemAdapter<VerticalFieldLabelItem>()
    }

    private lateinit var vm: InvoiceDetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent.inject(this)
        vm = viewModel(viewModelFactory) {
            observe(invoiceDetail, ::renderInvoiceDetail)
            observe(photoView, ::showPhotoView)
            observe(bannerImage, ::renderBanner)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentInvoiceDetailBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val id = arguments?.getString(ARG_RECEIPT_ID).orEmpty()
        bindToolbar()
        bindList()
        vm.init(id)
    }

    override fun onDetach() {
        (activity as AppCompatActivity).setSupportActionBar(null)
        binding = null
        super.onDetach()
    }

    private fun bindToolbar() {
        (activity as AppCompatActivity).run {
            setSupportActionBar(binding?.toolbarLayout?.toolbar)
            supportActionBar?.setTitle(R.string.invoiceDetail_title)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
    }

    private fun bindList() {
        binding?.rvDetail?.run {
            addItemDecoration(VerticalDividerDecoration(context, RecyclerView.VERTICAL))
            layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
            adapter = FastAdapter.with(
                listOf(bannerImageAdapter, dividerAdapter, verticalFieldLabelAdapter)
            ).also {
                it.onClickListener = { _, _, item, _ ->
                    if (item is BannerImageItem) {
                        vm.handleBannerImageClicked(item.imageUrl)
                    }
                    true
                }
            }
        }
    }

    private fun renderInvoiceDetail(state: List<VerticalFieldLabelState>?) {
        state?.let { viewState ->
            verticalFieldLabelAdapter.set(
                viewState.map { VerticalFieldLabelItem(it) }
            )
        }
    }

    private fun showPhotoView(event: Event<String>?) {
        event?.getIfNotHandled()?.let {
            navigator.showPhotoView(requireContext(), it)
        }
    }

    private fun renderBanner(url: String?) {
        url?.let {
            bannerImageAdapter.set(listOf(BannerImageItem(it)))
        }
        dividerAdapter.set(listOf(DividerItem()))
    }

    companion object {
        private const val ARG_RECEIPT_ID = "ARG_RECEIPT_ID"

        fun newInstance(receiptId: String): InvoiceDetailFragment {
            val args = Bundle()
            args.putString(ARG_RECEIPT_ID, receiptId)
            val fragment = InvoiceDetailFragment()
            fragment.arguments = args
            return fragment
        }
    }
}
