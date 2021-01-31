/*
 *
 * Created by Andreas on 1/31/21 10:09 PM
 * Copyright (c) 2021 . All rights reserved.
 * Last modified 1/31/21 10:09 PM
 * Github Link: https://github.com/oandrz
 * Email: oentoro.andreas@gmail.com
 *
 */

package com.dre.loyalty.features.authentication.data.entity.request

import com.google.gson.annotations.SerializedName

data class UpdatePasswordRequest(
    @SerializedName("userId")
    val userId: String,
    @SerializedName("password")
    val password: String,
    @SerializedName("newPassword")
    val newPassword: String
)