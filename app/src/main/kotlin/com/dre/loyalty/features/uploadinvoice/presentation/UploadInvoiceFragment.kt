/*
 * Created by Andreas Oen on 1/6/21 7:26 PM
 * Copyright (c) 2021 . All rights reserved.
 * Last modified 1/6/21 7:26 PM
 * github: https://github.com/oandrz
 */

package com.dre.loyalty.features.uploadinvoice.presentation

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.dre.loyalty.core.extension.observe
import com.dre.loyalty.core.extension.viewModel
import com.dre.loyalty.core.functional.Event
import com.dre.loyalty.core.navigation.Navigator
import com.dre.loyalty.core.platform.BaseFragment
import com.dre.loyalty.databinding.FragmentUploadInvoiceBinding
import com.dre.loyalty.features.uploadinvoice.presentation.entity.HospitalBranchState
import com.dre.loyalty.features.uploadinvoice.presentation.sheet.BranchListSheetModal
import com.dre.loyalty.features.userdetailform.presentation.dialog.DatePickerDialogFragment
import javax.inject.Inject

class UploadInvoiceFragment : BaseFragment() {

    @Inject
    lateinit var navigator: Navigator

    private lateinit var vm: UploadInvoiceViewModel

    private var binding: FragmentUploadInvoiceBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent.inject(this)
        vm = viewModel(viewModelFactory) {
            observe(showDateSelector, ::showDateDialog)
            observe(showBranchListSheet, ::showBranchList)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUploadInvoiceBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.getString(ARGS_IMAGE_URI)?.let {
            Glide.with(this)
                .load(Uri.parse(it))
                .into(binding!!.ivInvoice)
        }
        bindToolbar()
        bindEtFormBranch()
        bindEtDate()
    }

    private fun bindToolbar() {
        (activity as AppCompatActivity).run {
            setSupportActionBar(binding?.toolbarLayout?.toolbar)
            supportActionBar?.setDisplayShowTitleEnabled(false)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
    }

    private fun bindEtFormBranch() {
        binding?.etFormBranch?.editText?.run {
            isFocusableInTouchMode = false
            isClickable = true
            setOnClickListener {
                vm.handleFormBranchEtClicked()
            }
        }
    }

    private fun bindEtDate() {
        binding?.etTransactionDate?.editText?.run {
            isFocusableInTouchMode = false
            isClickable = true
            setOnClickListener {
                vm.handleTransactionDateEtClicked()
            }
        }
    }

    private fun showDateDialog(event: Event<String?>?) {
        event?.getIfNotHandled().let {
            val dialog = DatePickerDialogFragment.newInstance(it)
            dialog.listener = { selectedDate ->
                vm.handleSelectedDate(selectedDate)
            }
            dialog.show(requireActivity().supportFragmentManager, DatePickerDialogFragment.TAG)
        }
    }

    private fun showBranchList(event: Event<HospitalBranchState>?) {
        event?.getIfNotHandled()?.let {
            val sheet = BranchListSheetModal.newInstance(it)
            sheet.onItemClickListener = { selectedItem ->
                vm.handleSelectedBranch(selectedItem)
            }
            sheet.show(requireActivity().supportFragmentManager, BranchListSheetModal.TAG)
        }
    }

    companion object {
        private const val ARGS_IMAGE_URI = "ARGS_IMAGE_URI"

        fun newInstance(imageUri: String): UploadInvoiceFragment {
            val args = Bundle()
            args.putString(ARGS_IMAGE_URI, imageUri)
            val fragment = UploadInvoiceFragment()
            fragment.arguments = args
            return fragment
        }
    }
}
