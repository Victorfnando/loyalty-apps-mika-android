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

import com.dre.loyalty.features.authentication.data.repository.datasource.AuthenticationCloudDataSourceContract
import com.dre.loyalty.features.authentication.domain.AuthenticationRepositoryContract
import javax.inject.Inject

class AuthenticationRepository @Inject constructor(
    private val cloudDataSource: AuthenticationCloudDataSourceContract
) : AuthenticationRepositoryContract{
}