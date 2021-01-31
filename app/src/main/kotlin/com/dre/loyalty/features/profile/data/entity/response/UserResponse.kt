/*
 *
 * Created by Andreas on 1/31/21 6:45 PM
 * Copyright (c) 2021 . All rights reserved.
 * Last modified 1/31/21 6:44 PM
 * Github Link: https://github.com/oandrz
 * Email: oentoro.andreas@gmail.com
 *
 */

package com.dre.loyalty.features.profile.data.entity.response

import com.google.gson.annotations.SerializedName

data class UserResponse(
    @SerializedName("userId")
    val userId: String,
    @SerializedName("firstName")
    val firstName: String,
    @SerializedName("lastName")
    val lastName: String,
    @SerializedName("cardId")
    val cardId: String,
    @SerializedName("phone")
    val phone: String,
    @SerializedName("gender")
    val gender: String,
    @SerializedName("birthdate")
    val dob: String,
    @SerializedName("profileImage")
    val profileImage: String,
    @SerializedName("email")
    val email: String
)