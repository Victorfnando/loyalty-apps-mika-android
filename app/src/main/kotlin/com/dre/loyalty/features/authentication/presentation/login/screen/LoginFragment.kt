/*
 * Created by Andreas Oen on 12/27/20 10:47 AM
 * Copyright (c) 2020 . All rights reserved.
 * Last modified 12/27/20 10:38 AM
 * github: https://github.com/oandrz
 */

package com.dre.loyalty.features.authentication.presentation.login.screen

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.text.method.TransformationMethod
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.HtmlCompat
import com.dre.loyalty.R
import com.dre.loyalty.core.networking.exception.Failure
import com.dre.loyalty.core.platform.extension.observe
import com.dre.loyalty.core.platform.extension.viewModel
import com.dre.loyalty.core.platform.functional.Event
import com.dre.loyalty.core.platform.navigation.Navigator
import com.dre.loyalty.core.platform.BaseFragment
import com.dre.loyalty.core.view.sheet.ConfirmationSheetModal
import com.dre.loyalty.databinding.FragmentLoginBinding
import com.dre.loyalty.features.authentication.presentation.login.entity.LoginButtonState
import com.dre.loyalty.features.authentication.presentation.login.entity.LoginEmailInputState
import com.dre.loyalty.features.authentication.presentation.login.entity.LoginPasswordInputState
import javax.inject.Inject

class LoginFragment : BaseFragment() {

    @Inject
    lateinit var navigator: Navigator

    private lateinit var vm: LoginViewModel

    private var binding: FragmentLoginBinding? = null

    private val emailChangeListener: TextWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        }

        override fun afterTextChanged(s: Editable?) {
            vm.handleEmailChanged(s.toString())
        }
    }

    private val passwordChangeListener: TextWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        }

        override fun afterTextChanged(s: Editable?) {
            vm.handlePasswordChanged(s.toString())
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent.inject(this)
        vm = viewModel(viewModelFactory) {
            observe(navigateMain, ::showHomePage)
            observe(navigateResetPassword, ::showResetPassword)
            observe(loginButtonState, ::updateLoginButtonState)
            observe(loginEmailInputState, ::updateEmailInput)
            observe(loginPasswordInputState, ::updatePasswordInput)
            observe(loading, ::renderLoading)
            observe(failure, ::showFailureSheet)
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
        bindStickyButton()
        binding?.etMail?.editText?.addTextChangedListener(emailChangeListener)
        bindEtPassword()
    }

    override fun onDetach() {
        (activity as AppCompatActivity).setSupportActionBar(null)
        binding?.run {
            etMail.editText.removeTextChangedListener(emailChangeListener)
            etPass.editText.removeTextChangedListener(passwordChangeListener)
        }
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

    private fun bindEtPassword() {
        binding?.etPass?.run {
            editText.addTextChangedListener(passwordChangeListener)
            drawableEndClickListener = View.OnClickListener {
                vm.handleShowHidePasswordClicked()
            }
        }
    }

    private fun bindStickyButton() {
        binding?.btnLoginSticky?.run {
            setFooterGravity(Gravity.CENTER)
            tvFooterClickListener = {
                vm.handleTvFooterClicked()
            }
            tvFooterText = HtmlCompat.fromHtml(getString(R.string.login_screen_label_reset), HtmlCompat.FROM_HTML_MODE_LEGACY)
            buttonClickListener = {
                vm.handleLoginButtonClicked(
                    binding?.etMail?.text.toString(),
                    binding?.etPass?.text.toString()
                )
            }
        }
    }

    private fun updateLoginButtonState(state: LoginButtonState?) {
        binding?.btnLoginSticky?.isButtonEnabled = state?.enabled ?: false
    }

    private fun updateEmailInput(state: LoginEmailInputState?) {
        val errorMessage = state?.error
        binding?.etMail?.error = if (errorMessage != null && state.error != -1) {
            getString(errorMessage)
        } else {
            ""
        }
    }

    private fun updatePasswordInput(state: LoginPasswordInputState?) {
        binding?.etPass?.run {
            editText.let {
                it.transformationMethod = getInputTransformation(state)
                it.setSelection(it.text?.length ?: 0)
            }
            val errorMessage = state?.error
            error = if (errorMessage != null && state.error != -1) {
                getString(errorMessage)
            } else {
                ""
            }
        }
    }

    private fun getInputTransformation(state: LoginPasswordInputState?): TransformationMethod {
        return if (state?.isShowingPassword == true) {
            HideReturnsTransformationMethod.getInstance()
        } else {
            PasswordTransformationMethod.getInstance()
        }
    }

    private fun showHomePage(event: Event<Boolean>?) {
        event?.getIfNotHandled()?.let {
            navigator.showHome(requireContext())
        }
    }

    private fun showResetPassword(event: Event<Boolean>?) {
        event?.getIfNotHandled()?.let {
            navigator.showResetPasswordScreen(requireContext())
        }
    }

    private fun renderLoading(visibility: Int?) {
        visibility?.let {
            binding?.progress?.visibility = it
        }
    }

    private fun showFailureSheet(failure: Failure?) {
        if (failure == null) return
        binding?.btnLoginSticky?.isButtonEnabled = true
        val sheet = getNetworkErrorSheet(failure)?.also {
            it.primaryButtonClickListener = {
                vm.handleLoginButtonClicked(
                    binding?.etMail?.text.toString(),
                    binding?.etPass?.text.toString()
                )
            }
            it.secondaryButtonClickListener = {
                navigator.showSetting(requireContext())
            }
        }
        sheet?.show(requireActivity().supportFragmentManager, ConfirmationSheetModal.TAG)
    }

    companion object {
        fun newInstance(): LoginFragment {
            return LoginFragment()
        }
    }
}
