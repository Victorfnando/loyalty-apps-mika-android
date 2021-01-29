/*
 *
 * Created by Andreas on 1/28/21 11:45 AM
 * Copyright (c) 2021 . All rights reserved.
 * Last modified 1/28/21 11:45 AM
 * Github Link: https://github.com/oandrz
 * Email: oentoro.andreas@gmail.com
 *
 */

package com.dre.loyalty.features.authentication.domain.usecase

import com.dre.loyalty.core.networking.exception.Failure
import com.dre.loyalty.core.platform.functional.Either
import com.dre.loyalty.core.interactor.UseCase
import com.dre.loyalty.core.model.AuthCertificate
import com.dre.loyalty.features.authentication.data.entity.request.LoginRequest
import com.dre.loyalty.features.authentication.domain.AuthenticationRepositoryContract
import com.dre.loyalty.features.authentication.domain.usecase.DoLoginUseCase.Param
import javax.inject.Inject

class DoLoginUseCase @Inject constructor(
    private val repository: AuthenticationRepositoryContract
) : UseCase<AuthCertificate, Param>() {

    override suspend fun run(params: Param): Either<Failure, AuthCertificate> {
        return repository.login(LoginRequest(params.email, params.password))
    }

    data class Param(
        val email: String,
        val password: String
    )
}