package com.dre.loyalty.features.faq.data.repository

import com.dre.loyalty.core.networking.exception.Failure
import com.dre.loyalty.core.platform.extension.request
import com.dre.loyalty.core.platform.functional.Either
import com.dre.loyalty.core.platform.NetworkHandler
import com.dre.loyalty.features.faq.data.entity.mapper.FaqResponseMapper
import com.dre.loyalty.features.faq.data.repository.datasource.FaqCloudDataSourceContract
import com.dre.loyalty.features.faq.domain.FaqRepositoryContract
import com.dre.loyalty.features.faq.domain.entity.FrequentlyAskedQuestion
import com.dre.loyalty.features.faq.domain.usecase.GetFaqQuestion
import javax.inject.Inject

class FaqRepository @Inject constructor(
    private val networkHandler: NetworkHandler,
    private val cloudDataSource: FaqCloudDataSourceContract,
    private val mapper: FaqResponseMapper
) : FaqRepositoryContract {
    override fun getFaqQuestionList(): Either<Failure, List<FrequentlyAskedQuestion>> {
        return when(networkHandler.isNetworkAvailable()) {
            true -> {
                cloudDataSource.getFaq().request {
                    mapper.transform(it)
                }
            }
            false -> Either.Left(Failure.NetworkConnection)
        }
    }
}