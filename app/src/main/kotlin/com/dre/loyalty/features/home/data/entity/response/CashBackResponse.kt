/*
 *
 * Created by Andreas on 1/26/21 5:13 PM
 * Copyright (c) 2021 . All rights reserved.
 * Last modified 1/25/21 4:20 PM
 * Github Link: https://github.com/oandrz
 * Email: oentoro.andreas@gmail.com
 *
 */

package com.dre.loyalty.features.home.data.entity.response

import com.google.gson.annotations.SerializedName

data class CashBackResponse(
    @SerializedName("receiptId")
    val id: String,
    @SerializedName("cashback")
    val cashBack: Long,
    @SerializedName("date")
    val date: String,
    @SerializedName("hospitalName")
    val hospitalName: String,
    @SerializedName("hospitalId")
    val hospitalId: String
)