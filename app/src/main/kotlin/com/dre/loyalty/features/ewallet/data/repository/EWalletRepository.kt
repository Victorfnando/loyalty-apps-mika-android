/*
 *
 * Created by Andreas on 1/31/21 3:41 PM
 * Copyright (c) 2021 . All rights reserved.
 * Last modified 1/31/21 3:41 PM
 * Github Link: https://github.com/oandrz
 * Email: oentoro.andreas@gmail.com
 *
 */

package com.dre.loyalty.features.ewallet.data.repository

import com.dre.loyalty.core.model.EWallet
import com.dre.loyalty.core.networking.exception.Failure
import com.dre.loyalty.core.platform.NetworkHandler
import com.dre.loyalty.core.platform.extension.request
import com.dre.loyalty.core.platform.functional.Either
import com.dre.loyalty.features.ewallet.data.entity.mapper.EWalletResponseMapper
import com.dre.loyalty.features.ewallet.data.repository.datasource.EWalletCloudDataSourceContract
import com.dre.loyalty.features.ewallet.domain.EWalletRepositoryContract
import javax.inject.Inject

class EWalletRepository @Inject constructor(
    private val networkHandler: NetworkHandler,
    private val cloudDataSource: EWalletCloudDataSourceContract,
    private val responseMapper: EWalletResponseMapper
) : EWalletRepositoryContract {

    override fun getEWallet(): Either<Failure, List<EWallet>> {
        return when(networkHandler.isNetworkAvailable()) {
            true -> {
                cloudDataSource.getWalletList().request {
                    responseMapper.transform(it.data)
                }
            }
            false -> Either.Left(Failure.NetworkConnection)
        }
    }
}