package com.dre.loyalty.features.home.domain

import com.dre.loyalty.core.exception.Failure
import com.dre.loyalty.core.functional.Either
import com.dre.loyalty.core.model.Home
import com.dre.loyalty.features.home.domain.usecase.GetHomeDataUseCase
import com.dre.loyalty.features.home.domain.usecase.GetHomeDataUseCase.Param

interface HomeRepositoryContract {
    fun getHomeData(param: Param): Either<Failure, Home>
}