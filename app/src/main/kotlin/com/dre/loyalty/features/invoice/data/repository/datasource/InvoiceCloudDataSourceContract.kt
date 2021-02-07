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

import android.content.Context
import com.dre.loyalty.core.networking.response.BasicResponse
import com.dre.loyalty.core.networking.response.LoyaltyResponse
import com.dre.loyalty.features.invoice.data.entity.request.CreateInvoiceRequest
import com.dre.loyalty.features.invoice.data.entity.request.InvoiceListRequest
import com.dre.loyalty.features.invoice.data.entity.response.InvoiceResponse
import retrofit2.Call

interface InvoiceCloudDataSourceContract {
    fun getInvoice(request: InvoiceListRequest): Call<LoyaltyResponse<List<InvoiceResponse>>>
    fun getInvoiceDetail(id: String): Call<LoyaltyResponse<InvoiceResponse>>
    fun createInvoice(request: CreateInvoiceRequest): Call<BasicResponse>
}