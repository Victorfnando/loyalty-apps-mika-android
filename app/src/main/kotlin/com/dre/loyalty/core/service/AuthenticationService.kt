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

import com.dre.loyalty.core.response.Response
import com.dre.loyalty.features.authentication.data.entity.request.LoginRequest
import com.dre.loyalty.features.authentication.data.entity.response.LoginResponse
import com.dre.loyalty.features.authentication.domain.usecase.DoLoginUseCase
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

private const val LOGIN_ENDPOINT = "login"
interface AuthenticationService {
    @POST(LOGIN_ENDPOINT) fun login(@Body param: LoginRequest): Call<Response<LoginResponse>>
}