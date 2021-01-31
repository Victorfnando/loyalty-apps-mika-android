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

import com.dre.loyalty.core.networking.exception.Failure
import com.dre.loyalty.core.platform.extension.request
import com.dre.loyalty.core.platform.functional.Either
import com.dre.loyalty.core.platform.functional.getOrElseNull
import com.dre.loyalty.core.model.AuthCertificate
import com.dre.loyalty.core.platform.NetworkHandler
import com.dre.loyalty.core.networking.response.BasicResponse
import com.dre.loyalty.features.authentication.data.entity.mapper.AuthMapper
import com.dre.loyalty.features.authentication.data.entity.request.*
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

    override fun login(request: LoginRequest): Either<Failure, AuthCertificate> {
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

    override fun register(request: RegisterRequest): Either<Failure, BasicResponse> {
        return when(networkHandler.isNetworkAvailable()) {
            true -> {
                return cloudDataSource.register(request).request { it }
            }
            false -> Either.Left(Failure.NetworkConnection)
        }
    }

    override fun checkMail(request: EmailRequest): Either<Failure, BasicResponse> {
        return when(networkHandler.isNetworkAvailable()) {
            true -> {
                return cloudDataSource.checkMail(request).request { it }
            }
            false -> Either.Left(Failure.NetworkConnection)
        }
    }

    override fun verifyCode(request: VerifyCodeRequest): Either<Failure, BasicResponse> {
        return when(networkHandler.isNetworkAvailable()) {
            true -> {
                return cloudDataSource.verifyCode(request).request { it }
            }
            false -> Either.Left(Failure.NetworkConnection)
        }
    }

    override fun forgotPasswordCheckMail(request: EmailRequest): Either<Failure, Int> {
        return when(networkHandler.isNetworkAvailable()) {
            true -> {
                return cloudDataSource.forgotPasswordCheckMail(request).request { it.data.attempt }
            }
            false -> Either.Left(Failure.NetworkConnection)
        }
    }

    override fun forgotPasswordVerifyCode(request: VerifyCodeRequest): Either<Failure, BasicResponse> {
        return when(networkHandler.isNetworkAvailable()) {
            true -> {
                return cloudDataSource.forgotPasswordVerifyCode(request).request { it }
            }
            false -> Either.Left(Failure.NetworkConnection)
        }
    }

    override fun resetPassword(request: ResetPasswordRequest): Either<Failure, BasicResponse> {
        return when(networkHandler.isNetworkAvailable()) {
            true -> {
                return cloudDataSource.resetPassword(request).request { it }
            }
            false -> Either.Left(Failure.NetworkConnection)
        }
    }

    override fun updatePassword(request: UpdatePasswordRequest): Either<Failure, BasicResponse> {
        return when(networkHandler.isNetworkAvailable()) {
            true -> {
                return cloudDataSource.updatePassword(request).request { it }
            }
            false -> Either.Left(Failure.NetworkConnection)
        }
    }

}