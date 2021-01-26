/*
 *
 * Created by Andreas on 1/26/21 7:29 PM
 * Copyright (c) 2021 . All rights reserved.
 * Last modified 1/26/21 7:29 PM
 * Github Link: https://github.com/oandrz
 * Email: oentoro.andreas@gmail.com
 *
 */

package com.dre.loyalty.features.news.data.entity.response

import com.google.gson.annotations.SerializedName

data class NewsDetailResponse(
    @SerializedName("news")
    val news: NewsResponse,
    @SerializedName("relatedNews")
    val relatedNews: List<NewsResponse>
)