/*
 *
 * Created by Andreas on 1/26/21 5:09 PM
 * Copyright (c) 2021 . All rights reserved.
 * Last modified 1/26/21 5:02 PM
 * Github Link: https://github.com/oandrz
 * Email: oentoro.andreas@gmail.com
 *
 */

package com.dre.loyalty.features.news.data.entity.response

import com.google.gson.annotations.SerializedName

data class NewsListResponse(
    @SerializedName("news")
    val content: List<NewsResponse>
)