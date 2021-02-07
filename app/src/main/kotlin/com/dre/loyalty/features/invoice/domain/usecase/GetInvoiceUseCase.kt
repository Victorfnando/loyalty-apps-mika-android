/*
 *
 * Created by Andreas on 1/27/21 11:40 AM
 * Copyright (c) 2021 . All rights reserved.
 * Last modified 1/27/21 11:40 AM
 * Github Link: https://github.com/oandrz
 * Email: oentoro.andreas@gmail.com
 *
 */

package com.dre.loyalty.features.invoice.domain.usecase

import com.dre.loyalty.core.interactor.UseCase
import com.dre.loyalty.core.model.Invoice
import com.dre.loyalty.core.networking.exception.Failure
import com.dre.loyalty.core.platform.functional.Either
import com.dre.loyalty.features.invoice.data.entity.request.InvoiceListRequest
import com.dre.loyalty.features.invoice.domain.InvoiceRepositoryContract
import com.dre.loyalty.features.invoice.domain.usecase.GetInvoiceUseCase.Param
import com.dre.loyalty.features.invoice.presentation.list.enumtype.InvoiceType
import javax.inject.Inject

class GetInvoiceUseCase @Inject constructor(
    private val repository: InvoiceRepositoryContract
) : UseCase<List<Invoice>, Param>() {

    override suspend fun run(params: Param): Either<Failure, List<Invoice>> {
        return repository.getInvoiceList(
            InvoiceListRequest(params.userId, params.status.position)
        )
    }

    data class Param(
        val userId: String,
        val status: InvoiceType
    )
}