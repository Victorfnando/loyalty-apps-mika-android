/*
 *
 * Created by Andreas on 1/28/21 6:14 PM
 * Copyright (c) 2021 . All rights reserved.
 * Last modified 1/28/21 6:14 PM
 * Github Link: https://github.com/oandrz
 * Email: oentoro.andreas@gmail.com
 *
 */

package com.dre.loyalty.features.authentication.domain.usecase

import com.dre.loyalty.core.networking.exception.Failure
import com.dre.loyalty.core.platform.functional.Either
import com.dre.loyalty.core.interactor.UseCase
import com.dre.loyalty.core.networking.response.BasicResponse
import com.dre.loyalty.features.authentication.data.entity.request.RegisterRequest
import com.dre.loyalty.features.authentication.domain.AuthenticationRepositoryContract
import javax.inject.Inject

class DoRegisterUseCase @Inject constructor(
    private val repository: AuthenticationRepositoryContract
) : UseCase<BasicResponse, RegisterRequest>() {
    override suspend fun run(params: RegisterRequest): Either<Failure, BasicResponse> {
        return repository.register(params)
    }
}