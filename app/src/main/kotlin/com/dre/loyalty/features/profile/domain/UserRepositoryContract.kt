/*
 *
 * Created by Andreas on 1/31/21 5:50 PM
 * Copyright (c) 2021 . All rights reserved.
 * Last modified 1/31/21 5:50 PM
 * Github Link: https://github.com/oandrz
 * Email: oentoro.andreas@gmail.com
 *
 */

package com.dre.loyalty.features.profile.domain

import com.dre.loyalty.core.model.User
import com.dre.loyalty.core.networking.exception.Failure
import com.dre.loyalty.core.networking.response.BasicResponse
import com.dre.loyalty.core.platform.functional.Either
import com.dre.loyalty.features.profile.data.entity.request.UpdateProfileRequest

interface UserRepositoryContract {
    fun getUser(userId: String): Either<Failure, User>
    fun changeProfileImage(uri: String): Either<Failure, String>
    fun updateProfile(requestUpdate: UpdateProfileRequest): Either<Failure, BasicResponse>
}