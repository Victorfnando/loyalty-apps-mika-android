/*
 * Created by Andreas Oen on 12/26/20 4:31 PM
 * Copyright (c) 2020 . All rights reserved.
 * Last modified 12/26/20 4:31 PM
 * github: https://github.com/oandrz
 */

package com.dre.loyalty.features.resetpassword.presentation

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.dre.loyalty.core.extension.observe
import com.dre.loyalty.core.extension.viewModel
import com.dre.loyalty.core.functional.Event
import com.dre.loyalty.core.navigation.Navigator
import com.dre.loyalty.core.platform.BaseFragment
import com.dre.loyalty.databinding.FragmentResetPasswordBinding
import com.dre.loyalty.features.passwordinput.presentation.enumtype.InputPasswordType
import com.dre.loyalty.features.resetpassword.presentation.entity.ResetPinButtonState
import com.dre.loyalty.features.resetpassword.presentation.entity.ResetPinPhoneNumberInputState
import javax.inject.Inject

class ResetPasswordFragment : BaseFragment() {

    @Inject
    lateinit var navigator: Navigator

    private lateinit var vm: ResetPasswordViewModel

    private var binding: FragmentResetPasswordBinding? = null

    private val phoneChangeListener: TextWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        }

        override fun afterTextChanged(s: Editable?) {
            vm.handleTextChanged(s.toString())
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent.inject(this)
        vm = viewModel(viewModelFactory) {
            observe(resetPinButtonState, ::updateResetPinButtonState)
            observe(mailInputState, ::updateEmailInputState)
            observe(navigatePasswordInput, ::showPasswordInput)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentResetPasswordBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindToolbar()
        binding?.etMail?.editText?.addTextChangedListener(phoneChangeListener)
    }

    override fun onDetach() {
        (activity as AppCompatActivity).setSupportActionBar(null)
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

    private fun updateResetPinButtonState(state: ResetPinButtonState?) {
        binding?.btnReset?.run {
            isEnabled = state?.isEnable ?: false
            setOnClickListener {
                vm.handleButtonClicked(binding?.etMail?.text.toString())
            }
        }
    }

    private fun updateEmailInputState(state: ResetPinPhoneNumberInputState?) {
        binding?.etMail?.error = if (state?.error != null && state.error != -1) {
            getString(state.error)
        } else {
            ""
        }
    }

    private fun showPasswordInput(event: Event<Boolean>?) {
        event?.getIfNotHandled()?.let {
            navigator.showInputPasswordScreen(requireContext(), InputPasswordType.RESET)
        }
    }

    companion object {
        fun newInstance(): ResetPasswordFragment {
            return ResetPasswordFragment()
        }
    }
}