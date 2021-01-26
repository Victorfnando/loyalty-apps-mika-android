/*
 *
 * Created by Andreas on 1/26/21 5:09 PM
 * Copyright (c) 2021 . All rights reserved.
 * Last modified 1/26/21 5:04 PM
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
import com.dre.loyalty.features.news.domain.usecase.GetNewsListUseCase.Param
import com.google.gson.annotations.SerializedName
import javax.inject.Inject

class GetNewsListUseCase @Inject constructor(
    private val repository: NewsRepositoryContract
) : UseCase<List<News>, Param>() {

    override suspend fun run(params: Param): Either<Failure, List<News>> {
        return repository.getNewsList(params)
    }

    data class Param(
        @SerializedName("user_id")
        val userId: String,
        @SerializedName("token")
        val token: String
    )
}