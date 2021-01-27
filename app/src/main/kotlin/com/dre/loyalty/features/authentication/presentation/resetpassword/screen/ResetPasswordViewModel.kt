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

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dre.loyalty.core.functional.Event
import com.dre.loyalty.core.util.enumtype.ConfirmationSheetType
import com.dre.loyalty.core.util.validator.type.ValidationType
import com.dre.loyalty.features.authentication.presentation.resetpassword.entity.ResetPinButtonState
import com.dre.loyalty.features.authentication.presentation.resetpassword.entity.ResetPinPhoneNumberInputState
import javax.inject.Inject

class ResetPasswordViewModel @Inject constructor() : ViewModel() {

    private val _resetPinButtonState: MutableLiveData<ResetPinButtonState> = MutableLiveData()
    val resetPinButtonState: LiveData<ResetPinButtonState> = _resetPinButtonState

    private val _mailInputState: MutableLiveData<ResetPinPhoneNumberInputState> = MutableLiveData()
    val mailInputState: LiveData<ResetPinPhoneNumberInputState> = _mailInputState

    private val _navigatePasswordInput: MutableLiveData<Event<ConfirmationSheetType>> = MutableLiveData()
    val navigatePasswordInput: LiveData<Event<ConfirmationSheetType>> = _navigatePasswordInput

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
        _navigatePasswordInput.value = Event(ConfirmationSheetType.RESET_PASSWORD_LINK_SHEET)
    }
}
