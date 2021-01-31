/*
 *
 * Created by Andreas on 1/31/21 4:05 PM
 * Copyright (c) 2021 . All rights reserved.
 * Last modified 1/31/21 4:05 PM
 * Github Link: https://github.com/oandrz
 * Email: oentoro.andreas@gmail.com
 *
 */

package com.dre.loyalty.features.invoice.data.entity.request

import com.google.gson.annotations.SerializedName

data class CreateInvoiceRequest(
    @SerializedName("userId")
    val userId: String,
    @SerializedName("walletId")
    val walletId: String,
    @SerializedName("hospitalId")
    val hospitalId: String,
    @SerializedName("receiptPrice")
    val price: Long,
    @SerializedName("phone")
    val phoneNumber: String,
    val imageUri: String,
    @SerializedName("date")
    val date: String
)