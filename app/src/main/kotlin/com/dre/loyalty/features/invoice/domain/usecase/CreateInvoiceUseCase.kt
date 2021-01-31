/*
 *
 * Created by Andreas on 1/31/21 4:02 PM
 * Copyright (c) 2021 . All rights reserved.
 * Last modified 1/31/21 4:02 PM
 * Github Link: https://github.com/oandrz
 * Email: oentoro.andreas@gmail.com
 *
 */

package com.dre.loyalty.features.invoice.domain.usecase

import com.dre.loyalty.core.interactor.UseCase
import com.dre.loyalty.core.networking.exception.Failure
import com.dre.loyalty.core.networking.response.BasicResponse
import com.dre.loyalty.core.platform.functional.Either
import com.dre.loyalty.features.invoice.data.entity.request.CreateInvoiceRequest
import com.dre.loyalty.features.invoice.domain.InvoiceRepositoryContract
import com.dre.loyalty.features.invoice.domain.usecase.CreateInvoiceUseCase.Param
import javax.inject.Inject

class CreateInvoiceUseCase @Inject constructor(
    private val repository: InvoiceRepositoryContract
) : UseCase<BasicResponse, Param>() {

    override suspend fun run(params: Param): Either<Failure, BasicResponse> {
        return repository.createInvoice(
            CreateInvoiceRequest(
                params.userId,
                params.walletId,
                params.hospitalId,
                params.price,
                params.phoneNumber,
                params.imageUri,
                params.date
            )
        )
    }

    data class Param(
        val userId: String,
        val walletId: String,
        val hospitalId: String,
        val price: Long,
        val phoneNumber: String,
        val imageUri: String,
        val date: String
    )
}