/*
 *
 * Created by Andreas on 1/28/21 6:20 PM
 * Copyright (c) 2021 . All rights reserved.
 * Last modified 1/28/21 6:20 PM
 * Github Link: https://github.com/oandrz
 * Email: oentoro.andreas@gmail.com
 *
 */

package com.dre.loyalty.features.authentication.data.entity.request

import com.google.gson.annotations.SerializedName

data class VerifyCodeRequest(
    @SerializedName("email")
    val email: String,
    @SerializedName("code")
    val code: String
)