/*
 *
 * Created by Andreas on 1/27/21 11:39 AM
 * Copyright (c) 2021 . All rights reserved.
 * Last modified 1/27/21 11:37 AM
 * Github Link: https://github.com/oandrz
 * Email: oentoro.andreas@gmail.com
 *
 */

package com.dre.loyalty.features.invoice.data.repository

import com.dre.loyalty.core.model.Invoice
import com.dre.loyalty.core.networking.exception.Failure
import com.dre.loyalty.core.platform.NetworkHandler
import com.dre.loyalty.core.platform.extension.request
import com.dre.loyalty.core.platform.functional.Either
import com.dre.loyalty.features.invoice.data.entity.mapper.InvoiceResponseMapper
import com.dre.loyalty.features.invoice.data.entity.request.InvoiceListRequest
import com.dre.loyalty.features.invoice.data.repository.datasource.InvoiceCloudDataSourceContract
import com.dre.loyalty.features.invoice.domain.InvoiceRepositoryContract
import javax.inject.Inject

class InvoiceRepository @Inject constructor(
    private val networkHandler: NetworkHandler,
    private val cloudDataSource: InvoiceCloudDataSourceContract,
    private val responseMapper: InvoiceResponseMapper
) : InvoiceRepositoryContract {
    override fun getInvoiceList(request: InvoiceListRequest): Either<Failure, List<Invoice>> {
        return when(networkHandler.isNetworkAvailable()) {
            true -> {
                cloudDataSource.getInvoice(request).request {
                    responseMapper.transform(it.data)
                }
            }
            false -> Either.Left(Failure.NetworkConnection)
        }
    }

    override fun getInvoiceDetail(id: String): Either<Failure, Invoice> {
        return when(networkHandler.isNetworkAvailable()) {
            true -> {
                cloudDataSource.getInvoiceDetail(id).request {
                    responseMapper.transform(it.data)
                }
            }
            false -> Either.Left(Failure.NetworkConnection)
        }
    }
}