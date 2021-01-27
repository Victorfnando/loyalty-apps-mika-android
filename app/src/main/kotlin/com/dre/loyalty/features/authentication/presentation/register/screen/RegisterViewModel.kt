/*
 * Created by Andreas Oen on 12/27/20 10:54 AM
 * Copyright (c) 2020 . All rights reserved.
 * Last modified 12/27/20 10:54 AM
 * github: https://github.com/oandrz
 */

package com.dre.loyalty.features.authentication.presentation.register.screen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dre.loyalty.core.functional.Event
import com.dre.loyalty.core.platform.BaseViewModel
import com.dre.loyalty.core.util.validator.type.ValidationType
import com.dre.loyalty.features.authentication.presentation.register.entity.RegisterButtonState
import com.dre.loyalty.features.authentication.presentation.register.entity.RegisterEmailInputState
import javax.inject.Inject

class RegisterViewModel @Inject constructor() : BaseViewModel() {

    private val _navigateLogin: MutableLiveData<Event<Boolean>> = MutableLiveData()
    val navigateLogin: LiveData<Event<Boolean>> = _navigateLogin

    private val _navigateOtpScreen: MutableLiveData<Event<Boolean>> = MutableLiveData()
    val navigateOtpScreen: LiveData<Event<Boolean>> = _navigateOtpScreen

    private val _regisButtonState: MutableLiveData<RegisterButtonState> = MutableLiveData()
    val regisButtonState: LiveData<RegisterButtonState> = _regisButtonState

    private val _emailInputState: MutableLiveData<RegisterEmailInputState> = MutableLiveData()
    val emailInputState: LiveData<RegisterEmailInputState> = _emailInputState

    init {
        _emailInputState.value = RegisterEmailInputState(-1)
        _regisButtonState.value = RegisterButtonState(false)
    }

    fun handleLoginButtonClicked() {
        _navigateLogin.value = Event(true)
    }

    fun handleRegisterButtonClicked() {
        _navigateOtpScreen.value = Event(true)
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
}
