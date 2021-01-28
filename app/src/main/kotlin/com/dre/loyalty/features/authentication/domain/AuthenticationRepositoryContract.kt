/*
 *
 * Created by Andreas on 1/27/21 1:49 PM
 * Copyright (c) 2021 . All rights reserved.
 * Last modified 1/27/21 1:49 PM
 * Github Link: https://github.com/oandrz
 * Email: oentoro.andreas@gmail.com
 *
 */

package com.dre.loyalty.features.authentication.domain

import com.dre.loyalty.core.exception.Failure
import com.dre.loyalty.core.functional.Either
import com.dre.loyalty.core.model.AuthCertificate
import com.dre.loyalty.core.response.BasicResponse
import com.dre.loyalty.features.authentication.data.entity.request.EmailRequest
import com.dre.loyalty.features.authentication.data.entity.request.LoginRequest
import com.dre.loyalty.features.authentication.data.entity.request.RegisterRequest
import com.dre.loyalty.features.authentication.data.entity.request.VerifyCodeRequest

interface AuthenticationRepositoryContract {
    fun login(request: LoginRequest): Either<Failure, AuthCertificate>
    fun register(request: RegisterRequest): Either<Failure, BasicResponse>
    fun checkMail(request: EmailRequest): Either<Failure, BasicResponse>
    fun verifyCode(request: VerifyCodeRequest): Either<Failure, BasicResponse>
}