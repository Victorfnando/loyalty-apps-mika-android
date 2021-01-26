package com.dre.loyalty.features.faq.data.entity.mapper

import com.dre.loyalty.features.faq.data.entity.response.FaqDataResponse
import com.dre.loyalty.features.faq.data.entity.response.FaqResponse
import com.dre.loyalty.features.faq.domain.entity.FrequentlyAskedQuestion
import com.dre.loyalty.core.response.BaseResponse
import javax.inject.Inject

class FaqResponseMapper @Inject constructor() {

    fun transform(response: BaseResponse<FaqDataResponse>): List<FrequentlyAskedQuestion> {
        return response.data.content.map { transform(it) }
    }

    private fun transform(response: FaqResponse) : FrequentlyAskedQuestion {
        return FrequentlyAskedQuestion(response.id, response.question, response.answer)
    }
}