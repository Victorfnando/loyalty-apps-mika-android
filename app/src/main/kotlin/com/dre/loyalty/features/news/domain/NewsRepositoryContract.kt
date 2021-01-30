/*
 *
 * Created by Andreas on 1/26/21 5:09 PM
 * Copyright (c) 2021 . All rights reserved.
 * Last modified 1/26/21 4:59 PM
 * Github Link: https://github.com/oandrz
 * Email: oentoro.andreas@gmail.com
 *
 */

package com.dre.loyalty.features.news.domain

import com.dre.loyalty.core.networking.exception.Failure
import com.dre.loyalty.core.platform.functional.Either
import com.dre.loyalty.core.model.News
import com.dre.loyalty.features.news.domain.usecase.GetNewsDetailUseCase

interface NewsRepositoryContract {
    fun getNewsList(): Either<Failure, List<News>>
    fun getNewsDetail(id: String): Either<Failure, News>
}