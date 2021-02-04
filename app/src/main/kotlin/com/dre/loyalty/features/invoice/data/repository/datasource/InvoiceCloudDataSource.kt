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

import android.net.Uri
import com.dre.loyalty.core.networking.InvoiceService
import com.dre.loyalty.core.networking.response.BasicResponse
import com.dre.loyalty.core.networking.response.LoyaltyResponse
import com.dre.loyalty.features.invoice.data.entity.request.CreateInvoiceRequest
import com.dre.loyalty.features.invoice.data.entity.request.InvoiceListRequest
import com.dre.loyalty.features.invoice.data.entity.response.InvoiceResponse
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import retrofit2.Call
import java.io.File
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

    override fun createInvoice(request: CreateInvoiceRequest): Call<BasicResponse> {
        val file = File(Uri.parse(request.imageUri).path.orEmpty())
        val requestFile: RequestBody = file.asRequestBody("multipart/form-data".toMediaTypeOrNull())
        val body: MultipartBody.Part = MultipartBody.Part.createFormData("photoPath", file.name, requestFile)
        return service.createInvoice(
            request.userId,
            request.walletId,
            request.hospitalId,
            request.price,
            request.phoneNumber,
            request.date,
            body
        )
    }
}