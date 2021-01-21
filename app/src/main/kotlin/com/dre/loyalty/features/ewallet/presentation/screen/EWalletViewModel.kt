/*
 * Created by Andreas Oen on 1/21/21 4:33 PM
 * Copyright (c) 2021 . All rights reserved.
 * Last modified 1/21/21 4:33 PM
 * github: https://github.com/oandrz
 */

package com.dre.loyalty.features.ewallet.presentation.screen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dre.loyalty.core.functional.Event
import com.dre.loyalty.core.platform.BaseViewModel
import com.dre.loyalty.features.ewallet.presentation.entity.Wallet
import com.dre.loyalty.features.ewallet.presentation.entity.WalletInputState
import javax.inject.Inject

class EWalletViewModel @Inject constructor() : BaseViewModel() {

    private val _walletSelectorClicked: MutableLiveData<Event<List<Wallet>>> = MutableLiveData()
    val walletSelectorClicked: LiveData<Event<List<Wallet>>> = _walletSelectorClicked

    private val _walletInputState: MutableLiveData<WalletInputState> = MutableLiveData()
    val walletInputState: LiveData<WalletInputState> = _walletInputState

    private var selectedWallet: Wallet? = null

    fun handleWalletSelectorClicked() {
        _walletSelectorClicked.value = Event(listOf(
            Wallet("Go Pay", ""),
            Wallet("Shopee Pay", ""),
            Wallet("OVO", "")
        ))
    }

    fun handleSelectedWallet(wallet: Wallet) {
        selectedWallet = wallet
        _walletInputState.value = WalletInputState(wallet.text)
    }
}
