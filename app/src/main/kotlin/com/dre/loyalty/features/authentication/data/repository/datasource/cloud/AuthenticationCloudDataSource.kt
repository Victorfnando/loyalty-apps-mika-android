/*
 *
 * Created by Andreas on 1/28/21 12:02 PM
 * Copyright (c) 2021 . All rights reserved.
 * Last modified 1/28/21 11:52 AM
 * Github Link: https://github.com/oandrz
 * Email: oentoro.andreas@gmail.com
 *
 */

package com.dre.loyalty.features.authentication.data.repository.datasource.cloud

import com.dre.loyalty.core.networking.response.BasicResponse
import com.dre.loyalty.core.networking.response.LoyaltyResponse
import com.dre.loyalty.core.networking.AuthenticationService
import com.dre.loyalty.features.authentication.data.entity.request.*
import com.dre.loyalty.features.authentication.data.entity.response.ForgotPasswordEmailVerificationResponse
import com.dre.loyalty.features.authentication.data.entity.response.LoginResponse
import retrofit2.Call
import javax.inject.Inject

class AuthenticationCloudDataSource @Inject constructor(
    private val service: AuthenticationService
) : AuthenticationCloudDataSourceContract {

    override fun login(request: LoginRequest): Call<LoyaltyResponse<LoginResponse>> {
        return service.login(request)
    }

    override fun register(request: RegisterRequest): Call<BasicResponse> {
        return service.register(request)
    }

    override fun checkMail(request: EmailRequest): Call<BasicResponse> {
        return service.checkMail(request)
    }

    override fun verifyCode(request: VerifyCodeRequest): Call<BasicResponse> {
        return service.verifyCode(request)
    }

    override fun forgotPasswordCheckMail(request: EmailRequest): Call<LoyaltyResponse<ForgotPasswordEmailVerificationResponse>> {
        return service.forgotPasswordCheckMail(request)
    }

    override fun forgotPasswordVerifyCode(request: VerifyCodeRequest): Call<BasicResponse> {
        return service.forgotPasswordVerifyCode(request)
    }

    override fun resetPassword(request: ResetPasswordRequest): Call<BasicResponse> {
        return service.resetPassword(request)
    }

    override fun updatePassword(request: UpdatePasswordRequest): Call<BasicResponse> {
        return service.updatePassword(request)
    }

    override fun logout(request: LogoutRequest): Call<BasicResponse> {
        return service.logout(request)
    }
}