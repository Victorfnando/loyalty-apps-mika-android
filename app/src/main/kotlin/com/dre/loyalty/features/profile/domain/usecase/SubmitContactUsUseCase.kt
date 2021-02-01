/*
 *
 * Created by Andreas on 2/1/21 10:19 AM
 * Copyright (c) 2021 . All rights reserved.
 * Last modified 2/1/21 10:19 AM
 * Github Link: https://github.com/oandrz
 * Email: oentoro.andreas@gmail.com
 *
 */

package com.dre.loyalty.features.profile.domain.usecase

import com.dre.loyalty.core.interactor.UseCase
import com.dre.loyalty.core.networking.exception.Failure
import com.dre.loyalty.core.networking.response.BasicResponse
import com.dre.loyalty.core.platform.functional.Either
import com.dre.loyalty.features.profile.data.entity.request.ContactUsRequest
import com.dre.loyalty.features.profile.domain.UserRepositoryContract
import com.dre.loyalty.features.profile.domain.usecase.SubmitContactUsUseCase.Param
import javax.inject.Inject

class SubmitContactUsUseCase @Inject constructor(
    private val repository: UserRepositoryContract
) : UseCase<BasicResponse, Param>() {

    override suspend fun run(params: Param): Either<Failure, BasicResponse> {
        return repository.submitContactUs(
            ContactUsRequest(
                params.userId,
                params.message,
                params.category
            )
        )
    }

    data class Param(
        val userId: String,
        val message: String,
        val category: String
    )
}