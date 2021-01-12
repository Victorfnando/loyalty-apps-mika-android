/*
 * Created by Andreas Oen on 1/6/21 7:26 PM
 * Copyright (c) 2021 . All rights reserved.
 * Last modified 1/6/21 7:26 PM
 * github: https://github.com/oandrz
 */

package com.dre.loyalty.features.uploadinvoice.presentation

import android.net.Uri
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.HtmlCompat
import com.bumptech.glide.Glide
import com.dre.loyalty.R
import com.dre.loyalty.core.extension.observe
import com.dre.loyalty.core.extension.viewModel
import com.dre.loyalty.core.functional.Event
import com.dre.loyalty.core.navigation.Navigator
import com.dre.loyalty.core.platform.BaseFragment
import com.dre.loyalty.databinding.FragmentUploadInvoiceBinding
import com.dre.loyalty.features.uploadinvoice.presentation.entity.HospitalBranchState
import com.dre.loyalty.features.uploadinvoice.presentation.entity.TotalAmountState
import com.dre.loyalty.features.uploadinvoice.presentation.entity.UploadButtonState
import com.dre.loyalty.features.uploadinvoice.presentation.sheet.BranchListSheetModal
import com.dre.loyalty.features.userdetailform.presentation.dialog.DatePickerDialogFragment
import javax.inject.Inject

class UploadInvoiceFragment : BaseFragment() {

    @Inject
    lateinit var navigator: Navigator

    private lateinit var vm: UploadInvoiceViewModel

    private var binding: FragmentUploadInvoiceBinding? = null

    private val totalAmountWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        }

        override fun afterTextChanged(s: Editable?) {
            vm.handleTextAmountChanged(s.toString())
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent.inject(this)
        vm = viewModel(viewModelFactory) {
            observe(showDateSelector, ::showDateDialog)
            observe(showBranchListSheet, ::showBranchList)
            observe(selectedBranch, ::renderSelectedBranch)
            observe(selectedDate, ::renderSelectedDate)
            observe(totalAmountInputState, ::updateTotalAmountState)
            observe(uploadButtonState, ::updateUploadButtonState)
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
        bindTvTnc()
        binding?.etAmount?.editText?.addTextChangedListener(totalAmountWatcher)
    }

    override fun onDetach() {
        binding?.etAmount?.editText?.removeTextChangedListener(totalAmountWatcher)
        binding = null
        super.onDetach()
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

    private fun bindTvTnc() {
        binding?.stickyButton?.tvFooterText = HtmlCompat.fromHtml(
            getString(R.string.upload_invoice_screen_label_tnc),
            HtmlCompat.FROM_HTML_MODE_LEGACY
        )
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

    private fun renderSelectedDate(selected: String?) {
        selected?.let {
            binding?.etTransactionDate?.text = it
        }
    }

    private fun renderSelectedBranch(selected: String?) {
        selected?.let {
            binding?.etFormBranch?.text = it
        }
    }

    private fun updateTotalAmountState(state: TotalAmountState?) {
        if (state?.error == -1) return
        binding?.etAmount?.error = state?.error?.let {
            getString(it)
        } ?:  ""
    }

    private fun updateUploadButtonState(state: UploadButtonState?) {
        binding?.stickyButton?.isButtonEnabled = state?.isEnabled ?: false
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
