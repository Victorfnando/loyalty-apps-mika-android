/*
 * Created by Andreas Oen on 1/20/21 7:37 PM
 * Copyright (c) 2021 . All rights reserved.
 * Last modified 1/20/21 7:03 PM
 * github: https://github.com/oandrz
 */

package com.dre.loyalty.features.passwordinput.presentation.screen.create

import com.dre.loyalty.R
import com.dre.loyalty.core.util.enumtype.ConfirmationSheetType
import com.dre.loyalty.features.passwordinput.presentation.entity.InputPasswordState
import com.dre.loyalty.features.passwordinput.presentation.entity.InputPasswordSubmitState
import com.dre.loyalty.features.passwordinput.presentation.entity.InputPasswordTitleState
import com.dre.loyalty.features.passwordinput.presentation.screen.InputPasswordViewModel
import javax.inject.Inject

class InputPasswordCreateViewModel @Inject constructor() : InputPasswordViewModel() {
    override fun bindInitialValue() {
        _titleState.value = InputPasswordTitleState(R.string.inputPassword_title_create)
        _inputPasswordState.value = InputPasswordState(
            R.string.inputPassword_label_passwordCreate,
            R.string.inputPassword_hint_create,
            false,
            -1
        )
        _inputPasswordConfirmationState.value = InputPasswordState(
            R.string.inputPassword_label_passwordCreateConfirmation,
            R.string.inputPassword_hint_createConfirmation,
            false,
            -1
        )
        _submitButtonState.value = InputPasswordSubmitState(
            R.string.inputPassword_label_button_create, false
        )
    }

    override fun getSuccessSheetType(): ConfirmationSheetType =
        ConfirmationSheetType.INPUT_PASSWORD_CREATE_SUCCESS_SHEET
}
