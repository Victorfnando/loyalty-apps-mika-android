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
import com.dre.loyalty.core.networking.response.LoyaltyResponse
import com.dre.loyalty.features.news.data.entity.response.NewsDetailResponse
import com.dre.loyalty.features.news.data.entity.response.NewsResponse
import javax.inject.Inject

class NewsResponseMapper @Inject constructor() {
    fun transform(loyaltyResponse: LoyaltyResponse<List<NewsResponse>>): List<News> {
        return loyaltyResponse.data.map { transform(it) }
    }

    fun transform(loyaltyResponse: LoyaltyResponse<NewsDetailResponse>): News {
        return News(
            loyaltyResponse.data.news.id,
            loyaltyResponse.data.news.title,
            loyaltyResponse.data.news.desc,
            loyaltyResponse.data.news.date,
            loyaltyResponse.data.news.imageUrl,
            loyaltyResponse.data.relatedNews.map { transform(it) },
        )
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