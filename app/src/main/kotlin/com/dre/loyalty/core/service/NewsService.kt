package com.dre.loyalty.core.service

import com.dre.loyalty.core.response.BaseResponse
import com.dre.loyalty.features.news.data.entity.response.NewsDetailResponse
import com.dre.loyalty.features.news.data.entity.response.NewsListResponse
import com.dre.loyalty.features.news.domain.usecase.GetNewsDetailUseCase
import com.dre.loyalty.features.news.domain.usecase.GetNewsListUseCase.Param
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

private const val NEWS_LIST_ENDPOINT = "news"
private const val NEWS_DETAIL_ENDPOINT = "news_detail"
interface NewsService {
    @POST(NEWS_LIST_ENDPOINT) fun getNewsList(@Body param: Param): Call<BaseResponse<NewsListResponse>>
    @POST(NEWS_DETAIL_ENDPOINT) fun getNewsDetail(@Body param: GetNewsDetailUseCase.Param): Call<BaseResponse<NewsDetailResponse>>
}