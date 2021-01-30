/*
 * Created by Andreas Oen on 1/3/21 2:32 PM
 * Copyright (c) 2021 . All rights reserved.
 * Last modified 1/3/21 2:32 PM
 * github: https://github.com/oandrz
 */

package com.dre.loyalty.features.cashback.presentation.screen

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dre.loyalty.R
import com.dre.loyalty.core.model.CashBack
import com.dre.loyalty.core.networking.exception.Failure
import com.dre.loyalty.core.platform.extension.observe
import com.dre.loyalty.core.platform.extension.viewModel
import com.dre.loyalty.core.platform.functional.Event
import com.dre.loyalty.core.platform.navigation.Navigator
import com.dre.loyalty.core.platform.BaseFragment
import com.dre.loyalty.core.view.VerticalDividerDecoration
import com.dre.loyalty.core.view.sheet.ConfirmationSheetModal
import com.dre.loyalty.databinding.FragmentCasbackListBinding
import com.dre.loyalty.features.camera.CameraActivity
import com.dre.loyalty.features.camera.CameraRequestType
import com.dre.loyalty.features.cashback.presentation.item.CashBackItem
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.adapters.ItemAdapter
import javax.inject.Inject

class CashBackListFragment : BaseFragment() {

    @Inject
    lateinit var navigator: Navigator

    private var binding: FragmentCasbackListBinding? = null

    private val cashBackItem: ItemAdapter<CashBackItem> by lazy {
        ItemAdapter<CashBackItem>()
    }

    private lateinit var vm: CashBackListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent.inject(this)
        vm = viewModel(viewModelFactory) {
            observe(uploadInvoiceClicked, ::showUploadInvoice)
            observe(cashBackList, ::renderCashBackList)
            observe(loading, ::renderLoading)
            observe(failure, ::showFailureSheet)
        }
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
        binding?.btnUploadInvoice?.setOnClickListener {
            vm.handleUploadInvoiceClicked()
        }
        vm.init()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK && requestCode == CameraActivity.REQUEST_CODE_CAMERA) {
            val uri = data?.extras?.getString(CameraActivity.EXTRA_URI)
            navigator.showUploadInvoice(requireContext(), Uri.parse(uri))
        }
    }

    override fun onDestroyView() {
        (activity as AppCompatActivity).setSupportActionBar(null)
        binding = null
        super.onDestroyView()
    }

    private fun bindToolbar() {
        (activity as AppCompatActivity).run {
            setSupportActionBar(binding?.toolbarLayout?.toolbar)
            supportActionBar?.title = resources.getText(R.string.cashbackList_title)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
    }

    private fun bindList() {
        binding?.rvCashback?.run {
            layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
            addItemDecoration(
                VerticalDividerDecoration(requireContext(), RecyclerView.VERTICAL)
            )
            adapter = FastAdapter.with(cashBackItem)
        }

    }

    private fun showUploadInvoice(event: Event<Boolean>?) {
        event?.getIfNotHandled()?.let {
            startActivityForResult(
                CameraActivity.callingIntent(requireContext(), CameraRequestType.CAMERA),
                CameraActivity.REQUEST_CODE_CAMERA
            )
        }
    }

    private fun renderLoading(visibility: Int?) {
        visibility?.let {
            binding?.progress?.visibility = it
        }
    }

    private fun showFailureSheet(failure: Failure?) {
        if (failure == null) return
        val sheet = getNetworkErrorSheet(failure)?.also {
            it.primaryButtonClickListener = {
                vm.refresh()
            }
            it.secondaryButtonClickListener = {
                navigator.showSetting(requireContext())
            }
        }
        sheet?.show(requireActivity().supportFragmentManager, ConfirmationSheetModal.TAG)
    }

    private fun renderCashBackList(cashBackList: List<CashBack>?) {
        cashBackItem.add(
            cashBackList?.map { CashBackItem(it) }.orEmpty()
        )
    }

    companion object {
        fun newInstance(): CashBackListFragment {
            return CashBackListFragment()
        }
    }
}
