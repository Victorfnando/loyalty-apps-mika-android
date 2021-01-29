/*
 *
 * Created by Andreas on 1/29/21 4:19 PM
 * Copyright (c) 2021 . All rights reserved.
 * Last modified 1/29/21 4:19 PM
 * Github Link: https://github.com/oandrz
 * Email: oentoro.andreas@gmail.com
 *
 */

package com.dre.loyalty.features.authentication.presentation.otp.screen.create

import com.dre.loyalty.core.platform.functional.Event
import com.dre.loyalty.core.networking.response.BasicResponse
import com.dre.loyalty.features.authentication.domain.entity.OtpCode
import com.dre.loyalty.features.authentication.domain.usecase.VerifyCodeUseCase
import com.dre.loyalty.features.authentication.domain.usecase.VerifyCodeUseCase.Param
import com.dre.loyalty.features.authentication.domain.usecase.VerifyEmailUseCase
import com.dre.loyalty.features.authentication.presentation.otp.enumType.OtpType
import com.dre.loyalty.features.authentication.presentation.otp.screen.OtpViewModel
import javax.inject.Inject

class OtpRegisterViewModel @Inject constructor(
    verifyEmailUseCase: VerifyEmailUseCase,
    private val verifyCodeUseCase: VerifyCodeUseCase
) : OtpViewModel(verifyEmailUseCase) {

    override fun getType(): OtpType = OtpType.REGISTER

    override fun verifyCode(otpCode: OtpCode) {
        verifyCodeUseCase(Param(otpCode.email, otpCode.code)) {
            it.fold(::handleFailure, ::handleSuccessVerifyCode)
        }
    }

    private fun handleSuccessVerifyCode(response: BasicResponse) {
        _navigateDetailForm.value = Event(email)
    }
}