/*
 *  Created by Andreas Oentoro on 12/19/20 5:03 PM
 *  Copyright (c) 2020 . All rights reserved.
 *  Last modified 12/14/20 8:16 PM
 *  Github Profile: https://github.com/oandrz
 */

package com.dre.loyalty.features.login.presentation.ui

import androidx.core.text.isDigitsOnly
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dre.loyalty.core.platform.BaseViewModel
import com.dre.loyalty.features.login.presentation.entity.LoginButtonState
import com.dre.loyalty.features.login.presentation.entity.LoginPhoneInputState
import javax.inject.Inject

class LoginViewModel @Inject constructor() : BaseViewModel() {

    private val _navigateMain: MutableLiveData<Boolean> = MutableLiveData()
    val navigateMain: LiveData<Boolean> = _navigateMain

    private val _loginButtonState: MutableLiveData<LoginButtonState> = MutableLiveData()
    val loginButtonState: LiveData<LoginButtonState> = _loginButtonState

    private val _loginPhoneInputState: MutableLiveData<LoginPhoneInputState> = MutableLiveData()
    val loginPhoneInputState: LiveData<LoginPhoneInputState> = _loginPhoneInputState

    init {
        _loginPhoneInputState.value = LoginPhoneInputState(null)
        _loginButtonState.value = LoginButtonState(false)
    }

    fun handleLoginButtonClicked() {
        _navigateMain.value = true
    }

    fun handleTextChanged(text: String) {
        if (text.isEmpty()) {
            _loginPhoneInputState.value = LoginPhoneInputState(null)
            _loginButtonState.value = LoginButtonState(false)
            return
        }
        if (!text.isDigitsOnly()) {
            _loginPhoneInputState.value = LoginPhoneInputState("Number Only")
            _loginButtonState.value = LoginButtonState(false)
            return
        }
        _loginPhoneInputState.value = LoginPhoneInputState(null)
        _loginButtonState.value = LoginButtonState(true)
    }
}
