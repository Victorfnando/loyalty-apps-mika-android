package com.dre.loyalty.core.networking

import com.dre.loyalty.core.networking.response.LoyaltyResponse
import com.dre.loyalty.features.faq.data.entity.response.FaqResponse
import retrofit2.Call
import retrofit2.http.GET

private const val FAQ_ENDPOINT = "faqs"
interface FaqService {
    @GET(FAQ_ENDPOINT) fun getFaq(): Call<LoyaltyResponse<List<FaqResponse>>>
}