/*
 * Created by Andreas Oen on 1/16/21 4:18 PM
 * Copyright (c) 2021 . All rights reserved.
 * Last modified 1/16/21 4:18 PM
 * github: https://github.com/oandrz
 */

package com.dre.loyalty.features.invoice.presentation.screen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dre.loyalty.R
import com.dre.loyalty.core.functional.Event
import com.dre.loyalty.core.platform.BaseViewModel
import com.dre.loyalty.core.view.sheet.SheetListState
import com.dre.loyalty.features.invoice.presentation.entity.Invoice
import com.dre.loyalty.features.invoice.presentation.enumtype.InvoiceType
import com.dre.loyalty.features.invoice.presentation.enumtype.SortOrder
import javax.inject.Inject

private const val SORT_SHEET_TITLE = "Urutkan berdasarkan waktu unggah kwitansi"
class InvoiceListPagerViewModel @Inject constructor() : BaseViewModel() {

    private val _invoiceList: MutableLiveData<List<Invoice>> = MutableLiveData()
    val invoiceList: LiveData<List<Invoice>> = _invoiceList

    private val _sortOrder: MutableLiveData<Event<SheetListState>> = MutableLiveData()
    val sortOrder: LiveData<Event<SheetListState>> = _sortOrder

    private val _buttonUploadClicked: MutableLiveData<Event<Boolean>> = MutableLiveData()
    val buttonUploadClicked: LiveData<Event<Boolean>> = _buttonUploadClicked

    private var originOrderInvoiceList: List<Invoice> = listOf()
    private var selectedSortOrder: SortOrder = SortOrder.ASC
    private lateinit var invoiceType: InvoiceType

    fun init(position: Int) {
        invoiceType = InvoiceType.fromValue(position)
        _invoiceList.value = when(invoiceType) {
            InvoiceType.ALL -> {
               listOf(
                   Invoice("", "1", 20_000L, "20 Desember 2020", "Bekasi Timur", InvoiceType.ACCEPTED)
               )
            }
            InvoiceType.ACCEPTED -> {
                listOf(
                    Invoice("", "2", 30_000L, "21 Desember 2020", "Jakarta", InvoiceType.ACCEPTED),
                    Invoice("", "2", 30_000L, "21 Desember 2020", "Jakarta", InvoiceType.ACCEPTED),
                            Invoice("", "2", 30_000L, "21 Desember 2020", "Jakarta", InvoiceType.ACCEPTED),
                Invoice("", "2", 30_000L, "21 Desember 2020", "Jakarta", InvoiceType.ACCEPTED),
                Invoice("", "2", 30_000L, "21 Desember 2020", "Jakarta", InvoiceType.ACCEPTED),
                Invoice("", "2", 30_000L, "21 Desember 2020", "Jakarta", InvoiceType.ACCEPTED),
                Invoice("", "2", 30_000L, "27 Desember 2020", "Jakarta", InvoiceType.ACCEPTED),

                )
            }
            InvoiceType.PROCESS -> {
                listOf(
                    Invoice("", "3", 420_000L, "22 Desember 2020", "Planet", InvoiceType.PROCESS)
                )
            }
            InvoiceType.DENIED -> {
                listOf(
                    Invoice("", "4", 50_000L, "23 Desember 2020", "Depok", InvoiceType.DENIED)
                )
            }
            else -> throw IllegalStateException()
        }
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
}
