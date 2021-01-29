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
import com.dre.loyalty.R
import com.dre.loyalty.core.exception.Failure
import com.dre.loyalty.core.extension.observe
import com.dre.loyalty.core.extension.viewModel
import com.dre.loyalty.core.functional.Event
import com.dre.loyalty.core.navigation.Navigator
import com.dre.loyalty.core.platform.BaseFragment
import com.dre.loyalty.core.view.sheet.ConfirmationSheetModal
import com.dre.loyalty.databinding.FragmentOtpBinding
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent.inject(this)
        vm = viewModel(viewModelFactory) {
            observe(startCountDown, ::startCountDownIfNeeded)
            observe(loading, ::renderLoading)
            observe(failure, ::showFailureSheet)
            observe(navigateUserDetailForm, ::showUserDetailScreen)
            observe(successVerifyEmail, ::showToastSuccessSendEmailVerification)
        }
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

    override fun onDetach() {
        binding?.etPin?.removeTextChangedListener(verificationCodeWatcher)
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
            val timer = object : CountDownTimer(EMAIL_VERIFICATION_TIME_INTERVAL, COUNT_DOWN_INTERVAL) {
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

    private fun showUserDetailScreen(event: Event<String>?) {
        event?.getIfNotHandled()?.let {
            navigator.showUserDetailForm(requireContext(), it)
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

        fun newInstance(email: String): OtpFragment {
            return OtpFragment().also {
                val bundle = Bundle()
                bundle.putString(ARG_EMAIL, email)
                it.arguments = bundle
            }
        }
    }
}
