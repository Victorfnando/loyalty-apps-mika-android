/*
 *
 * Created by Andreas on 1/30/21 2:59 PM
 * Copyright (c) 2021 . All rights reserved.
 * Last modified 1/30/21 2:59 PM
 * Github Link: https://github.com/oandrz
 * Email: oentoro.andreas@gmail.com
 *
 */

package com.dre.loyalty.features.cashback.data.repository

import com.dre.loyalty.core.model.CashBack
import com.dre.loyalty.core.networking.exception.Failure
import com.dre.loyalty.core.platform.NetworkHandler
import com.dre.loyalty.core.platform.extension.request
import com.dre.loyalty.core.platform.functional.Either
import com.dre.loyalty.features.cashback.data.entity.mapper.CashBackListResponseMapper
import com.dre.loyalty.features.cashback.data.entity.request.CashBackRequest
import com.dre.loyalty.features.cashback.data.repository.datasource.CashBackListCloudDataSourceContract
import com.dre.loyalty.features.cashback.domain.CashBackListRepositoryContract
import javax.inject.Inject

class CashBackListRepository @Inject constructor(
    private val networkHandler: NetworkHandler,
    private val listCloudDataSource: CashBackListCloudDataSourceContract,
    private val responseMapper: CashBackListResponseMapper
) : CashBackListRepositoryContract {
    override fun getCashBackList(request: CashBackRequest): Either<Failure, List<CashBack>> {
        return when(networkHandler.isNetworkAvailable()) {
            true -> {
                listCloudDataSource.getCashBackList(request).request {
                    responseMapper.transform(it.data)
                }
            }
            false -> Either.Left(Failure.NetworkConnection)
        }
    }
}