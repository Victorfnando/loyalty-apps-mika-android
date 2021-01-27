/*
 *
 * Created by Andreas on 1/27/21 11:39 AM
 * Copyright (c) 2021 . All rights reserved.
 * Last modified 1/27/21 11:37 AM
 * Github Link: https://github.com/oandrz
 * Email: oentoro.andreas@gmail.com
 *
 */

package com.dre.loyalty.features.invoice.data.repository

import com.dre.loyalty.features.invoice.data.repository.datasource.InvoiceCloudDataSourceContract
import com.dre.loyalty.features.invoice.domain.InvoiceRepositoryContract
import javax.inject.Inject

class InvoiceRepository @Inject constructor(
    private val cloudDataSource: InvoiceCloudDataSourceContract
) : InvoiceRepositoryContract {
}