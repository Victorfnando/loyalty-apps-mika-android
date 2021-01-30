package com.dre.loyalty.features.faq.domain.usecase

import com.dre.loyalty.core.networking.exception.Failure
import com.dre.loyalty.core.platform.functional.Either
import com.dre.loyalty.core.interactor.UseCase
import com.dre.loyalty.features.faq.domain.FaqRepositoryContract
import com.dre.loyalty.features.faq.domain.entity.FrequentlyAskedQuestion
import javax.inject.Inject

class GetFaqQuestion @Inject constructor(
    private val repository: FaqRepositoryContract
) : UseCase<List<FrequentlyAskedQuestion>, UseCase.None>() {

    override suspend fun run(params: None): Either<Failure, List<FrequentlyAskedQuestion>> {
        return repository.getFaqQuestionList()
    }
}