package com.dre.loyalty.features.cashback.presentation.screen

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dre.loyalty.core.model.CashBack
import com.dre.loyalty.core.platform.functional.Event
import com.dre.loyalty.core.platform.BaseViewModel
import com.dre.loyalty.core.platform.util.preferences.AuthenticationManager
import com.dre.loyalty.features.cashback.domain.usecase.GetCashBackListUseCase
import com.dre.loyalty.features.cashback.domain.usecase.GetCashBackListUseCase.Param
import javax.inject.Inject

class CashBackListViewModel @Inject constructor(
    private val getCashBackListUseCase: GetCashBackListUseCase,
    private val authenticationManager: AuthenticationManager
) : BaseViewModel() {

    private val _navigateCreateInvoice: MutableLiveData<Event<Boolean>> = MutableLiveData()
    val uploadInvoiceClicked: LiveData<Event<Boolean>> = _navigateCreateInvoice

    private val _cashBackList: MutableLiveData<List<CashBack>> = MutableLiveData()
    val cashBackList: LiveData<List<CashBack>> = _cashBackList

    fun init() {
        refresh()
    }

    fun handleUploadInvoiceClicked() {
        _navigateCreateInvoice.value = Event(true)
    }

    fun refresh() {
        val userId = authenticationManager.getUserId()
        userId?.let {
            _loading.value = View.VISIBLE
            getCashBackListUseCase(Param(it)) { result ->
                result.fold(::handleFailure, ::handleSuccessGetCashBack)
            }
        }
    }

    private fun handleSuccessGetCashBack(cashBackList: List<CashBack>) {
        _loading.value = View.GONE
        _cashBackList.value = cashBackList
    }
}