/*
 *
 * Created by Andreas on 1/27/21 1:21 PM
 * Copyright (c) 2021 . All rights reserved.
 * Last modified 1/23/21 8:43 PM
 * Github Link: https://github.com/oandrz
 * Email: oentoro.andreas@gmail.com
 *
 */

package com.dre.loyalty.features.authentication.presentation.resetpassword.screen

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dre.loyalty.core.platform.functional.Event
import com.dre.loyalty.core.platform.BaseViewModel
import com.dre.loyalty.core.platform.util.validator.type.ValidationType
import com.dre.loyalty.features.authentication.domain.usecase.DoCheckMailForgotPasswordUseCase
import com.dre.loyalty.features.authentication.presentation.resetpassword.entity.ResetPinButtonState
import com.dre.loyalty.features.authentication.presentation.resetpassword.entity.ResetPinPhoneNumberInputState
import javax.inject.Inject

class ResetPasswordViewModel @Inject constructor(
    private val checkMailUseCase: DoCheckMailForgotPasswordUseCase
) : BaseViewModel() {

    private val _resetPinButtonState: MutableLiveData<ResetPinButtonState> = MutableLiveData()
    val resetPinButtonState: LiveData<ResetPinButtonState> = _resetPinButtonState

    private val _mailInputState: MutableLiveData<ResetPinPhoneNumberInputState> = MutableLiveData()
    val mailInputState: LiveData<ResetPinPhoneNumberInputState> = _mailInputState

    private val _navigateOtp: MutableLiveData<Event<String>> = MutableLiveData()
    val navigateOtp: LiveData<Event<String>> = _navigateOtp

    private var email: String? = null

    init {
        _resetPinButtonState.value = ResetPinButtonState(false)
        _mailInputState.value = ResetPinPhoneNumberInputState(null)
    }

    fun handleTextChanged(text: String) {
        val result = ValidationType.EMAIL.strategy.validate(text)
        if (result.isPass) {
            _mailInputState.value = ResetPinPhoneNumberInputState(null)
        } else {
            _mailInputState.value = ResetPinPhoneNumberInputState(result.errorMessage)
        }
        _resetPinButtonState.value = ResetPinButtonState(_mailInputState.value?.error == null)
    }

    fun handleButtonClicked(text: String) {
        email = text
        _loading.value = View.VISIBLE
        _resetPinButtonState.value = _resetPinButtonState.value?.copy(isEnable = false)
        checkMailUseCase(DoCheckMailForgotPasswordUseCase.Param(text)) {
            it.fold(::handleFailure, ::handleSuccessVerifyEmail)
        }
    }

    private fun handleSuccessVerifyEmail(attempt: Int) {
        _loading.value = View.GONE
        _resetPinButtonState.value = _resetPinButtonState.value?.copy(isEnable = true)
        email?.let {
            _navigateOtp.value = Event(it)
        }
    }
}
