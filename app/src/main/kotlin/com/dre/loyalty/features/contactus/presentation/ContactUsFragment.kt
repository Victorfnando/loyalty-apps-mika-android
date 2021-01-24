/*
 * Created by Andreas Oen on 1/14/21 8:16 PM
 * Copyright (c) 2021 . All rights reserved.
 * Last modified 1/14/21 8:16 PM
 * github: https://github.com/oandrz
 */

package com.dre.loyalty.features.contactus.presentation

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.dre.loyalty.R
import com.dre.loyalty.core.extension.observe
import com.dre.loyalty.core.extension.viewModel
import com.dre.loyalty.core.functional.Event
import com.dre.loyalty.core.navigation.Navigator
import com.dre.loyalty.core.platform.BaseFragment
import com.dre.loyalty.core.util.enumtype.ConfirmationSheetType.CONTACT_US_SUCCESS_SHEET
import com.dre.loyalty.core.view.sheet.ConfirmationSheetModal
import com.dre.loyalty.core.view.sheet.SheetListModal
import com.dre.loyalty.core.view.sheet.SheetListState
import com.dre.loyalty.databinding.FragmentContactUsBinding
import com.dre.loyalty.features.contactus.presentation.entity.InputMessageState
import com.dre.loyalty.features.contactus.presentation.entity.SendMessageButtonState
import javax.inject.Inject

class ContactUsFragment : BaseFragment() {

    @Inject
    lateinit var navigator: Navigator

    private var binding: FragmentContactUsBinding? = null

    private lateinit var vm: ContactUsViewModel

    private val messageTextWatcher: TextWatcher = object : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

        override fun afterTextChanged(text: Editable?) {
            vm.handleMessageChanged(text.toString())
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent.inject(this)
        vm = viewModel(viewModelFactory) {
            observe(showContactCategorySheet, ::showCategorySheet)
            observe(selectedContactCategory, ::updateCategoryEt)
            observe(phoneButtonClicked, ::showDial)
            observe(submitMessageClicked, ::showSuccessSendMessageSheet)
            observe(inputMessageState, ::updateInputMessage)
            observe(sendButtonState, ::updateSendButtonState)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentContactUsBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindToolbar()
        bindEtCategory()
        bindEtMessage()
        bindPhoneCallButton()
        bindSubmitMessageButton()
    }

    override fun onDetach() {
        binding?.etMessage?.removeTextChangedListener(messageTextWatcher)
        binding?.btnCall?.setOnClickListener(null)
        binding = null
        super.onDetach()
    }

    private fun bindEtCategory() {
        binding?.etCategory?.editText?.run {
            isFocusableInTouchMode = false
            isClickable = true
            setOnClickListener {
                vm.handleCategoryClicked()
            }
        }
    }

    private fun bindEtMessage() {
        binding?.etMessage?.addTextChangedListener(messageTextWatcher)
    }

    private fun bindPhoneCallButton() {
        binding?.btnCall?.setOnClickListener {
            vm.handlePhoneButtonClicked()
        }
    }

    private fun bindSubmitMessageButton() {
        binding?.btnSend?.setOnClickListener {
            vm.handleSubmitMessageClicked()
        }
    }

    private fun showCategorySheet(event: Event<SheetListState>?) {
        event?.getIfNotHandled()?.let { state ->
            val sheet = SheetListModal.newInstance(state).also { modal ->
                modal.onItemClickListener = {
                    vm.handleSelectedCategory(it)
                }
            }
            sheet.show(requireActivity().supportFragmentManager, SheetListModal.TAG)
        }
    }

    private fun updateCategoryEt(selected: String?) {
        selected?.let {
            binding?.etCategory?.text = it
        }
    }

    private fun bindToolbar() {
        (activity as AppCompatActivity).run {
            setSupportActionBar(binding?.toolbarLayout?.toolbar)
            supportActionBar?.setTitle(R.string.contactUs_screen_title)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
    }

    private fun showDial(event: Event<String>?) {
        event?.getIfNotHandled()?.let {
            navigator.showDialPage(requireContext(), it)
        }
    }

    private fun showSuccessSendMessageSheet(event: Event<Boolean>?) {
        event?.getIfNotHandled()?.let {
            val modal = ConfirmationSheetModal.newInstance(CONTACT_US_SUCCESS_SHEET)
            modal.primaryButtonClickListener = {
                requireActivity().finish()
            }
            modal.show(requireActivity().supportFragmentManager, ConfirmationSheetModal.TAG)
        }
    }

    private fun updateInputMessage(state: InputMessageState?) {
        binding?.tvMessageError?.visibility = View.VISIBLE
        binding?.tvMessageError?.text = if (state?.error != null && state.error != -1) {
            getString(state.error)
        } else {
            ""
        }
    }

    private fun updateSendButtonState(state: SendMessageButtonState?) {
        binding?.btnSend?.isEnabled = state?.isEnabled ?: false
    }

    companion object {
        fun newInstance(): ContactUsFragment {
            return ContactUsFragment()
        }
    }
}
