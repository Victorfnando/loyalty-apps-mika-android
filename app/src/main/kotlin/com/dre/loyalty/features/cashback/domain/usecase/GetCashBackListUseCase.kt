/*
 *
 * Created by Andreas on 1/30/21 3:07 PM
 * Copyright (c) 2021 . All rights reserved.
 * Last modified 1/30/21 3:07 PM
 * Github Link: https://github.com/oandrz
 * Email: oentoro.andreas@gmail.com
 *
 */

package com.dre.loyalty.features.cashback.domain.usecase

import com.dre.loyalty.core.interactor.UseCase
import com.dre.loyalty.core.model.CashBack
import com.dre.loyalty.core.networking.exception.Failure
import com.dre.loyalty.core.platform.functional.Either
import com.dre.loyalty.features.cashback.data.entity.request.CashBackRequest
import com.dre.loyalty.features.cashback.domain.CashBackListRepositoryContract
import com.dre.loyalty.features.cashback.domain.usecase.GetCashBackListUseCase.Param
import javax.inject.Inject

class GetCashBackListUseCase @Inject constructor(
    private val repository: CashBackListRepositoryContract
) : UseCase<List<CashBack>, Param>() {

    override suspend fun run(params: Param): Either<Failure, List<CashBack>> {
        return repository.getCashBackList(CashBackRequest(params.userId))
    }

    data class Param(
        val userId: String
    )
}