package com.dre.loyalty.features.home.domain.usecase

import com.dre.loyalty.core.networking.exception.Failure
import com.dre.loyalty.core.platform.functional.Either
import com.dre.loyalty.core.interactor.UseCase
import com.dre.loyalty.core.model.Home
import com.dre.loyalty.features.home.domain.HomeRepositoryContract
import com.dre.loyalty.features.home.domain.usecase.GetHomeDataUseCase.Param
import com.google.gson.annotations.SerializedName
import javax.inject.Inject

class GetHomeDataUseCase @Inject constructor(
    private val repository: HomeRepositoryContract
) : UseCase<Home, Param>() {
    override suspend fun run(params: Param): Either<Failure, Home> {
        return repository.getHomeData(params)
    }

    data class Param(
        @SerializedName("user_id")
        val id: String,
    )
}