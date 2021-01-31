/*
 *
 * Created by Andreas on 1/27/21 11:37 AM
 * Copyright (c) 2021 . All rights reserved.
 * Last modified 1/27/21 11:37 AM
 * Github Link: https://github.com/oandrz
 * Email: oentoro.andreas@gmail.com
 *
 */

package com.dre.loyalty.features.invoice.domain

import com.dre.loyalty.core.model.Invoice
import com.dre.loyalty.core.networking.exception.Failure
import com.dre.loyalty.core.networking.response.BasicResponse
import com.dre.loyalty.core.platform.functional.Either
import com.dre.loyalty.features.invoice.data.entity.request.CreateInvoiceRequest
import com.dre.loyalty.features.invoice.data.entity.request.InvoiceListRequest

interface InvoiceRepositoryContract {
    fun getInvoiceList(request: InvoiceListRequest): Either<Failure, List<Invoice>>
    fun getInvoiceDetail(id: String): Either<Failure, Invoice>
    fun createInvoice(request: CreateInvoiceRequest): Either<Failure, BasicResponse>
}