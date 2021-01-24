/*
 * Created by Andreas Oen on 1/12/21 8:28 PM
 * Copyright (c) 2021 . All rights reserved.
 * Last modified 1/12/21 8:28 PM
 * github: https://github.com/oandrz
 */

package com.dre.loyalty.features.updatepassword.presentation

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
import com.dre.loyalty.core.extension.observe
import com.dre.loyalty.core.extension.viewModel
import com.dre.loyalty.core.functional.Event
import com.dre.loyalty.core.navigation.Navigator
import com.dre.loyalty.core.platform.BaseFragment
import com.dre.loyalty.core.util.enumtype.ConfirmationSheetType
import com.dre.loyalty.core.util.enumtype.ConfirmationSheetType.UPDATE_PASSWORD_SUCCESS_SHEET
import com.dre.loyalty.core.view.sheet.ConfirmationSheetModal
import com.dre.loyalty.databinding.FragmentUpdatePasswordBinding
import com.dre.loyalty.features.passwordinput.presentation.enumtype.InputPasswordType
import com.dre.loyalty.features.updatepassword.presentation.entity.PasswordInputState
import com.dre.loyalty.features.updatepassword.presentation.entity.SubmitButtonState
import javax.inject.Inject

class UpdatePasswordFragment : BaseFragment() {

    @Inject
    lateinit var navigator: Navigator

    private var binding: FragmentUpdatePasswordBinding? = null

    private lateinit var vm: UpdatePasswordViewModel

    private val oldPasswordWatcher: TextWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        override fun afterTextChanged(s: Editable?) { vm.handleOldPasswordChanged(s.toString()) }
    }

    private val confirmPasswordWatcher: TextWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        override fun afterTextChanged(s: Editable?) { vm.handleConfirmPassword(s.toString()) }
    }

    private val newPasswordWatcher: TextWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        override fun afterTextChanged(s: Editable?) { vm.handleNewPassword(s.toString()) }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent.inject(this)
        vm = viewModel(viewModelFactory) {
            observe(oldPasswordInputState, ::updateOldPasswordState)
            observe(newPasswordInputState, ::updateNewPasswordState)
            observe(confirmPasswordInputState, ::updateConfirmPasswordState)
            observe(submitButtonState, ::updateSubmitButtonState)
            observe(submitButtonClicked, ::showSuccessUpdatePasswordSheet)
            observe(tvFooterClicked, ::navigateResetPassword)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUpdatePasswordBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindToolbar()
        bindEtOldPass()
        bindEtNewPass()
        bindEtConfirmPass()
        bindFooter()
    }

    override fun onDetach() {
        binding?.etOldPass?.editText?.removeTextChangedListener(oldPasswordWatcher)
        binding?.etNewPass?.editText?.removeTextChangedListener(newPasswordWatcher)
        binding?.etNewPassConfirm?.editText?.removeTextChangedListener(confirmPasswordWatcher)
        binding = null
        super.onDetach()
    }

    private fun bindEtOldPass() {
        binding?.etOldPass?.run {
            editText.addTextChangedListener(oldPasswordWatcher)
            drawableEndClickListener = View.OnClickListener {
                vm.handleOldPasswordHidePasswordClicked()
            }
        }
    }

    private fun bindEtNewPass() {
        binding?.etNewPass?.run {
            editText.addTextChangedListener(newPasswordWatcher)
            drawableEndClickListener = View.OnClickListener {
                vm.handleNewPasswordHidePasswordClicked()
            }
        }
    }

    private fun bindEtConfirmPass() {
        binding?.etNewPassConfirm?.run {
            editText.addTextChangedListener(confirmPasswordWatcher)
            drawableEndClickListener = View.OnClickListener {
                vm.handleConfirmPasswordHidePasswordClicked()
            }
        }
    }

    private fun bindToolbar() {
        (activity as AppCompatActivity).run {
            setSupportActionBar(binding?.toolbarLayout?.toolbar)
            supportActionBar?.setDisplayShowTitleEnabled(false)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
    }

    private fun updateOldPasswordState(state: PasswordInputState?) {
        binding?.etOldPass?.editText?.run {
            transformationMethod = getInputTransformation(state)
            setSelection(text?.length ?: 0)
        }
        if (state?.error != null && state.error != -1) {
            binding?.etOldPass?.error = getString(state.error)
        } else {
            binding?.etOldPass?.error = ""
        }
    }

    private fun updateNewPasswordState(state: PasswordInputState?) {
        binding?.etNewPass?.editText?.run {
            transformationMethod = getInputTransformation(state)
            setSelection(text?.length ?: 0)
        }
        if (state?.error != null && state.error != -1) {
            binding?.etNewPass?.error = getString(state.error)
        } else {
            binding?.etNewPass?.error = ""
        }
    }

    private fun updateConfirmPasswordState(state: PasswordInputState?) {
        binding?.etNewPassConfirm?.editText?.run {
            transformationMethod = getInputTransformation(state)
            setSelection(text?.length ?: 0)
        }
        if (state?.error != null && state.error != -1) {
            binding?.etNewPassConfirm?.error = getString(state.error)
        } else {
            binding?.etNewPassConfirm?.error = ""
        }
    }

    private fun updateSubmitButtonState(state: SubmitButtonState?) {
        binding?.stickyButton?.isButtonEnabled = state?.isEnable ?: false
    }

    private fun getInputTransformation(state: PasswordInputState?): TransformationMethod {
        return if (state?.isShowPassword == true) {
            HideReturnsTransformationMethod.getInstance()
        } else {
            PasswordTransformationMethod.getInstance()
        }
    }

    private fun bindFooter() {
        binding?.stickyButton?.run {
            setFooterGravity(Gravity.CENTER)
            tvFooterText = HtmlCompat.fromHtml(
                getString(R.string.updatePassword_label_reset),
                HtmlCompat.FROM_HTML_MODE_LEGACY
            )
            tvFooterClickListener = {
                vm.handleFooterTextClicked()
            }
            buttonClickListener = {
                vm.handleSubmitButtonClicked()
            }
        }
    }

    private fun navigateResetPassword(event: Event<Boolean>?) {
        event?.getIfNotHandled()?.let {
            navigator.showResetPasswordScreen(requireContext())
        }
    }

    private fun showSuccessUpdatePasswordSheet(event: Event<Boolean>?) {
        event?.getIfNotHandled()?.let {
            val modal = ConfirmationSheetModal.newInstance(UPDATE_PASSWORD_SUCCESS_SHEET)
            modal.primaryButtonClickListener = {
                requireActivity().finish()
            }
            modal.show(requireActivity().supportFragmentManager, ConfirmationSheetModal.TAG)
        }
    }

    companion object {
        fun newInstance(): UpdatePasswordFragment {
            return UpdatePasswordFragment()
        }
    }
}
