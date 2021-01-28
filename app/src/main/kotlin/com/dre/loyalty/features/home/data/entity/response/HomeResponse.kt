/*
 *
 * Created by Andreas on 1/26/21 5:13 PM
 * Copyright (c) 2021 . All rights reserved.
 * Last modified 1/25/21 4:04 PM
 * Github Link: https://github.com/oandrz
 * Email: oentoro.andreas@gmail.com
 *
 */

package com.dre.loyalty.features.home.data.entity.response

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class HomeResponse(
    @SerializedName("cards")
    val cardResponse: CardResponse,
    @SerializedName("cashback")
    val cashBackResponse: List<CashBackResponse>,
    @SerializedName("news")
    val newsResponse: List<NewsResponse>
)