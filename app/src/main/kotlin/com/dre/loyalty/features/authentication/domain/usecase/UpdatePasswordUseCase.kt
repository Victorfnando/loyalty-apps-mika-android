/*
 *
 * Created by Andreas on 1/31/21 10:08 PM
 * Copyright (c) 2021 . All rights reserved.
 * Last modified 1/31/21 10:08 PM
 * Github Link: https://github.com/oandrz
 * Email: oentoro.andreas@gmail.com
 *
 */

package com.dre.loyalty.features.authentication.domain.usecase

import com.dre.loyalty.core.interactor.UseCase
import com.dre.loyalty.core.networking.exception.Failure
import com.dre.loyalty.core.networking.response.BasicResponse
import com.dre.loyalty.core.platform.functional.Either
import com.dre.loyalty.features.authentication.data.entity.request.UpdatePasswordRequest
import com.dre.loyalty.features.authentication.domain.AuthenticationRepositoryContract
import com.dre.loyalty.features.authentication.domain.usecase.UpdatePasswordUseCase.Param
import javax.inject.Inject

class UpdatePasswordUseCase @Inject constructor(
    private val repository: AuthenticationRepositoryContract
) : UseCase<BasicResponse, Param>() {

    override suspend fun run(params: Param): Either<Failure, BasicResponse> {
        return repository.updatePassword(
            UpdatePasswordRequest(
                params.userId,
                params.password,
                params.newPassword
            )
        )
    }

    data class Param(
        val userId: String,
        val password: String,
        val newPassword: String
    )
}