/*
 * Created by Andreas Oen on 1/16/21 7:39 PM
 * Copyright (c) 2021 . All rights reserved.
 * Last modified 1/16/21 7:39 PM
 * github: https://github.com/oandrz
 */

package com.dre.loyalty.features.invoice.presentation.entity

import com.dre.loyalty.features.invoice.presentation.list.enumtype.InvoiceType

data class Invoice(
    val imageUrl: String,
    val invoiceId: String,
    val price: Long,
    val date: String,
    val location: String,
    val status: InvoiceType
)
