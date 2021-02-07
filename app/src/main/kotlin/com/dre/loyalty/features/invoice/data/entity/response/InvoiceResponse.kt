/*
 *
 * Created by Andreas on 1/30/21 3:41 PM
 * Copyright (c) 2021 . All rights reserved.
 * Last modified 1/30/21 3:41 PM
 * Github Link: https://github.com/oandrz
 * Email: oentoro.andreas@gmail.com
 *
 */

package com.dre.loyalty.features.invoice.data.entity.response

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class InvoiceResponse(
    @SerializedName("receiptId")
    val receiptId: String,
    @SerializedName("cashback")
    val cashBack: Long,
    @SerializedName("transaction")
    val transaction: Long,
    @SerializedName("date")
    val date: String,
    @SerializedName("status")
    val status: String,
    @SerializedName("hospitalCity")
    val hospitalCity: String,
    @SerializedName("photoPath")
    val imgUrl: String,
    @SerializedName("reason")
    val reason: String,
    @SerializedName("walletName")
    val walletName: String,
    @SerializedName("phone")
    val phoneNumber: String,
    @SerializedName("isSuccess")
    val isSuccess: Boolean
)