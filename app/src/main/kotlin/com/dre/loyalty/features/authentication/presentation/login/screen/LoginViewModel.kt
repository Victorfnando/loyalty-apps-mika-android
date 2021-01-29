/*
 *  Created by Andreas Oentoro on 12/19/20 5:03 PM
 *  Copyright (c) 2020 . All rights reserved.
 *  Last modified 12/14/20 8:16 PM
 *  Github Profile: https://github.com/oandrz
 */

package com.dre.loyalty.features.authentication.presentation.login.screen

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dre.loyalty.core.platform.functional.Event
import com.dre.loyalty.core.model.AuthCertificate
import com.dre.loyalty.core.platform.BaseViewModel
import com.dre.loyalty.core.platform.util.validator.type.ValidationType
import com.dre.loyalty.features.authentication.domain.usecase.DoLoginUseCase
import com.dre.loyalty.features.authentication.presentation.login.entity.LoginButtonState
import com.dre.loyalty.features.authentication.presentation.login.entity.LoginEmailInputState
import com.dre.loyalty.features.authentication.presentation.login.entity.LoginPasswordInputState
import javax.inject.Inject

class LoginViewModel @Inject constructor(
    private val doLoginUseCase: DoLoginUseCase
) : BaseViewModel() {

    private val _navigateMain: MutableLiveData<Event<Boolean>> = MutableLiveData()
    val navigateMain: LiveData<Event<Boolean>> = _navigateMain

    private val _navigateResetPassword: MutableLiveData<Event<Boolean>> = MutableLiveData()
    val navigateResetPassword: LiveData<Event<Boolean>> = _navigateResetPassword

    private val _loginButtonState: MutableLiveData<LoginButtonState> = MutableLiveData()
    val loginButtonState: LiveData<LoginButtonState> = _loginButtonState

    private val _loginEmailInputState: MutableLiveData<LoginEmailInputState> = MutableLiveData()
    val loginEmailInputState: LiveData<LoginEmailInputState> = _loginEmailInputState

    private val _loginPasswordInputState: MutableLiveData<LoginPasswordInputState> = MutableLiveData()
    val loginPasswordInputState: LiveData<LoginPasswordInputState> = _loginPasswordInputState

    init {
        _loginEmailInputState.value = LoginEmailInputState(-1)
        _loginPasswordInputState.value = LoginPasswordInputState(-1, false)
        _loginButtonState.value = LoginButtonState(false)
    }

    fun handleEmailChanged(text: String) {
        val result = ValidationType.EMAIL.strategy.validate(text)
        if (result.isPass) {
            _loginEmailInputState.value = LoginEmailInputState(null)
        } else {
            _loginEmailInputState.value = LoginEmailInputState(result.errorMessage)
        }
        updateButtonState()
    }

    fun handlePasswordChanged(text: String) {
        val result = ValidationType.PASSWORD.strategy.validate(text)
        if (result.isPass) {
            _loginPasswordInputState.value = LoginPasswordInputState(
                null,
                _loginPasswordInputState.value?.isShowingPassword ?: false
            )
        } else {
            _loginPasswordInputState.value = LoginPasswordInputState(
                result.errorMessage,
                _loginPasswordInputState.value?.isShowingPassword ?: false
            )
        }
        updateButtonState()
    }

    fun handleShowHidePasswordClicked() {
        _loginPasswordInputState.value = _loginPasswordInputState.value?.copy(
            isShowingPassword = !(_loginPasswordInputState.value?.isShowingPassword ?: false)
        )
    }

    fun handleLoginButtonClicked(email: String, password: String) {
        _loading.value = View.VISIBLE
        _loginButtonState.value = LoginButtonState(false)
        doLoginUseCase(DoLoginUseCase.Param(email, password)) {
            it.fold(::handleFailure, ::handleSuccessLogin)
        }
    }

    fun handleTvFooterClicked() {
        _navigateResetPassword.value = Event(true)
    }

    private fun handleSuccessLogin(certificate: AuthCertificate) {
        _loading.value = View.GONE
        _loginButtonState.value = LoginButtonState(true)
        _navigateMain.value = Event(true)
    }

    private fun updateButtonState() {
        _loginButtonState.value = LoginButtonState(
            _loginEmailInputState.value?.error == null
                    && _loginPasswordInputState.value?.error == null
        )
    }

}
