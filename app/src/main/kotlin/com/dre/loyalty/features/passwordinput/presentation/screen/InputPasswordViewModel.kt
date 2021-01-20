/*
 * Created by Andreas Oen on 1/20/21 7:01 PM
 * Copyright (c) 2021 . All rights reserved.
 * Last modified 1/20/21 7:01 PM
 * github: https://github.com/oandrz
 */

package com.dre.loyalty.features.passwordinput.presentation.screen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dre.loyalty.core.platform.BaseViewModel
import com.dre.loyalty.features.passwordinput.presentation.entity.InputPasswordPasswordState
import com.dre.loyalty.features.passwordinput.presentation.entity.InputPasswordSubmitState
import com.dre.loyalty.features.passwordinput.presentation.entity.InputPasswordTitleState

abstract class InputPasswordViewModel : BaseViewModel() {

    protected val _titleState: MutableLiveData<InputPasswordTitleState> = MutableLiveData()
    val titleState: LiveData<InputPasswordTitleState> = _titleState

    protected val _inputPasswordState: MutableLiveData<InputPasswordPasswordState> = MutableLiveData()
    val inputPasswordState: LiveData<InputPasswordPasswordState> = _inputPasswordState

    protected val _inputPasswordConfirmationState: MutableLiveData<InputPasswordPasswordState> = MutableLiveData()
    val inputPasswordConfirmationState: LiveData<InputPasswordPasswordState> = _inputPasswordConfirmationState

    protected val _submitButtonState: MutableLiveData<InputPasswordSubmitState> = MutableLiveData()
    val submitButtonState: LiveData<InputPasswordSubmitState> = _submitButtonState

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

    abstract fun bindInitialValue()
}
