/*
 *
 * Created by Andreas on 1/26/21 5:13 PM
 * Copyright (c) 2021 . All rights reserved.
 * Last modified 1/25/21 3:13 PM
 * Github Link: https://github.com/oandrz
 * Email: oentoro.andreas@gmail.com
 *
 */

package com.dre.loyalty.features.home.data.repository.datasource.cloud

import com.dre.loyalty.core.response.BaseResponse
import com.dre.loyalty.features.home.data.entity.response.HomeResponse
import com.dre.loyalty.features.home.domain.usecase.GetHomeDataUseCase
import retrofit2.Call

interface HomeCloudDataSourceContract {
    fun getHome(param: GetHomeDataUseCase.Param): Call<BaseResponse<HomeResponse>>
}