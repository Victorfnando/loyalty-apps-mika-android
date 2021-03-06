/*
 *
 * Created by Andreas on 1/26/21 5:13 PM
 * Copyright (c) 2021 . All rights reserved.
 * Last modified 1/25/21 4:20 PM
 * Github Link: https://github.com/oandrz
 * Email: oentoro.andreas@gmail.com
 *
 */

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