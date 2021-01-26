package com.dre.loyalty.features.faq.domain.usecase

import com.dre.loyalty.core.exception.Failure
import com.dre.loyalty.core.functional.Either
import com.dre.loyalty.core.interactor.UseCase
import com.dre.loyalty.features.faq.domain.FaqRepositoryContract
import com.dre.loyalty.features.faq.domain.entity.FrequentlyAskedQuestion
import com.dre.loyalty.features.faq.domain.usecase.GetFaqQuestion.Param
import com.google.gson.annotations.SerializedName
import javax.inject.Inject

class GetFaqQuestion @Inject constructor(
    private val repository: FaqRepositoryContract
) : UseCase<List<FrequentlyAskedQuestion>, Param>() {

    override suspend fun run(params: Param): Either<Failure, List<FrequentlyAskedQuestion>> {
        return repository.getFaqQuestionList(params)
    }

    data class Param(
        @SerializedName("user_id")
        val userId: String,
        @SerializedName("token")
        val token: String
    )
}