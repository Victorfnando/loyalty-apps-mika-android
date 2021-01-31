/*
 * Created by Andreas Oen on 1/21/21 4:33 PM
 * Copyright (c) 2021 . All rights reserved.
 * Last modified 1/21/21 4:33 PM
 * github: https://github.com/oandrz
 */

package com.dre.loyalty.features.ewallet.presentation.screen

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dre.loyalty.core.interactor.UseCase
import com.dre.loyalty.core.model.EWallet
import com.dre.loyalty.core.networking.exception.Failure
import com.dre.loyalty.core.networking.response.BasicResponse
import com.dre.loyalty.core.platform.functional.Event
import com.dre.loyalty.core.platform.BaseViewModel
import com.dre.loyalty.core.platform.util.validator.type.ValidationType
import com.dre.loyalty.features.ewallet.data.entity.failure.GetWalletFailure
import com.dre.loyalty.features.ewallet.domain.usecase.GetWalletListUseCase
import com.dre.loyalty.features.ewallet.presentation.entity.WalletInputState
import com.dre.loyalty.features.ewallet.presentation.entity.WalletPhoneInputState
import com.dre.loyalty.features.ewallet.presentation.entity.WalletUploadButtonState
import com.dre.loyalty.features.invoice.domain.usecase.CreateInvoiceUseCase
import com.dre.loyalty.features.invoice.presentation.entity.UploadInvoiceState
import javax.inject.Inject

class EWalletViewModel @Inject constructor(
    private val getWalletListUseCase: GetWalletListUseCase,
    private val createInvoiceUseCase: CreateInvoiceUseCase
) : BaseViewModel() {

    private val _walletSelectorClicked: MutableLiveData<Event<List<EWallet>>> = MutableLiveData()
    val walletSelectorClicked: LiveData<Event<List<EWallet>>> = _walletSelectorClicked

    private val _walletInputState: MutableLiveData<WalletInputState> = MutableLiveData()
    val walletInputState: LiveData<WalletInputState> = _walletInputState

    private val _phoneInputState: MutableLiveData<WalletPhoneInputState> = MutableLiveData()
    val phoneInputState: LiveData<WalletPhoneInputState> = _phoneInputState

    private val _uploadButtonState: MutableLiveData<WalletUploadButtonState> = MutableLiveData()
    val uploadButtonState: LiveData<WalletUploadButtonState> = _uploadButtonState

    private val _walletConfirmationSheet: MutableLiveData<Boolean> = MutableLiveData()
    val walletConfirmationSheet: LiveData<Boolean> = _walletConfirmationSheet

    private var selectedWallet: EWallet? = null
    private var state: UploadInvoiceState? = null
    private var walletList: List<EWallet> = listOf()

    init {
        _uploadButtonState.value = WalletUploadButtonState(false)
        _phoneInputState.value = WalletPhoneInputState(-1)
    }

    fun init(state: UploadInvoiceState) {
        this.state = state
        fetchWalletList()
    }

    fun handleWalletSelectorClicked() {
        _walletSelectorClicked.value = Event(walletList)
    }

    fun handleSelectedWallet(wallet: EWallet) {
        selectedWallet = wallet
        _walletInputState.value = WalletInputState(wallet.name)
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

    fun handleUploadButtonClicked(eWallet: String, phoneNumber: String) {
        _loading.value = View.VISIBLE
        _uploadButtonState.value = _uploadButtonState.value?.copy(isEnabled = false)
        val selectedWallet = walletList.find { it.name == eWallet }
        state?.copy(walletId = selectedWallet?.id.orEmpty(), phoneNumber = phoneNumber)
        state?.let {
            createInvoiceUseCase(CreateInvoiceUseCase.Param(
                it.userId,
                it.walletId,
                it.hospitalId,
                it.price,
                it.phoneNumber,
                it.imageUri,
                it.date
            )) { result ->
                result.fold(::handleFailure, ::handleSuccessCreateWallet)
            }
        }
    }

    fun fetchWalletList() {
        _loading.value = View.VISIBLE
        getWalletListUseCase(UseCase.None()) {
            it.fold(::handleFailedGetWallet, ::handleSuccessGetWallet)
        }
    }

    private fun handleSuccessGetWallet(walletList: List<EWallet>) {
        _loading.value = View.GONE
        this.walletList = walletList
    }

    private fun handleFailedGetWallet(failure: Failure) {
        handleFailure(GetWalletFailure())
    }

    private fun handleSuccessCreateWallet(response: BasicResponse) {
        _loading.value = View.GONE
        _uploadButtonState.value = _uploadButtonState.value?.copy(isEnabled = true)
        _walletConfirmationSheet.value = true
    }

    private fun checkButtonState() {
        _uploadButtonState.value = _uploadButtonState.value?.copy(
            isEnabled = _phoneInputState.value?.error == null && selectedWallet != null
        )
    }
}
