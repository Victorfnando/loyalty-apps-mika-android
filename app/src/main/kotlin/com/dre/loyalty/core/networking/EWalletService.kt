/*
 *
 * Created by Andreas on 1/31/21 3:41 PM
 * Copyright (c) 2021 . All rights reserved.
 * Last modified 1/31/21 3:41 PM
 * Github Link: https://github.com/oandrz
 * Email: oentoro.andreas@gmail.com
 *
 */

package com.dre.loyalty.core.networking

import com.dre.loyalty.core.networking.response.LoyaltyResponse
import com.dre.loyalty.features.ewallet.data.entity.response.EWalletResponse
import retrofit2.Call
import retrofit2.http.GET

private const val WALLET_ENDPOINT = "wallet_list"
interface EWalletService {
    @GET(WALLET_ENDPOINT)
    fun getWalletList(): Call<LoyaltyResponse<List<EWalletResponse>>>
}