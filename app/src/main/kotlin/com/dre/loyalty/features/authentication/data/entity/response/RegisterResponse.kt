/*
 *
 * Created by Andreas on 1/28/21 11:47 AM
 * Copyright (c) 2021 . All rights reserved.
 * Last modified 1/28/21 11:47 AM
 * Github Link: https://github.com/oandrz
 * Email: oentoro.andreas@gmail.com
 *
 */

package com.dre.loyalty.features.authentication.data.entity.response

import com.google.gson.annotations.SerializedName

data class RegisterResponse(
    @SerializedName("isSuccess")
    val isSuccess: Boolean
)
