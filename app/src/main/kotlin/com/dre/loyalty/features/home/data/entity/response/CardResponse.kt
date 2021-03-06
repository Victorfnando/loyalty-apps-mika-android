/*
 *
 * Created by Andreas on 1/26/21 5:13 PM
 * Copyright (c) 2021 . All rights reserved.
 * Last modified 1/25/21 1:40 PM
 * Github Link: https://github.com/oandrz
 * Email: oentoro.andreas@gmail.com
 *
 */

package com.dre.loyalty.features.home.data.entity.response

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class CardResponse(
    @SerializedName("cardName")
    val cardName: String,
    @SerializedName("birthdate")
    val birthDate: String,
    @SerializedName("memberSince")
    val memberSince: String,
    @SerializedName("cardImageUrl")
    val backgroundImageUrl: String
)