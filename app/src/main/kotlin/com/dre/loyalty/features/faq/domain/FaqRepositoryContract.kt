package com.dre.loyalty.features.faq.domain

import com.dre.loyalty.core.networking.exception.Failure
import com.dre.loyalty.core.platform.functional.Either
import com.dre.loyalty.features.faq.domain.entity.FrequentlyAskedQuestion

interface FaqRepositoryContract {
    fun getFaqQuestionList(): Either<Failure, List<FrequentlyAskedQuestion>>
}