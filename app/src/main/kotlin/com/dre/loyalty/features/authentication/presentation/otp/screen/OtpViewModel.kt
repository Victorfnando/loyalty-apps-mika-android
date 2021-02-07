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
import com.dre.loyalty.core.platform.functional.Event
import com.dre.loyalty.core.model.User
import com.dre.loyalty.core.platform.BaseViewModel
import com.dre.loyalty.core.networking.response.BasicResponse
import com.dre.loyalty.core.networking.response.LoyaltyResponse
import com.dre.loyalty.features.authentication.data.entity.request.EmailRequest
import com.dre.loyalty.features.authentication.data.entity.response.RegisterResponse
import com.dre.loyalty.features.authentication.domain.entity.OtpCode
import com.dre.loyalty.features.authentication.domain.usecase.VerifyEmailUseCase
import com.dre.loyalty.features.authentication.presentation.otp.enumType.OtpType

private const val OTP_CODE_LENGTH = 6
abstract class OtpViewModel constructor(
    private val verifyEmailUseCase: VerifyEmailUseCase
) : BaseViewModel() {

    private val _startCountDown: MutableLiveData<Event<Boolean>> = MutableLiveData()
    val startCountDown: LiveData<Event<Boolean>> = _startCountDown

    protected val _navigateDetailForm: MutableLiveData<Event<String>> = MutableLiveData()
    val navigateDetailForm: LiveData<Event<String>> = _navigateDetailForm

    protected val _navigateResetPassword: MutableLiveData<Event<User>> = MutableLiveData()
    val navigateResetPassword: LiveData<Event<User>> = _navigateResetPassword

    private val _successVerifyEmail: MutableLiveData<Event<Boolean>> = MutableLiveData()
    val successVerifyEmail: LiveData<Event<Boolean>> = _successVerifyEmail

    protected lateinit var email: String

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
        verifyCode(OtpCode(email, text))
    }

    private fun handleSuccessVerifyEmail(response: LoyaltyResponse<RegisterResponse>) {
        _successVerifyEmail.value = Event(true)
    }

    abstract fun getType(): OtpType

    abstract fun verifyCode(otpCode: OtpCode)
}