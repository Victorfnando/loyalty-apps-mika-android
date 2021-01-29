/*
 *
 * Created by Andreas on 1/29/21 3:04 PM
 * Copyright (c) 2021 . All rights reserved.
 * Last modified 1/29/21 3:04 PM
 * Github Link: https://github.com/oandrz
 * Email: oentoro.andreas@gmail.com
 *
 */

package com.dre.loyalty.features.authentication.data.entity.response

import com.google.gson.annotations.SerializedName

data class ForgotPasswordEmailVerificationResponse(
    @SerializedName("attempts")
    val attempt: Int
)