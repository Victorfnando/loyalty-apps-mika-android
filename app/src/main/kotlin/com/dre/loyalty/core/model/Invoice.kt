/*
 *
 * Created by Andreas on 1/30/21 3:54 PM
 * Copyright (c) 2021 . All rights reserved.
 * Last modified 1/30/21 3:53 PM
 * Github Link: https://github.com/oandrz
 * Email: oentoro.andreas@gmail.com
 *
 */

package com.dre.loyalty.core.model

import com.dre.loyalty.features.invoice.presentation.list.enumtype.InvoiceType

data class Invoice(
    val receiptId: String,
    val status: InvoiceType,
    val imageUrl: String,
    val transactionPrice: Long,
    val cashBackAmount: Long,
    val date: String,
    val location: String,
    val reason: String,
    val walletName: String,
    val phoneNumber: String,
    val isSuccess: Boolean
)
