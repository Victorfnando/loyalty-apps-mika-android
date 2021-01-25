package com.dre.loyalty.features.home.data.entity.response

import com.google.gson.annotations.SerializedName

data class CashBackResponse(
    @SerializedName("receiptId")
    val id: String,
    @SerializedName("cashback")
    val cashBack: Long,
    @SerializedName("date")
    val date: String,
    @SerializedName("hospitalName")
    val hospitalName: String,
    @SerializedName("hospitalId")
    val hospitalId: String
)