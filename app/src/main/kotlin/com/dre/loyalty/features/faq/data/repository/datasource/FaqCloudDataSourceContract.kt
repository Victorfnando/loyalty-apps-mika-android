package com.dre.loyalty.features.faq.data.repository.datasource

import com.dre.loyalty.core.networking.response.LoyaltyResponse
import com.dre.loyalty.features.faq.data.entity.response.FaqResponse
import retrofit2.Call

interface FaqCloudDataSourceContract {
    fun getFaq(): Call<LoyaltyResponse<List<FaqResponse>>>
}