/*
 *
 * Created by Andreas on 1/29/21 3:05 PM
 * Copyright (c) 2021 . All rights reserved.
 * Last modified 1/29/21 3:05 PM
 * Github Link: https://github.com/oandrz
 * Email: oentoro.andreas@gmail.com
 *
 */

package com.dre.loyalty.features.authentication.data.entity.request

import com.google.gson.annotations.SerializedName

data class ResetPasswordRequest(
    @SerializedName("email")
    private val email: String,
    @SerializedName("password")
    private val password: String
)