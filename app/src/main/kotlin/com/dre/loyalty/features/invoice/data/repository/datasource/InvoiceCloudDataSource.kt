/*
 *
 * Created by Andreas on 1/27/21 11:38 AM
 * Copyright (c) 2021 . All rights reserved.
 * Last modified 1/27/21 11:38 AM
 * Github Link: https://github.com/oandrz
 * Email: oentoro.andreas@gmail.com
 *
 */

package com.dre.loyalty.features.invoice.data.repository.datasource

import com.dre.loyalty.core.networking.InvoiceService
import com.dre.loyalty.core.networking.response.LoyaltyResponse
import com.dre.loyalty.features.invoice.data.entity.request.InvoiceListRequest
import com.dre.loyalty.features.invoice.data.entity.response.InvoiceResponse
import retrofit2.Call
import javax.inject.Inject

class InvoiceCloudDataSource @Inject constructor(
    private val service: InvoiceService
): InvoiceCloudDataSourceContract {
    override fun getInvoice(request: InvoiceListRequest): Call<LoyaltyResponse<List<InvoiceResponse>>> {
        return service.getInvoice(request)
    }

    override fun getInvoiceDetail(id: String): Call<LoyaltyResponse<InvoiceResponse>> {
        return service.getInvoiceDetail(id)
    }
}