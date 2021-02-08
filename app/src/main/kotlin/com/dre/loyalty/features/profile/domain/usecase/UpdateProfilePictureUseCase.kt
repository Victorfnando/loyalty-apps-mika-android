/*
 *
 * Created by Andreas on 1/31/21 7:41 PM
 * Copyright (c) 2021 . All rights reserved.
 * Last modified 1/31/21 7:41 PM
 * Github Link: https://github.com/oandrz
 * Email: oentoro.andreas@gmail.com
 *
 */

package com.dre.loyalty.features.profile.domain.usecase

import com.dre.loyalty.core.interactor.UseCase
import com.dre.loyalty.core.networking.exception.Failure
import com.dre.loyalty.core.platform.functional.Either
import com.dre.loyalty.features.profile.data.entity.request.UpdateProfileImageRequest
import com.dre.loyalty.features.profile.domain.UserRepositoryContract
import com.dre.loyalty.features.profile.domain.usecase.UpdateProfilePictureUseCase.Param
import javax.inject.Inject

class UpdateProfilePictureUseCase @Inject constructor(
    private val repository: UserRepositoryContract
) : UseCase<String, Param>() {
    override suspend fun run(params: Param): Either<Failure, String> {
        return repository.changeProfileImage(
            UpdateProfileImageRequest(params.userId, params.uri)
        )
    }

    data class Param(
        val userId: Int,
        val uri: String
    )
}