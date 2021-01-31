/*
 *
 * Created by Andreas on 1/31/21 8:26 PM
 * Copyright (c) 2021 . All rights reserved.
 * Last modified 1/31/21 8:26 PM
 * Github Link: https://github.com/oandrz
 * Email: oentoro.andreas@gmail.com
 *
 */

package com.dre.loyalty.features.profile.data.entity.request

import com.google.gson.annotations.SerializedName

data class UpdateProfileRequest(
    @SerializedName("userId")
    val userId: String,
    @SerializedName("message")
    val message: String
)