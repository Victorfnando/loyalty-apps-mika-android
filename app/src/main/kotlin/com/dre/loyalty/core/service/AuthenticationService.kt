/*
 *
 * Created by Andreas on 1/28/21 11:43 AM
 * Copyright (c) 2021 . All rights reserved.
 * Last modified 1/28/21 11:43 AM
 * Github Link: https://github.com/oandrz
 * Email: oentoro.andreas@gmail.com
 *
 */

package com.dre.loyalty.core.service

import com.dre.loyalty.core.response.BasicResponse
import com.dre.loyalty.core.response.Response
import com.dre.loyalty.features.authentication.data.entity.request.EmailRequest
import com.dre.loyalty.features.authentication.data.entity.request.LoginRequest
import com.dre.loyalty.features.authentication.data.entity.request.RegisterRequest
import com.dre.loyalty.features.authentication.data.entity.request.VerifyCodeRequest
import com.dre.loyalty.features.authentication.data.entity.response.LoginResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

private const val LOGIN_ENDPOINT = "login"
private const val REGISTER_ENDPOINT = "register/update_profile"
private const val EMAIL_CHECK_ENDPOINT = "register/check_mail"
private const val VERIFY_CODE_ENDPOINT = "register/verify_code"
interface AuthenticationService {
    @POST(LOGIN_ENDPOINT) fun login(@Body request: LoginRequest): Call<Response<LoginResponse>>
    @POST(REGISTER_ENDPOINT) fun register(@Body request: RegisterRequest): Call<BasicResponse>
    @POST(EMAIL_CHECK_ENDPOINT) fun checkMail(@Body request: EmailRequest): Call<BasicResponse>
    @POST(VERIFY_CODE_ENDPOINT) fun verifyCode(@Body request: VerifyCodeRequest): Call<BasicResponse>
}