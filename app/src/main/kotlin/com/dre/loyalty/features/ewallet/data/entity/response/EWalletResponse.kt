/*
 *
 * Created by Andreas on 1/31/21 3:42 PM
 * Copyright (c) 2021 . All rights reserved.
 * Last modified 1/31/21 3:42 PM
 * Github Link: https://github.com/oandrz
 * Email: oentoro.andreas@gmail.com
 *
 */

package com.dre.loyalty.features.ewallet.data.entity.response

import com.google.gson.annotations.SerializedName

data class EWalletResponse(
    @SerializedName("walletId")
    val id: String,
    @SerializedName("walletName")
    val name: String,
    @SerializedName("walletImage")
    val image: String
)