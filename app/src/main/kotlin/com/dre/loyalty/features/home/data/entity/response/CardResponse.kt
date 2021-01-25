package com.dre.loyalty.features.home.data.entity.response

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class CardResponse(
    @SerializedName("medicNumber")
    val id: String,
    @SerializedName("cardName")
    val cardName: String,
    @SerializedName("hospitalId")
    val hospitalId: String,
    @SerializedName("hospitalName")
    val hospitalName: String
)