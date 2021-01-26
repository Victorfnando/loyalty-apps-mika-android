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

import com.dre.loyalty.core.response.BaseResponse
import com.dre.loyalty.features.news.data.entity.response.NewsListResponse
import com.dre.loyalty.features.news.domain.usecase.GetNewsListUseCase.Param
import retrofit2.Call

interface NewsCloudDataSourceContract {
    fun getNews(param: Param): Call<BaseResponse<NewsListResponse>>
}