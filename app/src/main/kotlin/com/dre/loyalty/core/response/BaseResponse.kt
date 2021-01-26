/*
 *
 * Created by Andreas on 1/26/21 5:13 PM
 * Copyright (c) 2021 . All rights reserved.
 * Last modified 1/25/21 1:40 PM
 * Github Link: https://github.com/oandrz
 * Email: oentoro.andreas@gmail.com
 *
 */

package com.dre.loyalty.core.response

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class BaseResponse<T>(
    @SerializedName("statusMessage")
    val statusMessage: String,
    @SerializedName("statusCode")
    val statusCode: String,
    @SerializedName("data")
    val data: T
)
