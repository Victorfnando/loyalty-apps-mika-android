/*
 *
 * Created by Andreas on 1/26/21 5:09 PM
 * Copyright (c) 2021 . All rights reserved.
 * Last modified 1/26/21 5:06 PM
 * Github Link: https://github.com/oandrz
 * Email: oentoro.andreas@gmail.com
 *
 */

package com.dre.loyalty.features.news.data.repository.datasource

import com.dre.loyalty.core.networking.response.Response
import com.dre.loyalty.features.news.data.entity.response.NewsDetailResponse
import com.dre.loyalty.features.news.data.entity.response.NewsResponse
import com.dre.loyalty.features.news.domain.usecase.GetNewsDetailUseCase
import retrofit2.Call

interface NewsCloudDataSourceContract {
    fun getNews(): Call<Response<List<NewsResponse>>>
    fun getNewsDetail(param: GetNewsDetailUseCase.Param): Call<Response<NewsDetailResponse>>
}