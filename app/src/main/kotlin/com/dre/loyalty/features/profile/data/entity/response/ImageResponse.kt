/*
 *
 * Created by Andreas on 1/31/21 7:38 PM
 * Copyright (c) 2021 . All rights reserved.
 * Last modified 1/31/21 7:38 PM
 * Github Link: https://github.com/oandrz
 * Email: oentoro.andreas@gmail.com
 *
 */

package com.dre.loyalty.features.profile.data.entity.response

import com.google.gson.annotations.SerializedName

data class ImageResponse(
    @SerializedName("photoPath")
    val imageUri: String
)