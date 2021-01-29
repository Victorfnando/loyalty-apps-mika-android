package com.dre.loyalty.core.networking

import com.dre.loyalty.core.networking.response.Response
import com.dre.loyalty.features.news.data.entity.response.NewsDetailResponse
import com.dre.loyalty.features.news.data.entity.response.NewsResponse
import com.dre.loyalty.features.news.domain.usecase.GetNewsDetailUseCase
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

private const val NEWS_LIST_ENDPOINT = "news"
private const val NEWS_DETAIL_ENDPOINT = "news_detail"
interface NewsService {
    @GET(NEWS_LIST_ENDPOINT) fun getNewsList(): Call<Response<List<NewsResponse>>>
    @POST(NEWS_DETAIL_ENDPOINT) fun getNewsDetail(@Body param: GetNewsDetailUseCase.Param): Call<Response<NewsDetailResponse>>
}