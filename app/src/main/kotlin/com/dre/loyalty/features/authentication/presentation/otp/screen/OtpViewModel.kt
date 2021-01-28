/*
 *
 * Created by Andreas on 1/28/21 7:22 PM
 * Copyright (c) 2021 . All rights reserved.
 * Last modified 1/28/21 7:22 PM
 * Github Link: https://github.com/oandrz
 * Email: oentoro.andreas@gmail.com
 *
 */

package com.dre.loyalty.features.authentication.presentation.otp.screen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dre.loyalty.core.functional.Event
import com.dre.loyalty.core.platform.BaseViewModel
import com.dre.loyalty.core.response.BasicResponse
import com.dre.loyalty.features.authentication.data.entity.request.EmailRequest
import com.dre.loyalty.features.authentication.data.entity.request.VerifyCodeRequest
import com.dre.loyalty.features.authentication.domain.usecase.VerifyCodeUseCase
import com.dre.loyalty.features.authentication.domain.usecase.VerifyEmailUseCase
import javax.inject.Inject

private const val OTP_CODE_LENGTH = 6
class OtpViewModel @Inject constructor(
    private val verifyCodeUseCase: VerifyCodeUseCase,
    private val verifyEmailUseCase: VerifyEmailUseCase
) : BaseViewModel() {

    private val _startCountDown: MutableLiveData<Event<Boolean>> = MutableLiveData()
    val startCountDown: LiveData<Event<Boolean>> = _startCountDown

    private val _navigateUserDetailForm: MutableLiveData<Event<String>> = MutableLiveData()
    val navigateUserDetailForm: LiveData<Event<String>> = _navigateUserDetailForm

    private val _successVerifyEmail: MutableLiveData<Event<Boolean>> = MutableLiveData()
    val successVerifyEmail: LiveData<Event<Boolean>> = _successVerifyEmail

    private lateinit var email: String

    fun init(email: String) {
        this.email = email
        _startCountDown.value = Event(true)
    }

    fun handleResendButtonClicked() {
        _startCountDown.value = Event(true)
        verifyEmailUseCase(EmailRequest(email)) {
            it.fold(::handleFailure, ::handleSuccessVerifyEmail)
        }
    }

    fun handleCodeTextChanged(text: String) {
        if (text.length != OTP_CODE_LENGTH) return
        verifyCodeUseCase(VerifyCodeRequest(email, text)) {
            it.fold(::handleFailure, ::handleSuccessVerifyCode)
        }
    }

    private fun handleSuccessVerifyCode(response: BasicResponse) {
        _navigateUserDetailForm.value = Event(email)
    }

    private fun handleSuccessVerifyEmail(response: BasicResponse) {
        _successVerifyEmail.value = Event(true)
    }
}