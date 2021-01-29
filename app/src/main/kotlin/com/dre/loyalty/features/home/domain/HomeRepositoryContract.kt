package com.dre.loyalty.features.home.domain

import com.dre.loyalty.core.networking.exception.Failure
import com.dre.loyalty.core.platform.functional.Either
import com.dre.loyalty.core.model.Home
import com.dre.loyalty.features.home.domain.usecase.GetHomeDataUseCase.Param

interface HomeRepositoryContract {
    fun getHomeData(param: Param): Either<Failure, Home>
}