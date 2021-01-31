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

import com.dre.loyalty.core.networking.exception.Failure
import com.dre.loyalty.core.platform.functional.Either
import com.dre.loyalty.core.model.AuthCertificate
import com.dre.loyalty.core.networking.response.BasicResponse
import com.dre.loyalty.features.authentication.data.entity.request.*

interface AuthenticationRepositoryContract {
    fun login(request: LoginRequest): Either<Failure, AuthCertificate>
    fun register(request: RegisterRequest): Either<Failure, BasicResponse>
    fun checkMail(request: EmailRequest): Either<Failure, BasicResponse>
    fun verifyCode(request: VerifyCodeRequest): Either<Failure, BasicResponse>
    fun forgotPasswordCheckMail(request: EmailRequest): Either<Failure, Int>
    fun forgotPasswordVerifyCode(request: VerifyCodeRequest): Either<Failure, BasicResponse>
    fun resetPassword(request: ResetPasswordRequest): Either<Failure, BasicResponse>
    fun updatePassword(request: UpdatePasswordRequest): Either<Failure, BasicResponse>
}