package com.dre.loyalty.features.home.data.entity.response

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class NewsResponse(
    @SerializedName("newsId")
    val id: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("date")
    val dateTime: String,
    @SerializedName("imgUrl")
    val imgUrl: String
)