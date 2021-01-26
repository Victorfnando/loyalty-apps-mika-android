/*
 *
 * Created by Andreas on 1/26/21 7:26 PM
 * Copyright (c) 2021 . All rights reserved.
 * Last modified 1/26/21 7:26 PM
 * Github Link: https://github.com/oandrz
 * Email: oentoro.andreas@gmail.com
 *
 */

package com.dre.loyalty.features.news.domain.usecase

import com.dre.loyalty.core.exception.Failure
import com.dre.loyalty.core.functional.Either
import com.dre.loyalty.core.interactor.UseCase
import com.dre.loyalty.core.model.News
import com.dre.loyalty.features.news.domain.NewsRepositoryContract
import com.google.gson.annotations.SerializedName
import javax.inject.Inject

class GetNewsDetailUseCase @Inject constructor(
    private val repository: NewsRepositoryContract
) : UseCase<News, GetNewsDetailUseCase.Param>() {

    override suspend fun run(params: Param): Either<Failure, News> {
        return repository.getNewsDetail(params)
    }

    data class Param(
        @SerializedName("user_id")
        private val userId: String,
        @SerializedName("news_id")
        private val newsId: String,
        @SerializedName("token")
        private val token: String
    )
}