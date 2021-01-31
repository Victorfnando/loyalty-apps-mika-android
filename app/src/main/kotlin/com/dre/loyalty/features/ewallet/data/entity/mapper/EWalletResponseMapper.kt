/*
 *
 * Created by Andreas on 1/31/21 3:51 PM
 * Copyright (c) 2021 . All rights reserved.
 * Last modified 1/31/21 3:51 PM
 * Github Link: https://github.com/oandrz
 * Email: oentoro.andreas@gmail.com
 *
 */

package com.dre.loyalty.features.ewallet.data.entity.mapper

import com.dre.loyalty.core.model.EWallet
import com.dre.loyalty.features.ewallet.data.entity.response.EWalletResponse
import javax.inject.Inject

class EWalletResponseMapper @Inject constructor() {

    fun transform(response: List<EWalletResponse>): List<EWallet> {
        return response.map {
            transform(it)
        }
    }

    private fun transform(response: EWalletResponse): EWallet {
        return EWallet(
            response.id,
            response.name,
            response.image
        )
    }
}