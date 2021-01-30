/*
 *
 * Created by Andreas on 1/27/21 1:29 PM
 * Copyright (c) 2021 . All rights reserved.
 * Last modified 1/24/21 5:30 PM
 * Github Link: https://github.com/oandrz
 * Email: oentoro.andreas@gmail.com
 *
 */

package com.dre.loyalty.features.profile.presentation.changeprofile.screen

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.dre.loyalty.R
import com.dre.loyalty.core.platform.extension.observe
import com.dre.loyalty.core.platform.extension.viewModel
import com.dre.loyalty.core.platform.functional.Event
import com.dre.loyalty.core.platform.BaseFragment
import com.dre.loyalty.core.platform.util.enumtype.ConfirmationSheetType
import com.dre.loyalty.core.view.sheet.ConfirmationSheetModal
import com.dre.loyalty.databinding.FragmentChangeProfileBinding
import com.dre.loyalty.features.profile.presentation.changeprofile.entity.DescriptionEtState
import com.dre.loyalty.features.profile.presentation.changeprofile.entity.SendButtonState

class UpdateProfileFragment : BaseFragment() {

    private var binding: FragmentChangeProfileBinding? = null

    private lateinit var vm: UpdateProfileViewModel

    private val descWatcher: TextWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        override fun afterTextChanged(s: Editable?) { vm.handleEtDescriptionChanged(s.toString()) }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent.inject(this)
        vm = viewModel(viewModelFactory) {
            observe(descInputState, ::updateDescriptionEtState)
            observe(sendButtonState, ::updateSendButtonState)
            observe(buttonSubmitClicked, ::showEditProfileSuccessModal)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentChangeProfileBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindToolbar()
        binding?.etDescription?.addTextChangedListener(descWatcher)
        bindSubmitButton()
    }

    override fun onDestroyView() {
        binding?.etDescription?.removeTextChangedListener(descWatcher)
        binding = null
        super.onDestroyView()
    }

    private fun bindToolbar() {
        (activity as AppCompatActivity).run {
            setSupportActionBar(binding?.toolbarLayout?.toolbar)
            supportActionBar?.title = getString(R.string.updateprofile_screen_title)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
    }

    private fun bindSubmitButton() {
        binding?.btnSend?.setOnClickListener {
            vm.handleSubmitButtonClicked()
        }
    }

    private fun updateDescriptionEtState(state: DescriptionEtState?) {
        if (state?.error != null && state.error != -1) {
            binding?.tvError?.run {
                visibility = View.VISIBLE
                text = getString(state.error)
            }
        } else {
            binding?.tvError?.visibility = View.GONE
        }
    }

    private fun updateSendButtonState(state: SendButtonState?) {
        binding?.btnSend?.isEnabled = state?.isEnable ?: false
    }

    private fun showEditProfileSuccessModal(event: Event<Boolean>?) {
        event?.getIfNotHandled()?.let {
            val modal = ConfirmationSheetModal.newInstance(ConfirmationSheetType.CHANGE_PROFILE_SUCCESS_SHEET)
            modal.primaryButtonClickListener = {
                requireActivity().finish()
            }
            modal.show(requireActivity().supportFragmentManager, ConfirmationSheetModal.TAG)
        }
    }

    companion object {
        fun newInstance(): UpdateProfileFragment {
            return UpdateProfileFragment()
        }
    }
}
