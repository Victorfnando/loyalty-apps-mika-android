package com.dre.loyalty.core.networking

import com.dre.loyalty.features.faq.domain.usecase.GetFaqQuestion.Param
import com.dre.loyalty.core.networking.response.Response
import com.dre.loyalty.features.faq.data.entity.response.FaqResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

private const val FAQ_ENDPOINT = "faqs"
interface FaqService {
    @POST(FAQ_ENDPOINT) fun getFaq(@Body param: Param): Call<Response<List<FaqResponse>>>
}