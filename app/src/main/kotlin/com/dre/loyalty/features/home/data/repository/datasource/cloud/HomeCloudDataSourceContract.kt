package com.dre.loyalty.features.home.data.repository.datasource.cloud

import com.dre.loyalty.features.home.data.entity.response.BaseResponse
import com.dre.loyalty.features.home.data.entity.response.HomeResponse
import com.dre.loyalty.features.home.domain.usecase.GetHomeDataUseCase
import retrofit2.Call

interface HomeCloudDataSourceContract {
    fun getHome(param: GetHomeDataUseCase.Param): Call<BaseResponse<HomeResponse>>
}