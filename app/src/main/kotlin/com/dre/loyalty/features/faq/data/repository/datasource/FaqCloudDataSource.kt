package com.dre.loyalty.features.faq.data.repository.datasource

import com.dre.loyalty.core.networking.FaqService
import com.dre.loyalty.features.faq.domain.usecase.GetFaqQuestion
import com.dre.loyalty.core.networking.response.LoyaltyResponse
import com.dre.loyalty.features.faq.data.entity.response.FaqResponse
import retrofit2.Call
import javax.inject.Inject

class FaqCloudDataSource @Inject constructor(
    private val service: FaqService
) : FaqCloudDataSourceContract {
    override fun getFaq(param: GetFaqQuestion.Param): Call<LoyaltyResponse<List<FaqResponse>>> {
        return service.getFaq(param)
    }
}