/*
 * Created by Andreas Oen on 1/20/21 7:37 PM
 * Copyright (c) 2021 . All rights reserved.
 * Last modified 1/20/21 7:03 PM
 * github: https://github.com/oandrz
 */

package com.dre.loyalty.features.authentication.presentation.inputpassword.screen.create

import android.view.View
import com.dre.loyalty.R
import com.dre.loyalty.core.functional.Event
import com.dre.loyalty.core.model.User
import com.dre.loyalty.core.response.BasicResponse
import com.dre.loyalty.core.util.enumtype.ConfirmationSheetType
import com.dre.loyalty.features.authentication.data.entity.request.RegisterRequest
import com.dre.loyalty.features.authentication.domain.usecase.DoRegisterUseCase
import com.dre.loyalty.features.authentication.presentation.inputpassword.entity.InputPasswordState
import com.dre.loyalty.features.authentication.presentation.inputpassword.entity.InputPasswordSubmitState
import com.dre.loyalty.features.authentication.presentation.inputpassword.entity.InputPasswordTitleState
import com.dre.loyalty.features.authentication.presentation.inputpassword.screen.InputPasswordViewModel
import javax.inject.Inject

class InputPasswordCreateViewModel @Inject constructor(
    private val doRegisterUseCase: DoRegisterUseCase
) : InputPasswordViewModel() {
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

    override fun doApiTransaction(user: User) {
        doRegisterUseCase(
            RegisterRequest(
                user.firstName,
                user.lastName,
                user.cardId,
                user.phone,
                user.gender,
                user.birthDate,
                user.email,
                user.password
            )
        ) {
            it.fold(::handleFailure, ::handleSuccessRegister)
        }
    }

    override fun getSuccessSheetType(): ConfirmationSheetType =
        ConfirmationSheetType.INPUT_PASSWORD_CREATE_SUCCESS_SHEET

    private fun handleSuccessRegister(response: BasicResponse) {
        _submitButtonState.value = _submitButtonState.value?.copy(isEnabled = true)
        _loading.value = View.GONE
        _submitButtonClicked.value = Event(getSuccessSheetType())
    }
}
