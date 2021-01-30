/*
 *
 * Created by Andreas on 1/30/21 2:56 PM
 * Copyright (c) 2021 . All rights reserved.
 * Last modified 1/30/21 2:56 PM
 * Github Link: https://github.com/oandrz
 * Email: oentoro.andreas@gmail.com
 *
 */

package com.dre.loyalty.features.cashback.data.entity.request

import com.google.gson.annotations.SerializedName

data class CashBackRequest(
    @SerializedName("userId")
    val userId: String
)