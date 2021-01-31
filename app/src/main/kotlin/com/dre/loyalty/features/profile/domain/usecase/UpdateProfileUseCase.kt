/*
 *
 * Created by Andreas on 1/31/21 8:24 PM
 * Copyright (c) 2021 . All rights reserved.
 * Last modified 1/31/21 8:24 PM
 * Github Link: https://github.com/oandrz
 * Email: oentoro.andreas@gmail.com
 *
 */

package com.dre.loyalty.features.profile.domain.usecase

import com.dre.loyalty.core.interactor.UseCase
import com.dre.loyalty.core.networking.exception.Failure
import com.dre.loyalty.core.networking.response.BasicResponse
import com.dre.loyalty.core.platform.functional.Either
import com.dre.loyalty.features.profile.data.entity.request.UpdateProfileRequest
import com.dre.loyalty.features.profile.domain.UserRepositoryContract
import com.dre.loyalty.features.profile.domain.usecase.UpdateProfileUseCase.Param
import javax.inject.Inject

class UpdateProfileUseCase @Inject constructor(
    private val repository: UserRepositoryContract
) : UseCase<BasicResponse, Param>() {

    override suspend fun run(params: Param): Either<Failure, BasicResponse> {
        return repository.updateProfile(
            UpdateProfileRequest(
                params.userId,
                params.message
            )
        )
    }

    data class Param(
        val userId: String,
        val message: String
    )
}