/*
 * Created by Andreas Oen on 1/20/21 7:01 PM
 * Copyright (c) 2021 . All rights reserved.
 * Last modified 1/20/21 7:01 PM
 * github: https://github.com/oandrz
 */

package com.dre.loyalty.features.authentication.presentation.inputpassword.screen

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dre.loyalty.R
import com.dre.loyalty.core.platform.functional.Event
import com.dre.loyalty.core.model.User
import com.dre.loyalty.core.platform.BaseViewModel
import com.dre.loyalty.core.platform.util.enumtype.ConfirmationSheetType
import com.dre.loyalty.core.platform.util.validator.type.ValidationType
import com.dre.loyalty.features.authentication.presentation.inputpassword.entity.InputPasswordState
import com.dre.loyalty.features.authentication.presentation.inputpassword.entity.InputPasswordSubmitState
import com.dre.loyalty.features.authentication.presentation.inputpassword.entity.InputPasswordTitleState

abstract class InputPasswordViewModel : BaseViewModel() {

    protected val _titleState: MutableLiveData<InputPasswordTitleState> = MutableLiveData()
    val titleState: LiveData<InputPasswordTitleState> = _titleState

    protected val _inputPasswordState: MutableLiveData<InputPasswordState> = MutableLiveData()
    val inputPasswordState: LiveData<InputPasswordState> = _inputPasswordState

    protected val _inputPasswordConfirmationState: MutableLiveData<InputPasswordState> = MutableLiveData()
    val inputPasswordConfirmationState: LiveData<InputPasswordState> = _inputPasswordConfirmationState

    protected val _submitButtonState: MutableLiveData<InputPasswordSubmitState> = MutableLiveData()
    val submitButtonState: LiveData<InputPasswordSubmitState> = _submitButtonState

    protected val _submitButtonClicked: MutableLiveData<Event<ConfirmationSheetType>> = MutableLiveData()
    val submitButtonClicked: LiveData<Event<ConfirmationSheetType>> = _submitButtonClicked

    private val _confirmationButtonClicked: MutableLiveData<Event<Boolean>> = MutableLiveData()
    val confirmationButtonClicked: LiveData<Event<Boolean>> = _confirmationButtonClicked

    private var password: Pair<String?, String?> = Pair(null, null)

    var user: User? = null

    fun handleInputPasswordDrawableEndClicked() {
        _inputPasswordState.value = _inputPasswordState.value?.copy(
            isShowing = !(_inputPasswordState.value?.isShowing ?: false)
        )
    }

    fun handleConfirmInputPasswordDrawableEndClicked() {
        _inputPasswordConfirmationState.value = _inputPasswordState.value?.copy(
            isShowing = !(_inputPasswordConfirmationState.value?.isShowing ?: false)
        )
    }

    fun handleInputPasswordTextChanged(text: String) {
        password = password.copy(first = text)
        val result = ValidationType.PASSWORD.strategy.validate(text)
        if (result.isPass) {
            _inputPasswordState.value = _inputPasswordState.value?.copy(error = null)
        } else {
            _inputPasswordState.value = _inputPasswordState.value?.copy(error = result.errorMessage)
        }
        checkButtonState()
    }

    fun handleConfirmInputPasswordTextChanged(text: String) {
        password = password.copy(second = text)
        val result = ValidationType.PASSWORD.strategy.validate(text)
        if (password.first != text) {
            _inputPasswordConfirmationState.value = _inputPasswordState.value?.copy(
                error = R.string.validation_failed_password_confirm
            )
            return
        }
        if (result.isPass) {
            _inputPasswordConfirmationState.value = _inputPasswordConfirmationState.value?.copy(
                error = null
            )
        } else {
            _inputPasswordConfirmationState.value = _inputPasswordConfirmationState.value?.copy(
                error = result.errorMessage
            )
        }
        checkButtonState()
    }

    fun handleSubmitButtonClicked(pass: String) {
        user = user?.copy(password = pass)
        user?.let {
            _loading.value = View.VISIBLE
            _submitButtonState.value = _submitButtonState.value?.copy(isEnabled = false)
            doApiTransaction(it)
        }
    }

    fun handleConfirmationSheetButtonClicked() {
        _confirmationButtonClicked.value = Event(true)
    }

    private fun checkButtonState() {
        _submitButtonState.value = _submitButtonState.value?.copy(
            isEnabled = _inputPasswordState.value?.error == null
                    && _inputPasswordConfirmationState.value?.error == null
        )
    }

    abstract fun bindInitialValue()

    protected abstract fun doApiTransaction(user: User)

    abstract fun getSuccessSheetType(): ConfirmationSheetType
}
