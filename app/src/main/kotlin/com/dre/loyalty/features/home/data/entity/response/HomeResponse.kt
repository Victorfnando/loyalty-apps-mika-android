package com.dre.loyalty.features.home.data.entity.response

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class HomeResponse(
    @SerializedName("cards")
    val cardResponse: List<CardResponse>,
    @SerializedName("cashback")
    val cashBackResponse: List<CashBackResponse>,
    @SerializedName("news")
    val newsResponse: List<NewsResponse>
)