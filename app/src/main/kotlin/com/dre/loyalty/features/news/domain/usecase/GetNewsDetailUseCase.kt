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

import com.dre.loyalty.core.networking.exception.Failure
import com.dre.loyalty.core.platform.functional.Either
import com.dre.loyalty.core.interactor.UseCase
import com.dre.loyalty.core.model.News
import com.dre.loyalty.features.news.domain.NewsRepositoryContract
import com.google.gson.annotations.SerializedName
import javax.inject.Inject

class GetNewsDetailUseCase @Inject constructor(
    private val repository: NewsRepositoryContract
) : UseCase<News, String>() {

    override suspend fun run(params: String): Either<Failure, News> {
        return repository.getNewsDetail(params)
    }
}