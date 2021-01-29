/*
 * Created by Andreas Oen on 1/20/21 6:32 PM
 * Copyright (c) 2021 . All rights reserved.
 * Last modified 1/20/21 6:32 PM
 * github: https://github.com/oandrz
 */

package com.dre.loyalty.features.authentication.presentation.inputpassword.screen

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.text.method.TransformationMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.dre.loyalty.core.exception.Failure
import com.dre.loyalty.core.extension.observe
import com.dre.loyalty.core.functional.Event
import com.dre.loyalty.core.model.User
import com.dre.loyalty.core.navigation.Navigator
import com.dre.loyalty.core.platform.BaseFragment
import com.dre.loyalty.core.util.enumtype.ConfirmationSheetType
import com.dre.loyalty.core.view.sheet.ConfirmationSheetModal
import com.dre.loyalty.databinding.FragmentInputPasswordBinding
import com.dre.loyalty.features.authentication.presentation.inputpassword.entity.InputPasswordState
import com.dre.loyalty.features.authentication.presentation.inputpassword.entity.InputPasswordSubmitState
import com.dre.loyalty.features.authentication.presentation.inputpassword.entity.InputPasswordTitleState
import com.dre.loyalty.features.authentication.presentation.inputpassword.enumtype.InputPasswordType
import javax.inject.Inject

class InputPasswordFragment : BaseFragment() {

    @Inject
    lateinit var navigator: Navigator

    private var binding: FragmentInputPasswordBinding? = null

    private lateinit var vm: InputPasswordViewModel

    private val passwordWatcher: TextWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        override fun afterTextChanged(s: Editable?) { vm.handleInputPasswordTextChanged(s.toString()) }
    }

    private val confirmPasswordWatcher: TextWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        override fun afterTextChanged(s: Editable?) { vm.handleConfirmInputPasswordTextChanged(s.toString()) }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent.inject(this)
        injectViewModel()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentInputPasswordBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindToolbar()
        bindEtPassword()
        bindEtConfirmPassword()
        vm.user = arguments?.getParcelable(ARGS_USER)
        vm.bindInitialValue()
        binding?.btnSubmit?.buttonClickListener = {
            vm.handleSubmitButtonClicked(binding?.etPass?.editText?.text.toString())
        }
    }

    override fun onDetach() {
        (activity as AppCompatActivity).setSupportActionBar(null)
        binding?.etPass?.editText?.removeTextChangedListener(passwordWatcher)
        binding?.etConfirmPass?.editText?.removeTextChangedListener(confirmPasswordWatcher)
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
            drawableEndClickListener = View.OnClickListener {
                vm.handleInputPasswordDrawableEndClicked()
            }
            editText.addTextChangedListener(passwordWatcher)
        }
    }

    private fun bindEtConfirmPassword() {
        binding?.etConfirmPass?.run {
            drawableEndClickListener = View.OnClickListener {
                vm.handleConfirmInputPasswordDrawableEndClicked()
            }
            editText.addTextChangedListener(confirmPasswordWatcher)
        }
    }

    private fun injectViewModel() {
        val inputPasswordType: InputPasswordType = arguments?.getSerializable(ARGS_PASSWORD_INPUT) as InputPasswordType
        vm = ViewModelProviders.of(this, viewModelFactory)[inputPasswordType.reference].apply {
            observe(titleState, ::renderTitle)
            observe(inputPasswordState, ::renderPassword)
            observe(inputPasswordConfirmationState, ::renderConfirmationPassword)
            observe(submitButtonState, ::renderSubmitButton)
            observe(submitButtonClicked, ::showSuccessSheet)
            observe(confirmationButtonClicked, ::showLoginScreen)
            observe(loading, ::renderLoading)
            observe(failure, ::showFailureSheet)
        }
    }

    private fun renderTitle(statePassword: InputPasswordTitleState?) {
        statePassword?.let {
            binding?.tvTitle?.text = getString(it.title)
        }
    }

    private fun renderPassword(statePassword: InputPasswordState?) {
        binding?.etPass?.run {
            hint = getString(statePassword?.label ?: 0)
            label = getString(statePassword?.label ?: 0)
            editText.transformationMethod = getInputTransformation(statePassword)
            editText.setSelection(editText.text?.length ?: 0)
            error = if (statePassword?.error != null && statePassword.error != -1) {
                getString(statePassword.error)
            } else {
                null
            }
        }
    }

    private fun renderConfirmationPassword(statePassword: InputPasswordState?) {
        binding?.etConfirmPass?.run {
            hint = getString(statePassword?.label ?: 0)
            label = getString(statePassword?.label ?: 0)
            editText.transformationMethod = getInputTransformation(statePassword)
            editText.setSelection(editText.text?.length ?: 0)
            error = if (statePassword?.error != null && statePassword.error != -1) {
                getString(statePassword.error)
            } else {
                null
            }
        }
    }

    private fun renderSubmitButton(statePassword: InputPasswordSubmitState?) {
        binding?.btnSubmit?.run {
            tvFooterVisibility = View.GONE
            buttonText = getString(statePassword?.text ?: -1)
            isButtonEnabled = statePassword?.isEnabled ?: false
        }
    }

    private fun getInputTransformation(statePassword: InputPasswordState?): TransformationMethod {
        return if (statePassword?.isShowing == true) {
            HideReturnsTransformationMethod.getInstance()
        } else {
            PasswordTransformationMethod.getInstance()
        }
    }

    private fun showSuccessSheet(event: Event<ConfirmationSheetType>?) {
        event?.getIfNotHandled()?.let {
            val modal = ConfirmationSheetModal.newInstance(it)
            modal.primaryButtonClickListener = {
                vm.handleConfirmationSheetButtonClicked()
            }
            modal.show(requireActivity().supportFragmentManager, ConfirmationSheetModal.TAG)
        }
    }

    private fun showLoginScreen(event: Event<Boolean>?) {
        event?.getIfNotHandled()?.let {
            navigator.showLogin(requireContext(), true)
        }
    }

    private fun renderLoading(visibility: Int?) {
        visibility?.let {
            binding?.progress?.visibility = it
        }
    }

    private fun showFailureSheet(failure: Failure?) {
        if (failure == null) return
        binding?.btnSubmit?.isButtonEnabled = true
        val sheet = getNetworkErrorSheet(failure)?.also {
            it.primaryButtonClickListener = {
                vm.handleSubmitButtonClicked(binding?.etPass?.editText?.text.toString())
            }
            it.secondaryButtonClickListener = {
                navigator.showSetting(requireContext())
            }
        }
        sheet?.show(requireActivity().supportFragmentManager, ConfirmationSheetModal.TAG)
    }

    companion object {
        private const val ARGS_PASSWORD_INPUT = "ARGS_PASSWORD_INPUT"
        private const val ARGS_USER = "ARGS_USER"

        fun newInstance(user: User, passwordType: InputPasswordType): InputPasswordFragment {
            val args = Bundle()
            args.putSerializable(ARGS_PASSWORD_INPUT, passwordType)
            args.putParcelable(ARGS_USER, user)
            val fragment = InputPasswordFragment()
            fragment.arguments = args
            return fragment
        }
    }
}
