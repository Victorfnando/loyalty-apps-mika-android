/*
 * Created by Andreas Oen on 12/27/20 10:54 AM
 * Copyright (c) 2020 . All rights reserved.
 * Last modified 12/27/20 10:49 AM
 * github: https://github.com/oandrz
 */

package com.dre.loyalty.features.register.presentation.ui

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
import com.dre.loyalty.databinding.FragmentRegisterBinding
import com.dre.loyalty.features.register.presentation.entity.RegisterButtonState
import com.dre.loyalty.features.register.presentation.entity.RegisterPhoneInputState
import javax.inject.Inject

class RegisterFragment : BaseFragment() {

    @Inject
    lateinit var navigator: Navigator

    private lateinit var vm: RegisterViewModel

    private var binding: FragmentRegisterBinding? = null

    private val phoneChangeListener: TextWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        }

        override fun afterTextChanged(s: Editable?) {
            vm.handlePhoneNumberTextChanged(s.toString())
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent.inject(this)
        vm = viewModel(viewModelFactory) {
            observe(navigateLogin, ::navigateToLoginPage)
            observe(navigateOtpScreen, ::navigateOtpScreen)
            observe(regisButtonState, ::updateRegisButtonState)
            observe(regisPhoneInputState, ::updatePhoneInputState)
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
        (activity as AppCompatActivity).run {
            setSupportActionBar(binding?.toolbarLayout?.toolbar)
            supportActionBar?.setDisplayShowTitleEnabled(false)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
        binding?.run {
            setToastDescription()
            btnLogin.setOnClickListener {
                vm.handleLoginButtonClicked()
            }
            btnRegister.setOnClickListener {
                vm.handleRegisterButtonClicked()
            }
            etPhone.editText.addTextChangedListener(phoneChangeListener)
        }
    }

    private fun setToastDescription() {
        binding?.run {
            tvDescription.fromHtml(getString(R.string.register_screen_toast_desc))
        }
    }

    private fun updateRegisButtonState(state: RegisterButtonState?) {
        binding?.btnRegister?.isEnabled = state?.enabled ?: false
    }

    private fun updatePhoneInputState(state: RegisterPhoneInputState?) {
        binding?.etPhone?.error = state?.error
    }

    private fun navigateToLoginPage(flag: Event<Boolean>?) {
        flag?.getIfNotHandled()?.let {
            navigator.showLogin(requireContext())
        }
    }

    private fun navigateOtpScreen(flag: Event<Boolean>?) {
        flag?.getIfNotHandled()?.let {
            navigator.showOtp(requireContext())
        }
    }

    override fun onDetach() {
        (activity as AppCompatActivity).setSupportActionBar(null)
        binding?.etPhone?.editText?.removeTextChangedListener(phoneChangeListener)
        binding = null
        super.onDetach()
    }
}
