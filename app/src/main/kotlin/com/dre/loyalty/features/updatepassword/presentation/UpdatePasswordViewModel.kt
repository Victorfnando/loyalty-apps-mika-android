/*
 * Created by Andreas Oen on 1/12/21 9:02 PM
 * Copyright (c) 2021 . All rights reserved.
 * Last modified 1/12/21 9:02 PM
 * github: https://github.com/oandrz
 */

package com.dre.loyalty.features.updatepassword.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dre.loyalty.R
import com.dre.loyalty.core.platform.BaseViewModel
import com.dre.loyalty.core.util.validator.type.ValidationType
import com.dre.loyalty.features.updatepassword.presentation.entity.PasswordInputState
import com.dre.loyalty.features.updatepassword.presentation.entity.SubmitButtonState
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

    private var password: Triple<String?, String?, String?> = Triple(null, null, null)

    init {
        _oldPasswordInputState.value = PasswordInputState(true, -1)
        _newPasswordInputState.value = PasswordInputState(true, -1)
        _confirmPasswordInputState.value = PasswordInputState(true, -1)
    }

    fun handleOldPasswordHidePasswordClicked() {
        if (_oldPasswordInputState.value?.isHidePassword == true) {
            _oldPasswordInputState.value = PasswordInputState(false)
        } else {
            _oldPasswordInputState.value = PasswordInputState(true)
        }
    }

    fun handleNewPasswordHidePasswordClicked() {
        if (_newPasswordInputState.value?.isHidePassword == true) {
            _newPasswordInputState.value = PasswordInputState(false)
        } else {
            _newPasswordInputState.value = PasswordInputState(true)
        }
    }

    fun handleConfirmPasswordHidePasswordClicked() {
        if (_confirmPasswordInputState.value?.isHidePassword == true) {
            _confirmPasswordInputState.value = PasswordInputState(false)
        } else {
            _confirmPasswordInputState.value = PasswordInputState(true)
        }
    }

    fun handleOldPasswordChanged(text: String) {
        password = password.copy(first = text)
        val result = ValidationType.PASSWORD.strategy.validate(text)
        if (result.isPass) {
            _oldPasswordInputState.value = PasswordInputState(
                _oldPasswordInputState.value?.isHidePassword ?: false,
                null
            )
        } else {
            _oldPasswordInputState.value = PasswordInputState(
                _oldPasswordInputState.value?.isHidePassword ?: false,
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
                _oldPasswordInputState.value?.isHidePassword ?: false,
                R.string.validation_failed_password
            )
            return
        }
        if (result.isPass) {
            _newPasswordInputState.value = PasswordInputState(
                _oldPasswordInputState.value?.isHidePassword ?: false,
                null
            )
        } else {
            _newPasswordInputState.value = PasswordInputState(
                _oldPasswordInputState.value?.isHidePassword ?: false,
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
                _oldPasswordInputState.value?.isHidePassword ?: false,
                R.string.validation_failed_password_confirm
            )
            return
        }
        if (result.isPass) {
            _confirmPasswordInputState.value = PasswordInputState(
                _oldPasswordInputState.value?.isHidePassword ?: false,
                null
            )
        } else {
            _confirmPasswordInputState.value = PasswordInputState(
                _oldPasswordInputState.value?.isHidePassword ?: false,
                result.errorMessage
            )
        }
        checkButtonState()
    }

    private fun checkButtonState() {
        _submitButtonState.value = SubmitButtonState(isValidForm())
    }

    private fun isValidForm(): Boolean = _oldPasswordInputState.value?.error == null
            && _newPasswordInputState.value?.error == null
            && _confirmPasswordInputState.value?.error == null
}
