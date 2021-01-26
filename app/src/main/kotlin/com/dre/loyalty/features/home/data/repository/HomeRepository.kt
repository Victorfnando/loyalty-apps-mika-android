/*
 *
 * Created by Andreas on 1/26/21 5:13 PM
 * Copyright (c) 2021 . All rights reserved.
 * Last modified 1/25/21 3:15 PM
 * Github Link: https://github.com/oandrz
 * Email: oentoro.andreas@gmail.com
 *
 */

package com.dre.loyalty.features.home.data.repository

import com.dre.loyalty.core.exception.Failure
import com.dre.loyalty.core.extension.request
import com.dre.loyalty.core.functional.Either
import com.dre.loyalty.core.model.Home
import com.dre.loyalty.core.platform.NetworkHandler
import com.dre.loyalty.features.home.data.entity.mapper.HomeResponseMapper
import com.dre.loyalty.features.home.data.repository.datasource.cloud.HomeCloudDataSourceContract
import com.dre.loyalty.features.home.domain.HomeRepositoryContract
import com.dre.loyalty.features.home.domain.usecase.GetHomeDataUseCase.Param
import javax.inject.Inject

class HomeRepository @Inject constructor(
    private val networkHandler: NetworkHandler,
    private val cloudDataSource: HomeCloudDataSourceContract,
    private val mapper: HomeResponseMapper
) : HomeRepositoryContract {
    override fun getHomeData(param: Param): Either<Failure, Home> {
        return when(networkHandler.isNetworkAvailable()) {
            true -> {
                cloudDataSource.getHome(param).request {
                    mapper.transform(it)
                }
            }
            false -> Either.Left(Failure.NetworkConnection)
        }
    }
}