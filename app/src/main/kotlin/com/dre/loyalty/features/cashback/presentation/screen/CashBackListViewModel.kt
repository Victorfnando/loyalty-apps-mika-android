package com.dre.loyalty.features.cashback.presentation.screen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dre.loyalty.core.functional.Event
import com.dre.loyalty.core.platform.BaseViewModel
import javax.inject.Inject

class CashBackListViewModel @Inject constructor() : BaseViewModel() {

    private val _navigateCreateInvoice: MutableLiveData<Event<Boolean>> = MutableLiveData()
    val uploadInvoiceClicked: LiveData<Event<Boolean>> = _navigateCreateInvoice

    fun handleUploadInvoiceClicked() {
        _navigateCreateInvoice.value = Event(true)
    }
}