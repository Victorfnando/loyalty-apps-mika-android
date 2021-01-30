/*
 *
 * Created by Andreas on 1/30/21 3:51 PM
 * Copyright (c) 2021 . All rights reserved.
 * Last modified 1/30/21 3:51 PM
 * Github Link: https://github.com/oandrz
 * Email: oentoro.andreas@gmail.com
 *
 */

package com.dre.loyalty.features.invoice.data.entity.mapper

import com.dre.loyalty.core.model.Invoice
import com.dre.loyalty.features.invoice.data.entity.response.InvoiceResponse
import com.dre.loyalty.features.invoice.presentation.list.enumtype.InvoiceType
import javax.inject.Inject

class InvoiceResponseMapper @Inject constructor() {

    fun transform(response: List<InvoiceResponse>): List<Invoice> {
        return response.map {
            transform(it)
        }
    }

    fun transform(response: InvoiceResponse): Invoice {
        return Invoice(
            response.receiptId,
            InvoiceType.fromValue(response.status),
            response.imgUrl,
            response.transaction,
            response.cashBack,
            response.date,
            response.hospitalCity,
            response.reason,
            response.walletName
        )
    }
}