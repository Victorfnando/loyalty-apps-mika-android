/*
 *
 * Created by Andreas on 1/29/21 3:15 PM
 * Copyright (c) 2021 . All rights reserved.
 * Last modified 1/29/21 3:15 PM
 * Github Link: https://github.com/oandrz
 * Email: oentoro.andreas@gmail.com
 *
 */

package com.dre.loyalty.features.authentication.domain.usecase

import com.dre.loyalty.core.exception.Failure
import com.dre.loyalty.core.functional.Either
import com.dre.loyalty.core.interactor.UseCase
import com.dre.loyalty.features.authentication.data.entity.request.EmailRequest
import com.dre.loyalty.features.authentication.domain.AuthenticationRepositoryContract
import javax.inject.Inject

class DoCheckMailForgotPasswordUseCase @Inject constructor(
    private val repository: AuthenticationRepositoryContract
) : UseCase<Int, DoCheckMailForgotPasswordUseCase.Param>() {

    override suspend fun run(params: Param): Either<Failure, Int> {
        return repository.forgotPasswordCheckMail(
            EmailRequest(params.email)
        )
    }

    data class Param(
        val email: String
    )
}