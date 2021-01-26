/*
 *
 * Created by Andreas on 1/26/21 5:09 PM
 * Copyright (c) 2021 . All rights reserved.
 * Last modified 1/26/21 5:01 PM
 * Github Link: https://github.com/oandrz
 * Email: oentoro.andreas@gmail.com
 *
 */

package com.dre.loyalty.features.news.data.entity.response

import com.google.gson.annotations.SerializedName

data class NewsResponse(
    @SerializedName("newsId")
    val id: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("description")
    val desc: String,
    @SerializedName("dateTime")
    val date: String,
    @SerializedName("imgUrl")
    val imageUrl: String
)
