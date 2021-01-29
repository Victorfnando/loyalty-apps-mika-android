/*
 *
 * Created by Andreas on 1/27/21 1:47 PM
 * Copyright (c) 2021 . All rights reserved.
 * Last modified 1/21/21 11:07 AM
 * Github Link: https://github.com/oandrz
 * Email: oentoro.andreas@gmail.com
 *
 */

package com.dre.loyalty.features.authentication.presentation.otp.screen

import android.os.Bundle
import android.os.CountDownTimer
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProviders
import com.dre.loyalty.R
import com.dre.loyalty.core.networking.exception.Failure
import com.dre.loyalty.core.platform.extension.observe
import com.dre.loyalty.core.platform.functional.Event
import com.dre.loyalty.core.model.User
import com.dre.loyalty.core.platform.navigation.Navigator
import com.dre.loyalty.core.platform.BaseFragment
import com.dre.loyalty.core.view.sheet.ConfirmationSheetModal
import com.dre.loyalty.databinding.FragmentOtpBinding
import com.dre.loyalty.features.authentication.presentation.inputpassword.enumtype.InputPasswordType
import com.dre.loyalty.features.authentication.presentation.otp.enumType.OtpType
import javax.inject.Inject

class OtpFragment : BaseFragment() {

    @Inject
    lateinit var navigator: Navigator

    private var binding: FragmentOtpBinding? = null

    private lateinit var vm: OtpViewModel

    private val verificationCodeWatcher: TextWatcher = object : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

        override fun afterTextChanged(text: Editable?) {
            vm.handleCodeTextChanged(text.toString())
        }
    }

    private val timer = object : CountDownTimer(EMAIL_VERIFICATION_TIME_INTERVAL, COUNT_DOWN_INTERVAL) {
        override fun onTick(millisUntilFinished: Long) {
            val seconds = (millisUntilFinished / COUNT_DOWN_INTERVAL)
            binding?.btnResend?.text = getString(R.string.otp_button_format_resend, seconds)
        }

        override fun onFinish() {
            binding?.btnResend?.run {
                text = getString(R.string.otp_button_reSend)
                isEnabled = true
                setTextColor(ContextCompat.getColor(requireContext(), R.color.mainColor))
            }
        }
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
        binding = FragmentOtpBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindToolbar()
        bindPinInput()
        bindButtonResend()
        val email = arguments?.getString(ARG_EMAIL).orEmpty()
        binding?.tvDescription?.text = getString(R.string.otp_format_mailDesc, email)
        vm.init(email)
    }

    override fun onDestroyView() {
        timer.cancel()
        timer.onFinish()
        binding?.etPin?.removeTextChangedListener(verificationCodeWatcher)
        (activity as AppCompatActivity).setSupportActionBar(null)
        binding = null
        super.onDestroyView()
    }

    private fun injectViewModel() {
        val type: OtpType = arguments?.getSerializable(ARG_TYPE) as OtpType
        vm = ViewModelProviders.of(this, viewModelFactory)[type.reference].apply {
            observe(startCountDown, ::startCountDownIfNeeded)
            observe(loading, ::renderLoading)
            observe(failure, ::showFailureSheet)
            observe(navigateDetailForm, ::showDetailForm)
            observe(navigateResetPassword, ::showInputResetPassword)
            observe(successVerifyEmail, ::showToastSuccessSendEmailVerification)
        }
    }

    private fun bindToolbar() {
        (activity as AppCompatActivity).run {
            setSupportActionBar(binding?.toolbarLayout?.toolbar)
            supportActionBar?.setDisplayShowTitleEnabled(false)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
    }

    private fun bindPinInput() {
        binding?.etPin?.addTextChangedListener(verificationCodeWatcher)
    }

    private fun bindButtonResend() {
        binding?.btnResend?.setOnClickListener {
            vm.handleResendButtonClicked()
        }
    }

    private fun startCountDownIfNeeded(event: Event<Boolean>?) {
        event?.getIfNotHandled()?.let {
            binding?.btnResend?.run {
                isEnabled = false
                setTextColor(ContextCompat.getColor(requireContext(), R.color.gray_3))
            }
            timer.start()
        }
    }

    private fun renderLoading(visibility: Int?) {
        visibility?.let {
            binding?.progress?.visibility = it
        }
    }

    private fun showFailureSheet(failure: Failure?) {
        if (failure == null) return
        val sheet = getNetworkErrorSheet(failure)?.also {
            it.primaryButtonClickListener = {
                vm.handleCodeTextChanged(binding?.etPin?.text.toString())
            }
            it.secondaryButtonClickListener = {
                navigator.showSetting(requireContext())
            }
        }
        sheet?.show(requireActivity().supportFragmentManager, ConfirmationSheetModal.TAG)
    }

    private fun showDetailForm(event: Event<String>?) {
        event?.getIfNotHandled()?.let {
            navigator.showUserDetailForm(requireContext(), it)
            requireActivity().finish()
        }
    }

    private fun showInputResetPassword(event: Event<User>?) {
        event?.getIfNotHandled()?.let {
            navigator.showInputPasswordScreen(requireContext(), it, InputPasswordType.RESET)
            requireActivity().finish()
        }
    }

    private fun showToastSuccessSendEmailVerification(event: Event<Boolean>?) {
        event?.getIfNotHandled()?.let {
            Toast.makeText(requireContext(), R.string.otp_title, Toast.LENGTH_SHORT).show()
        }
    }

    companion object {
        private const val EMAIL_VERIFICATION_TIME_INTERVAL = 120000L
        private const val COUNT_DOWN_INTERVAL = 1000L
        private const val ARG_EMAIL = "ARG_EMAIL"
        private const val ARG_TYPE = "ARG_TYPE"

        fun newInstance(email: String, type: OtpType): OtpFragment {
            return OtpFragment().also {
                val bundle = Bundle()
                bundle.putString(ARG_EMAIL, email)
                bundle.putSerializable(ARG_TYPE, type)
                it.arguments = bundle
            }
        }
    }
}
