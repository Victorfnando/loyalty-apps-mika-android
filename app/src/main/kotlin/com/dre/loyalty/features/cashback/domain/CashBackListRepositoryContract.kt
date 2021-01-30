/*
 *
 * Created by Andreas on 1/30/21 2:58 PM
 * Copyright (c) 2021 . All rights reserved.
 * Last modified 1/30/21 2:58 PM
 * Github Link: https://github.com/oandrz
 * Email: oentoro.andreas@gmail.com
 *
 */

package com.dre.loyalty.features.cashback.domain

import com.dre.loyalty.core.model.CashBack
import com.dre.loyalty.core.networking.exception.Failure
import com.dre.loyalty.core.platform.functional.Either
import com.dre.loyalty.features.cashback.data.entity.request.CashBackRequest

interface CashBackListRepositoryContract {
    fun getCashBackList(request: CashBackRequest): Either<Failure, List<CashBack>>
}