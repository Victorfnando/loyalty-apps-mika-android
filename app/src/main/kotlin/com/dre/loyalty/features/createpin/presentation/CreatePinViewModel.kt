/*
 * Created by Andreas Oen on 12/29/20 7:13 PM
 * Copyright (c) 2020 . All rights reserved.
 * Last modified 12/29/20 7:13 PM
 * github: https://github.com/oandrz
 */

package com.dre.loyalty.features.createpin.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dre.loyalty.R
import com.dre.loyalty.core.platform.BaseViewModel
import com.dre.loyalty.core.util.validator.type.ValidationType
import com.dre.loyalty.features.createpin.presentation.enums.CreatePinType
import javax.inject.Inject

class CreatePinViewModel @Inject constructor() : BaseViewModel() {

    private val _currentType : MutableLiveData<CreatePinType> = MutableLiveData()
    val currentType : LiveData<CreatePinType> = _currentType

    private val _showError: MutableLiveData<Int> = MutableLiveData()
    val showError: LiveData<Int> = _showError

    private val _createButtonState: MutableLiveData<Boolean> = MutableLiveData()
    val createButtonState: LiveData<Boolean> = _createButtonState

    private var cachedType: CreatePinType? = null
    private var inputPin: String? = null

    fun init(type: CreatePinType) {
        _currentType.value = type
    }

    fun handleCreatePinButtonClicked(pin: String) {
        when {
            _currentType.value != CreatePinType.CONFIRM -> {
                inputPin = pin
                cachedType = _currentType.value
                _currentType.value = CreatePinType.CONFIRM
            }
            pin != inputPin -> {
                _currentType.value = cachedType
                _showError.value = R.string.createpin_screen_confirm_error_differentpin
            }
            else -> {
                ///TODO: Success Proces
            }
        }
    }

    fun handleTextChanged(text: String) {
        _createButtonState.value = text.isNotEmpty()
    }
}
