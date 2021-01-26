package com.dre.loyalty.core.service

import com.dre.loyalty.features.faq.data.entity.response.FaqDataResponse
import com.dre.loyalty.features.faq.domain.usecase.GetFaqQuestion.Param
import com.dre.loyalty.features.home.data.entity.response.BaseResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

private const val FAQ_ENDPOINT = "faqs"
interface FaqService {
    @POST(FAQ_ENDPOINT) fun getFaq(@Body param: Param): Call<BaseResponse<FaqDataResponse>>
}