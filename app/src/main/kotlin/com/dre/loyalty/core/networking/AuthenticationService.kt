/*
 *
 * Created by Andreas on 1/28/21 11:43 AM
 * Copyright (c) 2021 . All rights reserved.
 * Last modified 1/28/21 11:43 AM
 * Github Link: https://github.com/oandrz
 * Email: oentoro.andreas@gmail.com
 *
 */

package com.dre.loyalty.core.networking

import com.dre.loyalty.core.networking.response.BasicResponse
import com.dre.loyalty.core.networking.response.LoyaltyResponse
import com.dre.loyalty.features.authentication.data.entity.request.*
import com.dre.loyalty.features.authentication.data.entity.response.ForgotPasswordEmailVerificationResponse
import com.dre.loyalty.features.authentication.data.entity.response.LoginResponse
import com.dre.loyalty.features.authentication.data.entity.response.RegisterResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

private const val LOGIN_ENDPOINT = "login"
private const val REGISTER_ENDPOINT = "register/update_profile"
private const val EMAIL_CHECK_ENDPOINT = "register/check_mail"
private const val VERIFY_CODE_ENDPOINT = "register/verify_code"
private const val FORGOT_PASSWORD_EMAIL_CHECK_ENDPOINT = "forgot_password/check_mail"
private const val FORGOT_PASSWORD_VERIFY_CODE_ENDPOINT = "forgot_password/verify_code"
private const val FORGOT_PASSWORD_RESET_PASSWORD = "forgot_password/update"
private const val UPDATE_PASSWORD_ENDPOINT = "change_password"
private const val LOGOUT_ENDPOINT = "logout"
interface AuthenticationService {

    @POST(LOGIN_ENDPOINT)
    fun login(@Body request: LoginRequest): Call<LoyaltyResponse<LoginResponse>>

    @POST(REGISTER_ENDPOINT)
    fun register(@Body request: RegisterRequest): Call<BasicResponse>

    @POST(EMAIL_CHECK_ENDPOINT)
    fun checkMail(@Body request: EmailRequest): Call<LoyaltyResponse<RegisterResponse>>

    @POST(VERIFY_CODE_ENDPOINT)
    fun verifyCode(@Body request: VerifyCodeRequest): Call<BasicResponse>

    @POST(FORGOT_PASSWORD_EMAIL_CHECK_ENDPOINT)
    fun forgotPasswordCheckMail(@Body request: EmailRequest): Call<LoyaltyResponse<ForgotPasswordEmailVerificationResponse>>

    @POST(FORGOT_PASSWORD_VERIFY_CODE_ENDPOINT)
    fun forgotPasswordVerifyCode(@Body request: VerifyCodeRequest): Call<BasicResponse>

    @POST(FORGOT_PASSWORD_RESET_PASSWORD)
    fun resetPassword(@Body request: ResetPasswordRequest): Call<BasicResponse>

    @POST(UPDATE_PASSWORD_ENDPOINT)
    fun updatePassword(@Body request: UpdatePasswordRequest): Call<BasicResponse>

    @POST(LOGOUT_ENDPOINT)
    fun logout(@Body request: LogoutRequest): Call<BasicResponse>
}