/*
 *
 * Created by Andreas on 1/27/21 1:19 PM
 * Copyright (c) 2021 . All rights reserved.
 * Last modified 1/24/21 3:24 PM
 * Github Link: https://github.com/oandrz
 * Email: oentoro.andreas@gmail.com
 *
 */

package com.dre.loyalty.features.authentication.presentation.updatepassword.screen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dre.loyalty.R
import com.dre.loyalty.core.functional.Event
import com.dre.loyalty.core.platform.BaseViewModel
import com.dre.loyalty.core.util.validator.type.ValidationType
import com.dre.loyalty.features.authentication.presentation.updatepassword.entity.PasswordInputState
import com.dre.loyalty.features.authentication.presentation.updatepassword.entity.SubmitButtonState
import javax.inject.Inject

class UpdatePasswordViewModel @Inject constructor() : BaseViewModel() {

    private val _oldPasswordInputState: MutableLiveData<PasswordInputState> = MutableLiveData()
    val oldPasswordInputState: LiveData<PasswordInputState> = _oldPasswordInputState

    private val _newPasswordInputState: MutableLiveData<PasswordInputState> = MutableLiveData()
    val newPasswordInputState: LiveData<PasswordInputState> = _newPasswordInputState

    private val _confirmPasswordInputState: MutableLiveData<PasswordInputState> = MutableLiveData()
    val confirmPasswordInputState: LiveData<PasswordInputState> = _confirmPasswordInputState

    private val _submitButtonState: MutableLiveData<SubmitButtonState> = MutableLiveData()
    val submitButtonState: LiveData<SubmitButtonState> = _submitButtonState

    private val _submitButtonClicked: MutableLiveData<Event<Boolean>> = MutableLiveData()
    val submitButtonClicked: LiveData<Event<Boolean>> = _submitButtonClicked

    private val _tvFooterClicked: MutableLiveData<Event<Boolean>> = MutableLiveData()
    val tvFooterClicked: LiveData<Event<Boolean>> = _tvFooterClicked

    private var password: Triple<String?, String?, String?> = Triple(null, null, null)

    init {
        _oldPasswordInputState.value = PasswordInputState(false, -1)
        _newPasswordInputState.value = PasswordInputState(false, -1)
        _confirmPasswordInputState.value = PasswordInputState(false, -1)
    }

    fun handleOldPasswordHidePasswordClicked() {
        _oldPasswordInputState.value = _oldPasswordInputState.value?.copy(
            isShowPassword = !(_oldPasswordInputState.value?.isShowPassword ?: false)
        )
    }

    fun handleNewPasswordHidePasswordClicked() {
        _newPasswordInputState.value = _newPasswordInputState.value?.copy(
            isShowPassword = !(_newPasswordInputState.value?.isShowPassword ?: false)
        )
    }

    fun handleConfirmPasswordHidePasswordClicked() {
        _confirmPasswordInputState.value = _confirmPasswordInputState.value?.copy(
            isShowPassword = !(_confirmPasswordInputState.value?.isShowPassword ?: false)
        )
    }

    fun handleOldPasswordChanged(text: String) {
        password = password.copy(first = text)
        val result = ValidationType.PASSWORD.strategy.validate(text)
        if (result.isPass) {
            _oldPasswordInputState.value = PasswordInputState(
                _oldPasswordInputState.value?.isShowPassword ?: false,
                null
            )
        } else {
            _oldPasswordInputState.value = PasswordInputState(
                _oldPasswordInputState.value?.isShowPassword ?: false,
                result.errorMessage
            )
        }
        checkButtonState()
    }

    fun handleNewPassword(text: String) {
        password = password.copy(second = text)
        val result = ValidationType.PASSWORD.strategy.validate(text)
        if (password.first == text) {
            _newPasswordInputState.value = PasswordInputState(
                _newPasswordInputState.value?.isShowPassword ?: false,
                R.string.validation_failed_password
            )
            return
        }
        if (result.isPass) {
            _newPasswordInputState.value = PasswordInputState(
                _newPasswordInputState.value?.isShowPassword ?: false,
                null
            )
        } else {
            _newPasswordInputState.value = PasswordInputState(
                _newPasswordInputState.value?.isShowPassword ?: false,
                result.errorMessage
            )
        }
        checkButtonState()
    }

    fun handleConfirmPassword(text: String) {
        password = password.copy(third = text)
        val result = ValidationType.PASSWORD.strategy.validate(text)
        if (password.second != text) {
            _confirmPasswordInputState.value = PasswordInputState(
                _confirmPasswordInputState.value?.isShowPassword ?: false,
                R.string.validation_failed_password_confirm
            )
            return
        }
        if (result.isPass) {
            _confirmPasswordInputState.value = PasswordInputState(
                _confirmPasswordInputState.value?.isShowPassword ?: false,
                null
            )
        } else {
            _confirmPasswordInputState.value = PasswordInputState(
                _confirmPasswordInputState.value?.isShowPassword ?: false,
                result.errorMessage
            )
        }
        checkButtonState()
    }

    fun handleSubmitButtonClicked() {
        _submitButtonClicked.value = Event(true)
    }

    fun handleFooterTextClicked() {
        _tvFooterClicked.value = Event(true)
    }

    private fun checkButtonState() {
        _submitButtonState.value = SubmitButtonState(isValidForm())
    }

    private fun isValidForm(): Boolean = _oldPasswordInputState.value?.error == null
            && _newPasswordInputState.value?.error == null
            && _confirmPasswordInputState.value?.error == null
}
