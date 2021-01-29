/*
 *
 * Created by Andreas on 1/28/21 6:53 PM
 * Copyright (c) 2021 . All rights reserved.
 * Last modified 1/28/21 6:53 PM
 * Github Link: https://github.com/oandrz
 * Email: oentoro.andreas@gmail.com
 *
 */

package com.dre.loyalty.features.authentication.domain.usecase

import com.dre.loyalty.core.networking.exception.Failure
import com.dre.loyalty.core.platform.functional.Either
import com.dre.loyalty.core.interactor.UseCase
import com.dre.loyalty.core.networking.response.BasicResponse
import com.dre.loyalty.features.authentication.data.entity.request.EmailRequest
import com.dre.loyalty.features.authentication.domain.AuthenticationRepositoryContract
import javax.inject.Inject

class VerifyEmailUseCase @Inject constructor(
    private val repository: AuthenticationRepositoryContract
): UseCase<BasicResponse, EmailRequest>() {
    override suspend fun run(params: EmailRequest): Either<Failure, BasicResponse> {
        return repository.checkMail(params)
    }
}