package com.dre.loyalty.core.networking

import com.dre.loyalty.core.networking.response.LoyaltyResponse
import com.dre.loyalty.features.news.data.entity.response.NewsDetailResponse
import com.dre.loyalty.features.news.data.entity.response.NewsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

private const val NEWS_LIST_ENDPOINT = "news"
private const val NEWS_DETAIL_ENDPOINT = "news_detail/{newsId}"
interface NewsService {
    @GET(NEWS_LIST_ENDPOINT) fun getNewsList(): Call<LoyaltyResponse<List<NewsResponse>>>
    @GET(NEWS_DETAIL_ENDPOINT) fun getNewsDetail(@Path("newsId") id: String): Call<LoyaltyResponse<NewsDetailResponse>>
}