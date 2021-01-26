package com.dre.loyalty.features.faq.domain

import com.dre.loyalty.core.exception.Failure
import com.dre.loyalty.core.functional.Either
import com.dre.loyalty.features.faq.domain.entity.FrequentlyAskedQuestion
import com.dre.loyalty.features.faq.domain.usecase.GetFaqQuestion.Param

interface FaqRepositoryContract {
    fun getFaqQuestionList(param: Param): Either<Failure, List<FrequentlyAskedQuestion>>
}