/*
 *
 * Created by Andreas on 1/28/21 6:07 PM
 * Copyright (c) 2021 . All rights reserved.
 * Last modified 1/28/21 6:07 PM
 * Github Link: https://github.com/oandrz
 * Email: oentoro.andreas@gmail.com
 *
 */

package com.dre.loyalty.core.networking.response

import com.google.gson.annotations.SerializedName

data class BasicResponse(
    @SerializedName("statusMessage")
    val statusMessage: String,
    @SerializedName("statusCode")
    val statusCode: String
)