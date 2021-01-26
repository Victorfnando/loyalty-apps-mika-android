package com.dre.loyalty.features.faq.data.repository.datasource

import com.dre.loyalty.core.service.FaqService
import com.dre.loyalty.features.faq.data.entity.response.FaqDataResponse
import com.dre.loyalty.features.faq.domain.usecase.GetFaqQuestion
import com.dre.loyalty.features.home.data.entity.response.BaseResponse
import retrofit2.Call
import javax.inject.Inject

class FaqCloudDataSource @Inject constructor(
    private val service: FaqService
) : FaqCloudDataSourceContract {
    override fun getFaq(param: GetFaqQuestion.Param): Call<BaseResponse<FaqDataResponse>> {
        return service.getFaq(param)
    }
}