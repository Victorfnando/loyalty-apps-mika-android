/*
 * Created by Andreas Oen on 12/27/20 10:47 AM
 * Copyright (c) 2020 . All rights reserved.
 * Last modified 12/27/20 10:38 AM
 * github: https://github.com/oandrz
 */

package com.dre.loyalty.features.login.presentation.ui

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.dre.loyalty.core.extension.observe
import com.dre.loyalty.core.extension.viewModel
import com.dre.loyalty.core.navigation.Navigator
import com.dre.loyalty.core.platform.BaseFragment
import com.dre.loyalty.databinding.FragmentLoginBinding
import com.dre.loyalty.features.login.presentation.entity.LoginButtonState
import com.dre.loyalty.features.login.presentation.entity.LoginPhoneInputState
import javax.inject.Inject

class LoginFragment : BaseFragment() {

    @Inject
    lateinit var navigator: Navigator

    private lateinit var vm: LoginViewModel

    private var binding: FragmentLoginBinding? = null

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
            observe(navigateMain) { _ ->
                activity?.let { navigator.showPin(it) }
            }
            observe(loginButtonState, ::updateLoginButtonState)
            observe(loginPhoneInputState, ::updateLoginPhoneState)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding?.root!!
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindToolbar()
        binding?.run {
            btnLogin.setOnClickListener {
                vm.handleLoginButtonClicked()
            }
            etPhone.editText.addTextChangedListener(phoneChangeListener)
        }
    }

    private fun bindToolbar() {
        (activity as AppCompatActivity).run {
            setSupportActionBar(binding?.toolbarLayout?.toolbar)
            supportActionBar?.setDisplayShowTitleEnabled(false)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
    }

    private fun updateLoginButtonState(state: LoginButtonState?) {
        binding?.btnLogin?.isEnabled = state?.enabled ?: false
    }

    private fun updateLoginPhoneState(state: LoginPhoneInputState?) {
        binding?.etPhone?.error = state?.error
    }

    override fun onDetach() {
        binding?.etPhone?.editText?.removeTextChangedListener(phoneChangeListener)
        binding = null
        super.onDetach()
    }
}
