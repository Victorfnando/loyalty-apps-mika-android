/*
 * Created by Andreas Oen on 1/11/21 8:47 PM
 * Copyright (c) 2021 . All rights reserved.
 * Last modified 1/11/21 8:47 PM
 * github: https://github.com/oandrz
 */

package com.dre.loyalty.features.changeprofile.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dre.loyalty.core.platform.BaseViewModel
import com.dre.loyalty.core.util.validator.type.ValidationType
import com.dre.loyalty.features.changeprofile.presentation.entity.DescriptionEtState
import com.dre.loyalty.features.changeprofile.presentation.entity.SendButtonState
import javax.inject.Inject

class UpdateProfileViewModel @Inject constructor() : BaseViewModel() {

    private val _descInputState: MutableLiveData<DescriptionEtState> = MutableLiveData()
    val descInputState: LiveData<DescriptionEtState> = _descInputState

    private val _sendButtonState: MutableLiveData<SendButtonState> = MutableLiveData()
    val sendButtonState: LiveData<SendButtonState> = _sendButtonState

    init {
        _descInputState.value = DescriptionEtState(-1)
        _sendButtonState.value = SendButtonState(false)
    }

    fun handleEtDescriptionChanged(text: String) {
        val result = ValidationType.NAME.strategy.validate(text)
        if (result.isPass) {
            _descInputState.value = DescriptionEtState(null)
        } else {
            _descInputState.value = DescriptionEtState(result.errorMessage)
        }
        checkButtonState()
    }

    private fun checkButtonState() {
        _sendButtonState.value = SendButtonState(_descInputState.value?.error == null)
    }
}
