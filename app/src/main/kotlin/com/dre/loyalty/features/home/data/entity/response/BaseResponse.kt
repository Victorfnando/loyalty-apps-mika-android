package com.dre.loyalty.features.home.data.entity.response

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class BaseResponse<T>(
    @SerializedName("statusMessage")
    val statusMessage: String,
    @SerializedName("statusCode")
    val statusCode: String,
    @SerializedName("data")
    val data: T
)
