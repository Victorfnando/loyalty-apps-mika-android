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

import com.dre.loyalty.core.response.Response
import com.dre.loyalty.features.authentication.data.entity.request.LoginRequest
import com.dre.loyalty.features.authentication.data.entity.response.LoginResponse
import com.dre.loyalty.features.authentication.domain.usecase.DoLoginUseCase
import retrofit2.Call

interface AuthenticationCloudDataSourceContract {
    fun login(param: LoginRequest): Call<Response<LoginResponse>>
}