package com.dre.loyalty.features.faq.data.entity.mapper

import com.dre.loyalty.features.faq.data.entity.response.FaqResponse
import com.dre.loyalty.features.faq.domain.entity.FrequentlyAskedQuestion
import com.dre.loyalty.core.networking.response.LoyaltyResponse
import javax.inject.Inject

class FaqResponseMapper @Inject constructor() {

    fun transform(loyaltyResponse: LoyaltyResponse<List<FaqResponse>>): List<FrequentlyAskedQuestion> {
        return loyaltyResponse.data.map { transform(it) }
    }

    private fun transform(response: FaqResponse) : FrequentlyAskedQuestion {
        return FrequentlyAskedQuestion(response.id, response.question, response.answer)
    }
}