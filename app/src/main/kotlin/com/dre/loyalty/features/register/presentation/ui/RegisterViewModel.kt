/*
 * Created by Andreas Oen on 12/27/20 10:54 AM
 * Copyright (c) 2020 . All rights reserved.
 * Last modified 12/27/20 10:54 AM
 * github: https://github.com/oandrz
 */

package com.dre.loyalty.features.register.presentation.ui

import androidx.core.text.isDigitsOnly
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dre.loyalty.core.functional.Event
import com.dre.loyalty.core.platform.BaseViewModel
import com.dre.loyalty.features.register.presentation.entity.RegisterButtonState
import com.dre.loyalty.features.register.presentation.entity.RegisterPhoneInputState
import javax.inject.Inject

class RegisterViewModel @Inject constructor() : BaseViewModel() {

    private val _navigateLogin: MutableLiveData<Event<Boolean>> = MutableLiveData()
    val navigateLogin: LiveData<Event<Boolean>> = _navigateLogin

    private val _navigateOtpScreen: MutableLiveData<Event<Boolean>> = MutableLiveData()
    val navigateOtpScreen: LiveData<Event<Boolean>> = _navigateOtpScreen

    private val _regisButtonState: MutableLiveData<RegisterButtonState> = MutableLiveData()
    val regisButtonState: LiveData<RegisterButtonState> = _regisButtonState

    private val _regisPhoneInputState: MutableLiveData<RegisterPhoneInputState> = MutableLiveData()
    val regisPhoneInputState: LiveData<RegisterPhoneInputState> = _regisPhoneInputState

    init {
        _regisPhoneInputState.value = RegisterPhoneInputState(null)
        _regisButtonState.value = RegisterButtonState(false)
    }

    fun handleLoginButtonClicked() {
        _navigateLogin.value = Event(true)
    }

    fun handleRegisterButtonClicked() {
        _navigateOtpScreen.value = Event(true)
    }

    fun handlePhoneNumberTextChanged(text: String) {
        if (text.isEmpty()) {
            _regisPhoneInputState.value = RegisterPhoneInputState(null)
            _regisButtonState.value = RegisterButtonState(false)
            return
        }
        if (!text.isDigitsOnly()) {
            _regisPhoneInputState.value = RegisterPhoneInputState("Number Only")
            _regisButtonState.value = RegisterButtonState(false)
            return
        }
        _regisPhoneInputState.value = RegisterPhoneInputState(null)
        _regisButtonState.value = RegisterButtonState(true)
    }
}
