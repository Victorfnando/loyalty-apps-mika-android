/*
 * Created by Andreas Oen on 12/27/20 10:54 AM
 * Copyright (c) 2020 . All rights reserved.
 * Last modified 12/27/20 10:54 AM
 * github: https://github.com/oandrz
 */

package com.dre.loyalty.features.authentication.presentation.register.screen

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dre.loyalty.R
import com.dre.loyalty.core.platform.functional.Event
import com.dre.loyalty.core.platform.BaseViewModel
import com.dre.loyalty.core.networking.response.BasicResponse
import com.dre.loyalty.core.networking.response.LoyaltyResponse
import com.dre.loyalty.core.platform.util.validator.type.ValidationType
import com.dre.loyalty.features.authentication.data.entity.request.EmailRequest
import com.dre.loyalty.features.authentication.data.entity.response.RegisterResponse
import com.dre.loyalty.features.authentication.domain.usecase.VerifyEmailUseCase
import com.dre.loyalty.features.authentication.presentation.register.entity.RegisterButtonState
import com.dre.loyalty.features.authentication.presentation.register.entity.RegisterEmailInputState
import javax.inject.Inject

class RegisterViewModel @Inject constructor(
    private val verifyEmailUseCase: VerifyEmailUseCase
) : BaseViewModel() {

    private val _navigateLogin: MutableLiveData<Event<Boolean>> = MutableLiveData()
    val navigateLogin: LiveData<Event<Boolean>> = _navigateLogin

    private val _navigateOtpScreen: MutableLiveData<Event<String>> = MutableLiveData()
    val navigateOtpScreen: LiveData<Event<String>> = _navigateOtpScreen

    private val _regisButtonState: MutableLiveData<RegisterButtonState> = MutableLiveData()
    val regisButtonState: LiveData<RegisterButtonState> = _regisButtonState

    private val _emailInputState: MutableLiveData<RegisterEmailInputState> = MutableLiveData()
    val emailInputState: LiveData<RegisterEmailInputState> = _emailInputState

    private var email: String? = null

    init {
        _emailInputState.value = RegisterEmailInputState(-1)
        _regisButtonState.value = RegisterButtonState(false)
    }

    fun handleLoginButtonClicked() {
        _navigateLogin.value = Event(true)
    }

    fun handleRegisterButtonClicked(email: String) {
        _loading.value = View.VISIBLE
        _regisButtonState.value = RegisterButtonState(false)
        this.email = email
        verifyEmailUseCase(EmailRequest(email)) {
            it.fold(::handleFailure, ::handleVerifyEmailSuccess)
        }
    }

    fun handleEmailTextChanged(text: String) {
        val result = ValidationType.EMAIL.strategy.validate(text)
        _emailInputState.value = if (result.isPass) {
            RegisterEmailInputState(null)
        } else {
            RegisterEmailInputState(result.errorMessage)
        }
        _regisButtonState.value = RegisterButtonState(_emailInputState.value?.error == null)
    }

    private fun handleVerifyEmailSuccess(response: LoyaltyResponse<RegisterResponse>) {
        if(response.statusCode.equals("400")){
            _emailInputState.value =  RegisterEmailInputState(R.string.validation_failed_email_exist)
            _loading.value = View.GONE
            _regisButtonState.value = RegisterButtonState(true)
        } else {
            _loading.value = View.GONE
            _regisButtonState.value = RegisterButtonState(true)
            _navigateOtpScreen.value = Event(email.orEmpty())
        }
    }
}
