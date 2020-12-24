/*
 *  Created by Andreas Oentoro on 12/19/20 5:03 PM
 *  Copyright (c) 2020 . All rights reserved.
 *  Last modified 12/19/20 5:03 PM
 *  Github Profile: https://github.com/oandrz
 */

package com.dre.loyalty.features.login.presentation

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.isDigitsOnly
import com.dre.loyalty.core.extension.observe
import com.dre.loyalty.core.extension.viewModel
import com.dre.loyalty.core.navigation.Navigator
import com.dre.loyalty.core.platform.BaseFragment
import com.dre.loyalty.databinding.FragmentAuthFormBinding
import javax.inject.Inject

class LoginFragment : BaseFragment() {

    @Inject
    lateinit var navigator: Navigator

    private lateinit var vm: LoginViewModel

    private var binding: FragmentAuthFormBinding? = null

    private val phoneChangeListener: TextWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        }

        override fun afterTextChanged(s: Editable?) {
            if (s.toString().length > 2) {
                if (s?.substring(0, 3) != "62" && s?.firstOrNull() != '0') {
                    binding?.etPhone?.error = "Not Correct Phone Number Format"
                    return
                }
            }
            if (s?.isDigitsOnly() == false) {
                binding?.etPhone?.error = "Not Correct Phone Number Format"
                return
            }
            binding?.etPhone?.error = ""
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent.inject(this)
        vm = viewModel(viewModelFactory) {
            observe(navigateMain) { _ ->
                activity?.let { navigator.showPin(it) }
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAuthFormBinding.inflate(inflater, container, false)
        return binding?.root!!
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).setSupportActionBar(binding?.toolbarLayout?.toolbar)
        binding?.run {
            btnLogin.setOnClickListener {
                vm.handleLoginButtonClicked()
            }
            etPhone.editText.addTextChangedListener(phoneChangeListener)
        }
    }

    override fun onDetach() {
        super.onDetach()
        binding?.etPhone?.editText?.removeTextChangedListener(phoneChangeListener)
        binding = null
    }
}
