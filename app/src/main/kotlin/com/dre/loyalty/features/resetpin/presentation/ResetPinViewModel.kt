/*
 * Created by Andreas Oen on 12/27/20 2:00 PM
 * Copyright (c) 2020 . All rights reserved.
 * Last modified 12/27/20 2:00 PM
 * github: https://github.com/oandrz
 */

package com.dre.loyalty.features.resetpin.presentation

import androidx.core.text.isDigitsOnly
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dre.loyalty.features.resetpin.presentation.entity.ResetPinButtonState
import com.dre.loyalty.features.resetpin.presentation.entity.ResetPinPhoneNumberInputState
import javax.inject.Inject

class ResetPinViewModel @Inject constructor() : ViewModel() {

    private val _resetPinButtonState: MutableLiveData<ResetPinButtonState> = MutableLiveData()
    val resetPinButtonState: LiveData<ResetPinButtonState> = _resetPinButtonState

    private val _phoneInputState: MutableLiveData<ResetPinPhoneNumberInputState> = MutableLiveData()
    val phoneInputState: LiveData<ResetPinPhoneNumberInputState> = _phoneInputState

    init {
        _resetPinButtonState.value = ResetPinButtonState(false)
        _phoneInputState.value = ResetPinPhoneNumberInputState(null)
    }

    fun handleTextChanged(text: String) {
        if (text.isEmpty()) {
            _phoneInputState.value = ResetPinPhoneNumberInputState(null)
            _resetPinButtonState.value = ResetPinButtonState(false)
            return
        }
        if (!text.isDigitsOnly()) {
            _phoneInputState.value = ResetPinPhoneNumberInputState("Number Only")
            _resetPinButtonState.value = ResetPinButtonState(false)
            return
        }
        _phoneInputState.value = ResetPinPhoneNumberInputState(null)
        _resetPinButtonState.value = ResetPinButtonState(true)
    }
}
