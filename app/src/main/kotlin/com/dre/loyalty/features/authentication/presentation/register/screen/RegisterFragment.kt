/*
 * Created by Andreas Oen on 12/27/20 10:54 AM
 * Copyright (c) 2020 . All rights reserved.
 * Last modified 12/27/20 10:49 AM
 * github: https://github.com/oandrz
 */

package com.dre.loyalty.features.authentication.presentation.register.screen

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.dre.loyalty.core.exception.Failure
import com.dre.loyalty.core.extension.observe
import com.dre.loyalty.core.extension.viewModel
import com.dre.loyalty.core.functional.Event
import com.dre.loyalty.core.navigation.Navigator
import com.dre.loyalty.core.platform.BaseFragment
import com.dre.loyalty.core.view.sheet.ConfirmationSheetModal
import com.dre.loyalty.databinding.FragmentRegisterBinding
import com.dre.loyalty.features.authentication.presentation.register.entity.RegisterButtonState
import com.dre.loyalty.features.authentication.presentation.register.entity.RegisterEmailInputState
import javax.inject.Inject

class RegisterFragment : BaseFragment() {

    @Inject
    lateinit var navigator: Navigator

    private lateinit var vm: RegisterViewModel

    private var binding: FragmentRegisterBinding? = null

    private val emailChangedListener: TextWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        }

        override fun afterTextChanged(s: Editable?) {
            vm.handleEmailTextChanged(s.toString())
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent.inject(this)
        vm = viewModel(viewModelFactory) {
            observe(navigateLogin, ::navigateToLoginPage)
            observe(navigateOtpScreen, ::navigateOtpScreen)
            observe(regisButtonState, ::updateRegisButtonState)
            observe(emailInputState, ::updateEmailInputState)
            observe(loading, ::renderLoading)
            observe(failure, ::showFailureSheet)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindToolbar()
        binding?.run {
            btnLogin.setOnClickListener {
                vm.handleLoginButtonClicked()
            }
            btnRegister.setOnClickListener {
                vm.handleRegisterButtonClicked(
                    binding?.etMail?.editText?.text.toString()
                )
            }
            etMail.editText.addTextChangedListener(emailChangedListener)
        }
    }

    override fun onDetach() {
        (activity as AppCompatActivity).setSupportActionBar(null)
        binding?.etMail?.editText?.removeTextChangedListener(emailChangedListener)
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

    private fun updateRegisButtonState(state: RegisterButtonState?) {
        binding?.btnRegister?.isEnabled = state?.enabled ?: false
    }

    private fun updateEmailInputState(state: RegisterEmailInputState?) {
        binding?.etMail?.error = if (state?.error != null && state.error != -1) {
            getString(state.error)
        } else {
            ""
        }
    }

    private fun navigateToLoginPage(flag: Event<Boolean>?) {
        flag?.getIfNotHandled()?.let {
            navigator.showLogin(requireContext())
        }
    }

    private fun navigateOtpScreen(event: Event<String>?) {
        event?.getIfNotHandled()?.let {
            navigator.showOtp(requireContext(), it)
        }
    }

    private fun renderLoading(visibility: Int?) {
        visibility?.let {
            binding?.progress?.visibility = it
        }
    }

    private fun showFailureSheet(failure: Failure?) {
        if (failure == null) return
        binding?.btnRegister?.isEnabled = true
        val sheet = getNetworkErrorSheet(failure)?.also {
            it.primaryButtonClickListener = {
                vm.handleRegisterButtonClicked(
                    binding?.etMail?.text.toString()
                )
            }
            it.secondaryButtonClickListener = {
                navigator.showSetting(requireContext())
            }
        }
        sheet?.show(requireActivity().supportFragmentManager, ConfirmationSheetModal.TAG)
    }
}
