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
import com.dre.loyalty.core.extension.observe
import com.dre.loyalty.core.extension.viewModel
import com.dre.loyalty.core.functional.Event
import com.dre.loyalty.core.model.CashBack
import com.dre.loyalty.core.model.Hospital
import com.dre.loyalty.core.navigation.Navigator
import com.dre.loyalty.core.platform.BaseFragment
import com.dre.loyalty.core.view.VerticalDividerDecoration
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
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK && requestCode == CameraActivity.REQUEST_CODE_CAMERA) {
            val uri = data?.extras?.getString(CameraActivity.EXTRA_URI)
            navigator.showUploadInvoice(requireContext(), Uri.parse(uri))
        }
    }

    override fun onDetach() {
        (activity as AppCompatActivity).setSupportActionBar(null)
        binding = null
        super.onDetach()
    }

    private fun bindToolbar() {
        (activity as AppCompatActivity).run {
            setSupportActionBar(binding?.toolbarLayout?.toolbar)
            supportActionBar?.title = resources.getText(R.string.cashbackList_title)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
    }

    private fun bindList() {
        cashBackItem.add(
            listOf(
                CashBackItem(CashBack("10", 10000L, "12 Desember 2020", Hospital("", ""))),
                CashBackItem(CashBack("20", 20000L, "20 Desember 2020", Hospital("", ""))),
                CashBackItem(CashBack("30", 30000L, "21 Desember 2020", Hospital("", ""))),
                CashBackItem(CashBack("40", 40000L, "22 Desember 2020", Hospital("", ""))),
                CashBackItem(CashBack("50", 50000L, "12 Desember 2020", Hospital("", ""))),
                CashBackItem(CashBack("60", 60000L, "20 Desember 2020", Hospital("", ""))),
                CashBackItem(CashBack("70", 70000L, "21 Desember 2020", Hospital("", ""))),
                CashBackItem(CashBack("80", 80000L, "22 Desember 2020", Hospital("", ""))),
                CashBackItem(CashBack("10", 10000L, "12 Desember 2020", Hospital("", ""))),
                CashBackItem(CashBack("20", 20000L, "20 Desember 2020", Hospital("", ""))),
                CashBackItem(CashBack("30", 30000L, "21 Desember 2020", Hospital("", ""))),
                CashBackItem(CashBack("40", 40000L, "22 Desember 2020", Hospital("", ""))),
                CashBackItem(CashBack("50", 50000L, "12 Desember 2020", Hospital("", ""))),
                CashBackItem(CashBack("60", 60000L, "20 Desember 2020", Hospital("", ""))),
                CashBackItem(CashBack("70", 70000L, "21 Desember 2020", Hospital("", ""))),
                CashBackItem(CashBack("80", 80000L, "22 Desember 2020", Hospital("", "")))
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

    private fun showUploadInvoice(event: Event<Boolean>?) {
        event?.getIfNotHandled()?.let {
            startActivityForResult(
                CameraActivity.callingIntent(requireContext(), CameraRequestType.CAMERA),
                CameraActivity.REQUEST_CODE_CAMERA
            )
        }
    }

    companion object {
        fun newInstance(): CashBackListFragment {
            return CashBackListFragment()
        }
    }
}
