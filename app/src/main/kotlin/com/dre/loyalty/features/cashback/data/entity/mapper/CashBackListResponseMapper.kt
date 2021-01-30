/*
 *
 * Created by Andreas on 1/30/21 3:03 PM
 * Copyright (c) 2021 . All rights reserved.
 * Last modified 1/30/21 3:03 PM
 * Github Link: https://github.com/oandrz
 * Email: oentoro.andreas@gmail.com
 *
 */

package com.dre.loyalty.features.cashback.data.entity.mapper

import com.dre.loyalty.core.model.CashBack
import com.dre.loyalty.features.home.data.entity.response.CashBackResponse
import javax.inject.Inject

class CashBackListResponseMapper @Inject constructor() {

    fun transform(response: List<CashBackResponse>): List<CashBack> {
        return response.map {
            transform(it)
        }
    }

    private fun transform(response: CashBackResponse): CashBack {
        return CashBack(
            response.id,
            response.receiptId,
            response.cashBack,
            response.date,
            response.phone,
            response.walletName
        )
    }
}