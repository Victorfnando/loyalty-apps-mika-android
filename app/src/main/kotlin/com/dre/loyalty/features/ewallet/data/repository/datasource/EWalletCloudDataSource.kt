/*
 *
 * Created by Andreas on 1/31/21 3:40 PM
 * Copyright (c) 2021 . All rights reserved.
 * Last modified 1/31/21 3:40 PM
 * Github Link: https://github.com/oandrz
 * Email: oentoro.andreas@gmail.com
 *
 */

package com.dre.loyalty.features.ewallet.data.repository.datasource

import com.dre.loyalty.core.networking.EWalletService
import com.dre.loyalty.core.networking.response.LoyaltyResponse
import com.dre.loyalty.features.ewallet.data.entity.response.EWalletResponse
import retrofit2.Call
import javax.inject.Inject

class EWalletCloudDataSource @Inject constructor(
    private val service: EWalletService
) : EWalletCloudDataSourceContract {
    override fun getWalletList(): Call<LoyaltyResponse<List<EWalletResponse>>> {
        return service.getWalletList()
    }
}