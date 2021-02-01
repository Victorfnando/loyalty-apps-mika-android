/*
 *
 * Created by Andreas on 2/1/21 10:16 AM
 * Copyright (c) 2021 . All rights reserved.
 * Last modified 2/1/21 10:16 AM
 * Github Link: https://github.com/oandrz
 * Email: oentoro.andreas@gmail.com
 *
 */

package com.dre.loyalty.features.profile.data.entity.request

import com.google.gson.annotations.SerializedName

data class ContactUsRequest(
    @SerializedName("userId")
    val userId: String,
    @SerializedName("message")
    val message: String,
    @SerializedName("category")
    val category: String
)