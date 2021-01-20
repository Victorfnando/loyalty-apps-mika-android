/*
 * Created by Andreas Oen on 1/20/21 6:32 PM
 * Copyright (c) 2021 . All rights reserved.
 * Last modified 1/20/21 6:32 PM
 * github: https://github.com/oandrz
 */

package com.dre.loyalty.features.passwordinput.presentation.screen

import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.text.method.TransformationMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.dre.loyalty.core.extension.observe
import com.dre.loyalty.core.platform.BaseFragment
import com.dre.loyalty.databinding.FragmentInputPasswordBinding
import com.dre.loyalty.features.passwordinput.presentation.entity.InputPasswordPasswordState
import com.dre.loyalty.features.passwordinput.presentation.entity.InputPasswordSubmitState
import com.dre.loyalty.features.passwordinput.presentation.entity.InputPasswordTitleState
import com.dre.loyalty.features.passwordinput.presentation.enumtype.InputPasswordType

class InputPasswordFragment : BaseFragment() {

    private var binding: FragmentInputPasswordBinding? = null

    private lateinit var vm: InputPasswordViewModel

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
        vm.bindInitialValue()
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

    private fun bindEtPassword() {
        binding?.etPass?.drawableEndClickListener = View.OnClickListener {
            vm.handleInputPasswordDrawableEndClicked()
        }
    }

    private fun bindEtConfirmPassword() {
        binding?.etConfirmPass?.drawableEndClickListener = View.OnClickListener {
            vm.handleConfirmInputPasswordDrawableEndClicked()
        }
    }

    private fun injectViewModel() {
        val inputPasswordType: InputPasswordType = arguments?.getSerializable(ARGS_PASSWORD_INPUT) as InputPasswordType
        vm = ViewModelProviders.of(this, viewModelFactory)[inputPasswordType.reference].apply {
            observe(titleState, ::renderTitle)
            observe(inputPasswordState, ::renderPassword)
            observe(inputPasswordConfirmationState, ::renderConfirmationPassword)
            observe(submitButtonState, ::renderSubmitButton)
        }
    }

    private fun renderTitle(statePassword: InputPasswordTitleState?) {
        statePassword?.let {
            binding?.tvTitle?.text = getString(it.title)
        }
    }

    private fun renderPassword(statePassword: InputPasswordPasswordState?) {
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

    private fun renderConfirmationPassword(statePassword: InputPasswordPasswordState?) {
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
        }
    }

    private fun getInputTransformation(statePassword: InputPasswordPasswordState?): TransformationMethod {
        return if (statePassword?.isShowing == true) {
            HideReturnsTransformationMethod.getInstance()
        } else {
            PasswordTransformationMethod.getInstance()
        }
    }

    companion object {
        private const val ARGS_PASSWORD_INPUT = "ARGS_PASSWORD_INPUT"

        fun newInstance(passwordType: InputPasswordType): InputPasswordFragment {
            val args = Bundle()
            args.putSerializable(ARGS_PASSWORD_INPUT, passwordType)
            val fragment = InputPasswordFragment()
            fragment.arguments = args
            return fragment
        }
    }
}
