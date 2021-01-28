/*
 *
 * Created by Andreas on 1/28/21 6:04 PM
 * Copyright (c) 2021 . All rights reserved.
 * Last modified 1/28/21 6:04 PM
 * Github Link: https://github.com/oandrz
 * Email: oentoro.andreas@gmail.com
 *
 */

package com.dre.loyalty.features.authentication.data.entity.request

import com.google.gson.annotations.SerializedName

data class RegisterRequest(
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
    val birthDate: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("password")
    val password: String
)
