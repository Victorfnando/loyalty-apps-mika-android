/*
 *
 * Created by Andreas on 1/26/21 5:09 PM
 * Copyright (c) 2021 . All rights reserved.
 * Last modified 1/26/21 4:59 PM
 * Github Link: https://github.com/oandrz
 * Email: oentoro.andreas@gmail.com
 *
 */

package com.dre.loyalty.features.news.data.repository

import com.dre.loyalty.core.exception.Failure
import com.dre.loyalty.core.extension.request
import com.dre.loyalty.core.functional.Either
import com.dre.loyalty.core.model.News
import com.dre.loyalty.core.platform.NetworkHandler
import com.dre.loyalty.features.news.data.entity.mapper.NewsResponseMapper
import com.dre.loyalty.features.news.data.repository.datasource.NewsCloudDataSourceContract
import com.dre.loyalty.features.news.domain.NewsRepositoryContract
import com.dre.loyalty.features.news.domain.usecase.GetNewsListUseCase
import javax.inject.Inject

class NewsRepository @Inject constructor(
    private val networkHandler: NetworkHandler,
    private val cloud: NewsCloudDataSourceContract,
    private val responseMapper: NewsResponseMapper
) : NewsRepositoryContract {
    override fun getNewsList(param: GetNewsListUseCase.Param): Either<Failure, List<News>> {
        return when(networkHandler.isNetworkAvailable()) {
            true -> {
                cloud.getNews(param).request {
                    responseMapper.transform(it)
                }
            }
            false -> Either.Left(Failure.NetworkConnection)
        }
    }
}