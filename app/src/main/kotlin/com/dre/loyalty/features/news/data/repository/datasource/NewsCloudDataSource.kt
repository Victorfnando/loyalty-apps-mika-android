/*
 *
 * Created by Andreas on 1/26/21 5:09 PM
 * Copyright (c) 2021 . All rights reserved.
 * Last modified 1/26/21 5:07 PM
 * Github Link: https://github.com/oandrz
 * Email: oentoro.andreas@gmail.com
 *
 */

package com.dre.loyalty.features.news.data.repository.datasource

import com.dre.loyalty.core.service.NewsService
import com.dre.loyalty.core.response.Response
import com.dre.loyalty.features.news.data.entity.response.NewsDetailResponse
import com.dre.loyalty.features.news.data.entity.response.NewsListResponse
import com.dre.loyalty.features.news.data.entity.response.NewsResponse
import com.dre.loyalty.features.news.domain.usecase.GetNewsDetailUseCase
import com.dre.loyalty.features.news.domain.usecase.GetNewsListUseCase
import retrofit2.Call
import javax.inject.Inject

class NewsCloudDataSource @Inject constructor(
    private val service: NewsService
) : NewsCloudDataSourceContract {
    override fun getNews(param: GetNewsListUseCase.Param): Call<Response<List<NewsResponse>>> {
        return service.getNewsList(param)
    }

    override fun getNewsDetail(param: GetNewsDetailUseCase.Param): Call<Response<NewsDetailResponse>> {
        return service.getNewsDetail(param)
    }
}