/*
 * Created by Andreas Oen on 1/16/21 4:18 PM
 * Copyright (c) 2021 . All rights reserved.
 * Last modified 1/16/21 4:18 PM
 * github: https://github.com/oandrz
 */

package com.dre.loyalty.features.invoice.presentation.list.screen

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dre.loyalty.R
import com.dre.loyalty.core.model.Invoice
import com.dre.loyalty.core.platform.functional.Event
import com.dre.loyalty.core.platform.BaseViewModel
import com.dre.loyalty.core.platform.util.preferences.AuthenticationManager
import com.dre.loyalty.core.view.sheet.SheetListState
import com.dre.loyalty.features.invoice.domain.usecase.GetInvoiceUseCase
import com.dre.loyalty.features.invoice.domain.usecase.GetInvoiceUseCase.Param
import com.dre.loyalty.features.invoice.presentation.list.enumtype.InvoiceType
import com.dre.loyalty.features.invoice.presentation.list.enumtype.SortOrder
import javax.inject.Inject

private const val SORT_SHEET_TITLE = "Urutkan berdasarkan waktu unggah kwitansi"
class InvoiceListPagerViewModel @Inject constructor(
    private val authenticationManager: AuthenticationManager,
    private val getInvoiceUseCase: GetInvoiceUseCase
) : BaseViewModel() {

    private val _invoiceList: MutableLiveData<List<Invoice>> = MutableLiveData()
    val invoiceList: LiveData<List<Invoice>> = _invoiceList

    private val _sortOrder: MutableLiveData<Event<SheetListState>> = MutableLiveData()
    val sortOrder: LiveData<Event<SheetListState>> = _sortOrder

    private val _buttonUploadClicked: MutableLiveData<Event<Boolean>> = MutableLiveData()
    val buttonUploadClicked: LiveData<Event<Boolean>> = _buttonUploadClicked

    private val _selectedItem: MutableLiveData<Event<String>> = MutableLiveData()
    val selectedItem: LiveData<Event<String>> = _selectedItem

    private var selectedSortOrder: SortOrder = SortOrder.ASC
    private lateinit var invoiceType: InvoiceType

    fun init(position: Int) {
        invoiceType = InvoiceType.fromValue(position)
        refresh()
    }

    fun handleSortClicked() {
        _sortOrder.value = Event(SheetListState(
            SORT_SHEET_TITLE,
            SortOrder.values().map { it.sort }.filter { it.isNotEmpty() },
            selectedSortOrder.sort,
            R.dimen.invoice_list_sort_sheet_height
        ))
    }

    fun handleSelectedSort(selected: String) {
        val newOrder = SortOrder.fromValue(selected)
        if (selectedSortOrder == SortOrder.UNKNOWN) {
            selectedSortOrder = newOrder
        }
        if (selectedSortOrder != newOrder) {
            _invoiceList.value = _invoiceList.value?.reversed()
        }
        selectedSortOrder = newOrder
    }

    fun handleButtonUploadClicked() {
        _buttonUploadClicked.value = Event(true)
    }

    fun handleItemClicked(selectedId: String) {
        _selectedItem.value = Event(selectedId)
    }

    fun refresh() {
        _loading.value = View.VISIBLE
        getInvoiceUseCase(Param(
            authenticationManager.getUserId().orEmpty(),
            invoiceType
        )) {
            it.fold(::handleFailure, ::handleSuccessFetchInvoiceList)
        }
    }

    private fun handleSuccessFetchInvoiceList(invoices: List<Invoice>) {
        _loading.value = View.GONE
        _invoiceList.value = invoices
    }
}
