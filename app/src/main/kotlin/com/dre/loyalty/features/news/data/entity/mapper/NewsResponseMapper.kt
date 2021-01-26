/*
 *
 * Created by Andreas on 1/26/21 5:14 PM
 * Copyright (c) 2021 . All rights reserved.
 * Last modified 1/26/21 5:14 PM
 * Github Link: https://github.com/oandrz
 * Email: oentoro.andreas@gmail.com
 *
 */

package com.dre.loyalty.features.news.data.entity.mapper

import com.dre.loyalty.core.model.News
import com.dre.loyalty.core.response.BaseResponse
import com.dre.loyalty.features.news.data.entity.response.NewsListResponse
import com.dre.loyalty.features.news.data.entity.response.NewsResponse
import javax.inject.Inject

class NewsResponseMapper @Inject constructor() {
    fun transform(response: BaseResponse<NewsListResponse>): List<News> {
        return response.data.content.map { transform(it) }
    }

    private fun transform(response: NewsResponse): News {
        return News(
            response.id,
            response.title,
            response.desc,
            response.date,
            response.imageUrl
        )
    }
}