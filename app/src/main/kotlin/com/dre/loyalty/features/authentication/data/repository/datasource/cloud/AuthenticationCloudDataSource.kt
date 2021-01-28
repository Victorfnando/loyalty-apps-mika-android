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

import com.dre.loyalty.core.response.BasicResponse
import com.dre.loyalty.core.response.Response
import com.dre.loyalty.core.service.AuthenticationService
import com.dre.loyalty.features.authentication.data.entity.request.EmailRequest
import com.dre.loyalty.features.authentication.data.entity.request.LoginRequest
import com.dre.loyalty.features.authentication.data.entity.request.RegisterRequest
import com.dre.loyalty.features.authentication.data.entity.request.VerifyCodeRequest
import com.dre.loyalty.features.authentication.data.entity.response.LoginResponse
import com.dre.loyalty.features.authentication.domain.usecase.DoLoginUseCase
import retrofit2.Call
import javax.inject.Inject

class AuthenticationCloudDataSource @Inject constructor(
    private val service: AuthenticationService
) : AuthenticationCloudDataSourceContract {
    override fun login(request: LoginRequest): Call<Response<LoginResponse>> {
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
}