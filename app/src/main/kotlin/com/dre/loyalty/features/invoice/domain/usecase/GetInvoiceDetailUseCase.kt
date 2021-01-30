/*
 *
 * Created by Andreas on 1/30/21 7:25 PM
 * Copyright (c) 2021 . All rights reserved.
 * Last modified 1/30/21 7:25 PM
 * Github Link: https://github.com/oandrz
 * Email: oentoro.andreas@gmail.com
 *
 */

package com.dre.loyalty.features.invoice.domain.usecase

import com.dre.loyalty.core.interactor.UseCase
import com.dre.loyalty.core.model.Invoice
import com.dre.loyalty.core.networking.exception.Failure
import com.dre.loyalty.core.platform.functional.Either
import com.dre.loyalty.features.invoice.domain.InvoiceRepositoryContract
import javax.inject.Inject

class GetInvoiceDetailUseCase @Inject constructor(
    private val repository: InvoiceRepositoryContract
) : UseCase<Invoice, String>() {
    override suspend fun run(params: String): Either<Failure, Invoice> {
        return repository.getInvoiceDetail(params)
    }
}