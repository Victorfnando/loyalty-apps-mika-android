/*
 *
 * Created by Andreas on 1/30/21 2:59 PM
 * Copyright (c) 2021 . All rights reserved.
 * Last modified 1/30/21 2:59 PM
 * Github Link: https://github.com/oandrz
 * Email: oentoro.andreas@gmail.com
 *
 */

package com.dre.loyalty.features.cashback.data.repository.datasource

import com.dre.loyalty.core.networking.response.LoyaltyResponse
import com.dre.loyalty.features.cashback.data.entity.request.CashBackRequest
import com.dre.loyalty.features.home.data.entity.response.CashBackResponse
import retrofit2.Call

interface CashBackListCloudDataSourceContract {
    fun getCashBackList(request: CashBackRequest): Call<LoyaltyResponse<List<CashBackResponse>>>
}