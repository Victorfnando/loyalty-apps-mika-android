/*
 * Created by Andreas Oen on 1/21/21 4:33 PM
 * Copyright (c) 2021 . All rights reserved.
 * Last modified 1/21/21 4:33 PM
 * github: https://github.com/oandrz
 */

package com.dre.loyalty.features.ewallet.presentation.screen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dre.loyalty.core.platform.functional.Event
import com.dre.loyalty.core.platform.BaseViewModel
import com.dre.loyalty.core.platform.util.validator.type.ValidationType
import com.dre.loyalty.features.ewallet.presentation.entity.Wallet
import com.dre.loyalty.features.ewallet.presentation.entity.WalletInputState
import com.dre.loyalty.features.ewallet.presentation.entity.WalletPhoneInputState
import com.dre.loyalty.features.ewallet.presentation.entity.WalletUploadButtonState
import javax.inject.Inject

class EWalletViewModel @Inject constructor() : BaseViewModel() {

    private val _walletSelectorClicked: MutableLiveData<Event<List<Wallet>>> = MutableLiveData()
    val walletSelectorClicked: LiveData<Event<List<Wallet>>> = _walletSelectorClicked

    private val _walletInputState: MutableLiveData<WalletInputState> = MutableLiveData()
    val walletInputState: LiveData<WalletInputState> = _walletInputState

    private val _phoneInputState: MutableLiveData<WalletPhoneInputState> = MutableLiveData()
    val phoneInputState: LiveData<WalletPhoneInputState> = _phoneInputState

    private val _uploadButtonState: MutableLiveData<WalletUploadButtonState> = MutableLiveData()
    val uploadButtonState: LiveData<WalletUploadButtonState> = _uploadButtonState

    private var selectedWallet: Wallet? = null

    init {
        _uploadButtonState.value = WalletUploadButtonState(false)
        _phoneInputState.value = WalletPhoneInputState(-1)
    }

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

    fun handlePhoneNumberChangedListener(text: String) {
        val result = ValidationType.PHONE.strategy.validate(text)
        if (result.isPass) {
            _phoneInputState.value = _phoneInputState.value?.copy(error = null)
        } else {
            _phoneInputState.value = _phoneInputState.value?.copy(error = result.errorMessage)
        }
        checkButtonState()
    }

    private fun checkButtonState() {
        _uploadButtonState.value = _uploadButtonState.value?.copy(
            isEnabled = _phoneInputState.value?.error == null && selectedWallet != null
        )
    }
}
