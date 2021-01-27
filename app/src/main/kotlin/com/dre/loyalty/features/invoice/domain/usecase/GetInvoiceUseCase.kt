/*
 *
 * Created by Andreas on 1/27/21 11:40 AM
 * Copyright (c) 2021 . All rights reserved.
 * Last modified 1/27/21 11:40 AM
 * Github Link: https://github.com/oandrz
 * Email: oentoro.andreas@gmail.com
 *
 */

package com.dre.loyalty.features.invoice.domain.usecase

import com.dre.loyalty.features.invoice.domain.InvoiceRepositoryContract
import javax.inject.Inject

class GetInvoiceUseCase @Inject constructor(
    private val repository: InvoiceRepositoryContract
) {
}