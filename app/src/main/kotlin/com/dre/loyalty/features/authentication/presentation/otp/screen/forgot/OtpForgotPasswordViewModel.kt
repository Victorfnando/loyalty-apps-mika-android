/*
 *
 * Created by Andreas on 1/29/21 4:19 PM
 * Copyright (c) 2021 . All rights reserved.
 * Last modified 1/29/21 4:19 PM
 * Github Link: https://github.com/oandrz
 * Email: oentoro.andreas@gmail.com
 *
 */

package com.dre.loyalty.features.authentication.presentation.otp.screen.forgot

import com.dre.loyalty.core.platform.functional.Event
import com.dre.loyalty.core.model.User
import com.dre.loyalty.core.networking.response.BasicResponse
import com.dre.loyalty.features.authentication.domain.entity.OtpCode
import com.dre.loyalty.features.authentication.domain.usecase.DoVerifyCodeForgotPasswordUseCase
import com.dre.loyalty.features.authentication.domain.usecase.DoVerifyCodeForgotPasswordUseCase.Param
import com.dre.loyalty.features.authentication.domain.usecase.VerifyEmailUseCase
import com.dre.loyalty.features.authentication.presentation.otp.enumType.OtpType
import com.dre.loyalty.features.authentication.presentation.otp.screen.OtpViewModel
import javax.inject.Inject

class OtpForgotPasswordViewModel @Inject constructor(
    verifyEmailUseCase: VerifyEmailUseCase,
    private val verifyCodeForgotPasswordUseCase: DoVerifyCodeForgotPasswordUseCase
) : OtpViewModel(verifyEmailUseCase) {

    override fun getType(): OtpType = OtpType.FORGOT_PASSWORD

    override fun verifyCode(otpCode: OtpCode) {
        verifyCodeForgotPasswordUseCase(Param(otpCode.email, otpCode.code)) {
            it.fold(::handleFailure, ::handleSuccessVerifyCode)
        }
    }

    private fun handleSuccessVerifyCode(response: BasicResponse) {
        _navigateResetPassword.value = Event(User(
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            email,
            ""
        ))
    }
}