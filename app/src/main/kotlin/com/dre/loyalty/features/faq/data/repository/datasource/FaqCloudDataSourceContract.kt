package com.dre.loyalty.features.faq.data.repository.datasource

import com.dre.loyalty.features.faq.data.entity.response.FaqDataResponse
import com.dre.loyalty.features.faq.domain.usecase.GetFaqQuestion.Param
import com.dre.loyalty.features.home.data.entity.response.BaseResponse
import retrofit2.Call

interface FaqCloudDataSourceContract {
    fun getFaq(param: Param): Call<BaseResponse<FaqDataResponse>>
}