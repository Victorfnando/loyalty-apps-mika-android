/*
 *
 * Created by Andreas on 1/31/21 6:42 PM
 * Copyright (c) 2021 . All rights reserved.
 * Last modified 1/31/21 6:42 PM
 * Github Link: https://github.com/oandrz
 * Email: oentoro.andreas@gmail.com
 *
 */

package com.dre.loyalty.features.profile.data.entity.mapper

import com.dre.loyalty.core.model.User
import com.dre.loyalty.features.profile.data.entity.response.UserResponse
import javax.inject.Inject

class UserResponseMapper @Inject constructor() {

    fun transform(response: UserResponse): User {
        return User(
            response.firstName,
            response.lastName,
            response.cardId,
            response.phone,
            response.gender,
            response.dob,
            response.profileImage,
            response.email,
            ""
        )
    }
}