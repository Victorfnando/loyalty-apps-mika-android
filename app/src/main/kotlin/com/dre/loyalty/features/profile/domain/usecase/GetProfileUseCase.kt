/*
 *
 * Created by Andreas on 1/31/21 6:52 PM
 * Copyright (c) 2021 . All rights reserved.
 * Last modified 1/31/21 6:52 PM
 * Github Link: https://github.com/oandrz
 * Email: oentoro.andreas@gmail.com
 *
 */

package com.dre.loyalty.features.profile.domain.usecase

import com.dre.loyalty.core.interactor.UseCase
import com.dre.loyalty.core.model.User
import com.dre.loyalty.core.networking.exception.Failure
import com.dre.loyalty.core.platform.functional.Either
import com.dre.loyalty.features.profile.domain.UserRepositoryContract
import javax.inject.Inject

class GetProfileUseCase @Inject constructor(
    private val repository: UserRepositoryContract
) : UseCase<User, String>() {
    override suspend fun run(params: String): Either<Failure, User> {
        return repository.getUser(params)
    }
}