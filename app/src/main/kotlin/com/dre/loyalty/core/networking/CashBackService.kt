/*
 *
 * Created by Andreas on 1/30/21 2:53 PM
 * Copyright (c) 2021 . All rights reserved.
 * Last modified 1/30/21 2:53 PM
 * Github Link: https://github.com/oandrz
 * Email: oentoro.andreas@gmail.com
 *
 */

package com.dre.loyalty.core.networking

import com.dre.loyalty.core.networking.response.LoyaltyResponse
import com.dre.loyalty.features.cashback.data.entity.request.CashBackRequest
import com.dre.loyalty.features.home.data.entity.response.CashBackResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

private const val CASHBACK_LIST_ENDPOINT = "cashbacks"
interface CashBackService {
    @POST(CASHBACK_LIST_ENDPOINT)
    fun getCashBackList(@Body request: CashBackRequest): Call<LoyaltyResponse<List<CashBackResponse>>>
}