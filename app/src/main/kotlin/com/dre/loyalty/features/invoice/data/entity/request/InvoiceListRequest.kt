/*
 *
 * Created by Andreas on 1/30/21 3:35 PM
 * Copyright (c) 2021 . All rights reserved.
 * Last modified 1/30/21 3:35 PM
 * Github Link: https://github.com/oandrz
 * Email: oentoro.andreas@gmail.com
 *
 */

package com.dre.loyalty.features.invoice.data.entity.request

import com.google.gson.annotations.SerializedName

data class InvoiceListRequest(
    @SerializedName("userId")
    private val userId: String,
    @SerializedName("status")
    private val status: String
)