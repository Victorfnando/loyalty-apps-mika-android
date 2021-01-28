/*
 *
 * Created by Andreas on 1/27/21 1:55 PM
 * Copyright (c) 2021 . All rights reserved.
 * Last modified 1/27/21 1:55 PM
 * Github Link: https://github.com/oandrz
 * Email: oentoro.andreas@gmail.com
 *
 */

package com.dre.loyalty.features.authentication.data.repository

import com.dre.loyalty.core.exception.Failure
import com.dre.loyalty.core.extension.request
import com.dre.loyalty.core.functional.Either
import com.dre.loyalty.core.functional.getOrElseNull
import com.dre.loyalty.core.model.AuthCertificate
import com.dre.loyalty.core.platform.NetworkHandler
import com.dre.loyalty.features.authentication.data.entity.mapper.AuthMapper
import com.dre.loyalty.features.authentication.data.entity.request.LoginRequest
import com.dre.loyalty.features.authentication.data.repository.datasource.cloud.AuthenticationCloudDataSourceContract
import com.dre.loyalty.features.authentication.data.repository.datasource.local.AuthenticationLocalDataSourceContract
import com.dre.loyalty.features.authentication.domain.AuthenticationRepositoryContract
import javax.inject.Inject

class AuthenticationRepository @Inject constructor(
    private val networkHandler: NetworkHandler,
    private val cloudDataSource: AuthenticationCloudDataSourceContract,
    private val localDataSourceContract: AuthenticationLocalDataSourceContract,
    private val mapper: AuthMapper
) : AuthenticationRepositoryContract {
    override suspend fun login(request: LoginRequest): Either<Failure, AuthCertificate> {
        return when(networkHandler.isNetworkAvailable()) {
            true -> {
                val result = cloudDataSource.login(request).request {
                    mapper.transform(it.data)
                }
                result.getOrElseNull()?.let {
                    localDataSourceContract.userId = it.id
                    localDataSourceContract.token = it.token
                }
                result
            }
            false -> Either.Left(Failure.NetworkConnection)
        }
    }

}