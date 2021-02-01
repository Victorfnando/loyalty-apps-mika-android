/*
 *
 * Created by Andreas on 2/1/21 11:22 AM
 * Copyright (c) 2021 . All rights reserved.
 * Last modified 2/1/21 11:22 AM
 * Github Link: https://github.com/oandrz
 * Email: oentoro.andreas@gmail.com
 *
 */

package com.dre.loyalty.features.authentication.domain.usecase

import com.dre.loyalty.core.interactor.UseCase
import com.dre.loyalty.core.networking.exception.Failure
import com.dre.loyalty.core.networking.response.BasicResponse
import com.dre.loyalty.core.platform.functional.Either
import com.dre.loyalty.features.authentication.data.entity.request.LogoutRequest
import com.dre.loyalty.features.authentication.domain.AuthenticationRepositoryContract
import com.dre.loyalty.features.authentication.domain.usecase.LogOutUseCase.Param
import javax.inject.Inject

class LogOutUseCase @Inject constructor(
    private val repository: AuthenticationRepositoryContract
) : UseCase<BasicResponse, Param>() {

    override suspend fun run(params: Param): Either<Failure, BasicResponse> {
        return repository.logout(LogoutRequest(params.userId))
    }

    data class Param(
        val userId: String
    )
}