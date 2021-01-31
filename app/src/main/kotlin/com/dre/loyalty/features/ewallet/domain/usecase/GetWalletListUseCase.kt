/*
 *
 * Created by Andreas on 1/31/21 3:59 PM
 * Copyright (c) 2021 . All rights reserved.
 * Last modified 1/31/21 3:59 PM
 * Github Link: https://github.com/oandrz
 * Email: oentoro.andreas@gmail.com
 *
 */

package com.dre.loyalty.features.ewallet.domain.usecase

import com.dre.loyalty.core.interactor.UseCase
import com.dre.loyalty.core.model.EWallet
import com.dre.loyalty.core.networking.exception.Failure
import com.dre.loyalty.core.platform.functional.Either
import com.dre.loyalty.features.ewallet.domain.EWalletRepositoryContract
import javax.inject.Inject

class GetWalletListUseCase @Inject constructor(
    private val repository: EWalletRepositoryContract
) : UseCase<List<EWallet>, UseCase.None>() {

    override suspend fun run(params: None): Either<Failure, List<EWallet>> {
        return repository.getEWallet()
    }
}