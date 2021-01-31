/*
 *
 * Created by Andreas on 1/31/21 5:50 PM
 * Copyright (c) 2021 . All rights reserved.
 * Last modified 1/31/21 5:50 PM
 * Github Link: https://github.com/oandrz
 * Email: oentoro.andreas@gmail.com
 *
 */

package com.dre.loyalty.features.profile.data.repository

import com.dre.loyalty.core.model.User
import com.dre.loyalty.core.networking.exception.Failure
import com.dre.loyalty.core.platform.NetworkHandler
import com.dre.loyalty.core.platform.extension.request
import com.dre.loyalty.core.platform.functional.Either
import com.dre.loyalty.features.profile.data.entity.mapper.UserResponseMapper
import com.dre.loyalty.features.profile.data.repository.datasource.UserCloudDataSourceContract
import com.dre.loyalty.features.profile.domain.UserRepositoryContract
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val networkHandler: NetworkHandler,
    private val cloudDataSource: UserCloudDataSourceContract,
    private val responseMapper: UserResponseMapper
) : UserRepositoryContract {
    override fun getUser(userId: String): Either<Failure, User> {
        return when(networkHandler.isNetworkAvailable()) {
            true -> {
                cloudDataSource.getUser(userId).request {
                    responseMapper.transform(it.data)
                }
            }
            false -> Either.Left(Failure.NetworkConnection)
        }
    }

    override fun changeProfileImage(uri: String): Either<Failure, String> {
        return when(networkHandler.isNetworkAvailable()) {
            true -> {
                cloudDataSource.changeProfileImage(uri).request {
                    it.data.imageUri
                }
            }
            false -> Either.Left(Failure.NetworkConnection)
        }
    }
}