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
import com.dre.loyalty.features.authentication.data.entity.request.LoginRequest

interface AuthenticationRepositoryContract {
    suspend fun login(param: LoginRequest): Either<Failure, AuthCertificate>
}