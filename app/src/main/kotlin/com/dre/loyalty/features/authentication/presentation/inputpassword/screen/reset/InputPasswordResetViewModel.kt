/*
 * Created by Andreas Oen on 1/20/21 7:37 PM
 * Copyright (c) 2021 . All rights reserved.
 * Last modified 1/20/21 7:03 PM
 * github: https://github.com/oandrz
 */

package com.dre.loyalty.features.authentication.presentation.inputpassword.screen.reset

import android.view.View
import com.dre.loyalty.R
import com.dre.loyalty.core.platform.functional.Event
import com.dre.loyalty.core.model.User
import com.dre.loyalty.core.networking.response.BasicResponse
import com.dre.loyalty.core.platform.util.enumtype.ConfirmationSheetType
import com.dre.loyalty.features.authentication.domain.usecase.DoResetPasswordUseCase
import com.dre.loyalty.features.authentication.presentation.inputpassword.entity.InputPasswordState
import com.dre.loyalty.features.authentication.presentation.inputpassword.entity.InputPasswordSubmitState
import com.dre.loyalty.features.authentication.presentation.inputpassword.entity.InputPasswordTitleState
import com.dre.loyalty.features.authentication.presentation.inputpassword.screen.InputPasswordViewModel
import javax.inject.Inject

class InputPasswordResetViewModel @Inject constructor(
    private val resetPasswordUseCase: DoResetPasswordUseCase
) : InputPasswordViewModel() {

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

    override fun doApiTransaction(user: User) {
        resetPasswordUseCase(
            DoResetPasswordUseCase.Param(user.email, user.password)
        ) {
            it.fold(::handleFailure, ::handleSuccessResetPassword)
        }
    }

    override fun getSuccessSheetType(): ConfirmationSheetType =
        ConfirmationSheetType.INPUT_PASSWORD_RESET_SUCCESS_SHEET

    private fun handleSuccessResetPassword(response: BasicResponse) {
        _submitButtonState.value = _submitButtonState.value?.copy(isEnabled = true)
        _loading.value = View.GONE
        _submitButtonClicked.value = Event(getSuccessSheetType())
    }
}
