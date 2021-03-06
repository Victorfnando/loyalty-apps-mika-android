package com.dre.loyalty.core.networking

import com.dre.loyalty.core.networking.response.LoyaltyResponse
import com.dre.loyalty.features.home.data.entity.response.HomeResponse
import com.dre.loyalty.features.home.domain.usecase.GetHomeDataUseCase.Param
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

private const val HOME_DATA_ENDPOINT = "home_data"
interface HomeService {
    @POST(HOME_DATA_ENDPOINT) fun getHomeData(@Body param: Param): Call<LoyaltyResponse<HomeResponse>>
}