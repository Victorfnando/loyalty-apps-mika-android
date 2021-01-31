/*
 *
 * Created by Andreas on 1/31/21 3:33 PM
 * Copyright (c) 2021 . All rights reserved.
 * Last modified 1/31/21 3:33 PM
 * Github Link: https://github.com/oandrz
 * Email: oentoro.andreas@gmail.com
 *
 */

package com.dre.loyalty.features.ewallet.domain

import com.dre.loyalty.core.model.EWallet
import com.dre.loyalty.core.networking.exception.Failure
import com.dre.loyalty.core.platform.functional.Either

interface EWalletRepositoryContract {
    fun getEWallet(): Either<Failure, List<EWallet>>
}