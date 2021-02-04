/*
 *
 * Created by Andreas on 1/30/21 3:34 PM
 * Copyright (c) 2021 . All rights reserved.
 * Last modified 1/30/21 3:34 PM
 * Github Link: https://github.com/oandrz
 * Email: oentoro.andreas@gmail.com
 *
 */

package com.dre.loyalty.core.networking

import com.dre.loyalty.core.networking.response.BasicResponse
import com.dre.loyalty.core.networking.response.LoyaltyResponse
import com.dre.loyalty.features.invoice.data.entity.request.CreateInvoiceRequest
import com.dre.loyalty.features.invoice.data.entity.request.InvoiceListRequest
import com.dre.loyalty.features.invoice.data.entity.response.InvoiceResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*

private const val INVOICE_ENDPOINT = "receipts"
private const val CREATE_INVOICE_ENDPOINT = "create_receipt"
private const val INVOICE_DETAIL_ENDPOINT = "receipt_detail/{receiptId}"
interface InvoiceService {

    @POST(INVOICE_ENDPOINT)
    fun getInvoice(@Body request: InvoiceListRequest): Call<LoyaltyResponse<List<InvoiceResponse>>>

    @GET(INVOICE_DETAIL_ENDPOINT)
    fun getInvoiceDetail(@Path("receiptId") id: String): Call<LoyaltyResponse<InvoiceResponse>>

    @Multipart
    @POST(CREATE_INVOICE_ENDPOINT)
    fun createInvoice(
        @Part("userId") userId: String,
        @Part("walletId") walletId: String,
        @Part("hospitalId") hospitalId: String,
        @Part("transaction") price: Long,
        @Part("phone") phone: String,
        @Part("date") date: String,
        @Part photoPath: MultipartBody.Part
    ): Call<BasicResponse>

}