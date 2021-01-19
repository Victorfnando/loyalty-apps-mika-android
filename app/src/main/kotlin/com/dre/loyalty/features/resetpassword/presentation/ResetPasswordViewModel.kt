/*
 * Created by Andreas Oen on 12/27/20 2:00 PM
 * Copyright (c) 2020 . All rights reserved.
 * Last modified 12/27/20 2:00 PM
 * github: https://github.com/oandrz
 */

package com.dre.loyalty.features.resetpassword.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dre.loyalty.core.util.validator.type.ValidationType
import com.dre.loyalty.features.resetpassword.presentation.entity.ResetPinButtonState
import com.dre.loyalty.features.resetpassword.presentation.entity.ResetPinPhoneNumberInputState
import javax.inject.Inject

class ResetPasswordViewModel @Inject constructor() : ViewModel() {

    private val _resetPinButtonState: MutableLiveData<ResetPinButtonState> = MutableLiveData()
    val resetPinButtonState: LiveData<ResetPinButtonState> = _resetPinButtonState

    private val _mailInputState: MutableLiveData<ResetPinPhoneNumberInputState> = MutableLiveData()
    val mailInputState: LiveData<ResetPinPhoneNumberInputState> = _mailInputState

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
        _resetPinButtonState.value = ResetPinButtonState(true)
    }
}
