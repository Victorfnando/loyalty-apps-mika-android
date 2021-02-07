/*
 *
 * Created by Andreas on 1/28/21 4:24 PM
 * Copyright (c) 2021 . All rights reserved.
 * Last modified 1/28/21 12:07 PM
 * Github Link: https://github.com/oandrz
 * Email: oentoro.andreas@gmail.com
 *
 */

package com.dre.loyalty.features.authentication.data.repository.datasource.cloud

import com.dre.loyalty.core.networking.response.BasicResponse
import com.dre.loyalty.core.networking.response.LoyaltyResponse
import com.dre.loyalty.features.authentication.data.entity.request.*
import com.dre.loyalty.features.authentication.data.entity.response.ForgotPasswordEmailVerificationResponse
import com.dre.loyalty.features.authentication.data.entity.response.LoginResponse
import com.dre.loyalty.features.authentication.data.entity.response.RegisterResponse
import retrofit2.Call

interface AuthenticationCloudDataSourceContract {
    fun login(request: LoginRequest): Call<LoyaltyResponse<LoginResponse>>
    fun register(request: RegisterRequest): Call<BasicResponse>
    fun checkMail(request: EmailRequest): Call<LoyaltyResponse<RegisterResponse>>
    fun verifyCode(request: VerifyCodeRequest): Call<BasicResponse>
    fun forgotPasswordCheckMail(request: EmailRequest): Call<LoyaltyResponse<ForgotPasswordEmailVerificationResponse>>
    fun forgotPasswordVerifyCode(request: VerifyCodeRequest): Call<BasicResponse>
    fun resetPassword(request: ResetPasswordRequest): Call<BasicResponse>
    fun updatePassword(request: UpdatePasswordRequest): Call<BasicResponse>
    fun logout(request: LogoutRequest): Call<BasicResponse>
}