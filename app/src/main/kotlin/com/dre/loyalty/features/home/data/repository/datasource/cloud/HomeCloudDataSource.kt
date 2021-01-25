package com.dre.loyalty.features.home.data.repository.datasource.cloud

import com.dre.loyalty.core.service.HomeService
import com.dre.loyalty.features.home.data.entity.response.BaseResponse
import com.dre.loyalty.features.home.data.entity.response.HomeResponse
import com.dre.loyalty.features.home.domain.usecase.GetHomeDataUseCase.*
import retrofit2.Call
import javax.inject.Inject

class HomeCloudDataSource @Inject constructor(
    private val service: HomeService
) : HomeCloudDataSourceContract {
    override fun getHome(param: Param): Call<BaseResponse<HomeResponse>> {
        return service.getHomeData(param)
    }
}