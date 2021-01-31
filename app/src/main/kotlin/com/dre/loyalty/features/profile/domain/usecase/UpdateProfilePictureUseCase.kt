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
import com.dre.loyalty.features.profile.domain.UserRepositoryContract
import javax.inject.Inject

class UpdateProfilePictureUseCase @Inject constructor(
    private val repository: UserRepositoryContract
) : UseCase<String, String>() {
    override suspend fun run(params: String): Either<Failure, String> {
        return repository.changeProfileImage(params)
    }
}