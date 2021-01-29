/*
 *
 * Created by Andreas on 1/29/21 4:06 PM
 * Copyright (c) 2021 . All rights reserved.
 * Last modified 1/29/21 4:06 PM
 * Github Link: https://github.com/oandrz
 * Email: oentoro.andreas@gmail.com
 *
 */

package com.dre.loyalty.features.authentication.presentation.otp.enumType

import com.dre.loyalty.features.authentication.presentation.otp.screen.OtpViewModel
import com.dre.loyalty.features.authentication.presentation.otp.screen.create.OtpRegisterViewModel
import com.dre.loyalty.features.authentication.presentation.otp.screen.forgot.OtpForgotPasswordViewModel

enum class OtpType(val reference: Class<out OtpViewModel>) {
    FORGOT_PASSWORD(OtpForgotPasswordViewModel::class.java),
    REGISTER(OtpRegisterViewModel::class.java)
}