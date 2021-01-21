/*
 * Created by Andreas Oen on 1/20/21 7:37 PM
 * Copyright (c) 2021 . All rights reserved.
 * Last modified 1/20/21 7:03 PM
 * github: https://github.com/oandrz
 */

package com.dre.loyalty.features.passwordinput.presentation.screen.reset

import com.dre.loyalty.R
import com.dre.loyalty.features.passwordinput.presentation.entity.InputPasswordState
import com.dre.loyalty.features.passwordinput.presentation.entity.InputPasswordSubmitState
import com.dre.loyalty.features.passwordinput.presentation.entity.InputPasswordTitleState
import com.dre.loyalty.features.passwordinput.presentation.screen.InputPasswordViewModel
import javax.inject.Inject

class InputPasswordResetViewModel @Inject constructor() : InputPasswordViewModel() {
    override fun bindInitialValue() {
        _titleState.value = InputPasswordTitleState(R.string.inputPassword_title_reset)
        _inputPasswordState.value = InputPasswordState(
            R.string.inputPassword_label_passwordReset,
            R.string.inputPassword_hint_reset,
            false,
            -1
        )
        _inputPasswordConfirmationState.value = InputPasswordState(
            R.string.inputPassword_label_passwordResetConfirmation,
            R.string.inputPassword_hint_resetConfirmation,
            false,
            -1
        )
        _submitButtonState.value = InputPasswordSubmitState(
            R.string.inputPassword_label_button_reset, false
        )
    }
}
