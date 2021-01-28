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

import com.dre.loyalty.core.response.BasicResponse
import com.dre.loyalty.core.response.Response
import com.dre.loyalty.features.authentication.data.entity.request.EmailRequest
import com.dre.loyalty.features.authentication.data.entity.request.LoginRequest
import com.dre.loyalty.features.authentication.data.entity.request.RegisterRequest
import com.dre.loyalty.features.authentication.data.entity.request.VerifyCodeRequest
import com.dre.loyalty.features.authentication.data.entity.response.LoginResponse
import retrofit2.Call

interface AuthenticationCloudDataSourceContract {
    fun login(request: LoginRequest): Call<Response<LoginResponse>>
    fun register(request: RegisterRequest): Call<BasicResponse>
    fun checkMail(request: EmailRequest): Call<BasicResponse>
    fun verifyCode(request: VerifyCodeRequest): Call<BasicResponse>
}