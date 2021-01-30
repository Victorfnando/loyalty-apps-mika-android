package com.dre.loyalty.features.faq.data.repository.datasource

import com.dre.loyalty.features.faq.domain.usecase.GetFaqQuestion.Param
import com.dre.loyalty.core.networking.response.LoyaltyResponse
import com.dre.loyalty.features.faq.data.entity.response.FaqResponse
import retrofit2.Call

interface FaqCloudDataSourceContract {
    fun getFaq(param: Param): Call<LoyaltyResponse<List<FaqResponse>>>
}