package com.dre.loyalty.features.home.data.entity.response

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class HomeResponse(
    @SerializedName("card")
    val cardResponse: CardResponse,
    @SerializedName("cashback")
    val cashBackResponse: CashBackResponse,
    @SerializedName("news")
    val newsResponse: NewsResponse
)