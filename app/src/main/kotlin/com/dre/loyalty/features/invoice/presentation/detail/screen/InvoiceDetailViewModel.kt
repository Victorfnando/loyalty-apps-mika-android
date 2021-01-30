/*
 * Created by Andreas Oen on 1/18/21 6:22 PM
 * Copyright (c) 2021 . All rights reserved.
 * Last modified 1/18/21 6:22 PM
 * github: https://github.com/oandrz
 */

package com.dre.loyalty.features.invoice.presentation.detail.screen

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dre.loyalty.R
import com.dre.loyalty.core.model.Invoice
import com.dre.loyalty.core.platform.BaseViewModel
import com.dre.loyalty.core.platform.functional.Event
import com.dre.loyalty.core.platform.util.extension.formatToCurrency
import com.dre.loyalty.features.invoice.domain.usecase.GetInvoiceDetailUseCase
import com.dre.loyalty.features.invoice.presentation.list.enumtype.InvoiceType
import com.dre.loyalty.features.invoice.presentation.entity.VerticalFieldLabelState
import com.dre.loyalty.features.invoice.presentation.detail.enumtype.VerticalValueType
import javax.inject.Inject

class InvoiceDetailViewModel @Inject constructor(
    private val getInvoiceDetailUseCase: GetInvoiceDetailUseCase
) : BaseViewModel() {

    private val _invoiceDetail: MutableLiveData<List<VerticalFieldLabelState>> = MutableLiveData()
    val invoiceDetail: LiveData<List<VerticalFieldLabelState>> = _invoiceDetail

    private val _bannerImage: MutableLiveData<String> = MutableLiveData()
    val bannerImage: LiveData<String> = _bannerImage

    private val _photoView: MutableLiveData<Event<String>> = MutableLiveData()
    val photoView: LiveData<Event<String>> = _photoView

    private lateinit var selectedId: String

    fun init(id: String) {
        selectedId = id
        refresh()
    }

    fun handleBannerImageClicked(url: String) {
        _photoView.value = Event(url)
    }

    fun refresh() {
        _loading.value = View.VISIBLE
        getInvoiceDetailUseCase(selectedId) {
            it.fold(::handleFailure, ::handleSuccessGetDetail)
        }
    }

    private fun handleSuccessGetDetail(invoice: Invoice) {
        _loading.value = View.GONE
        _bannerImage.value = invoice.imageUrl
        val listState = mutableListOf(
            VerticalFieldLabelState(R.string.invoiceDetail_label_id, invoice.receiptId),
            VerticalFieldLabelState(R.string.invoiceDetail_label_status, invoice.status.status, VerticalValueType.STATUS, invoice.status.borderColor),
            VerticalFieldLabelState(R.string.invoiceDetail_label_eWallet, invoice.walletName),
            VerticalFieldLabelState(R.string.invoiceDetail_label_phone, invoice.phoneNumber),
            VerticalFieldLabelState(R.string.invoiceDetail_label_transactionAmount, invoice.transactionPrice.formatToCurrency()),
            VerticalFieldLabelState(R.string.invoiceDetail_label_transactionDate, invoice.date),
            VerticalFieldLabelState(R.string.invoiceDetail_label_branch, invoice.location),
        )

        when(invoice.status) {
            InvoiceType.DENIED -> {
                listState.add(2, VerticalFieldLabelState(R.string.invoiceDetail_label_reason, invoice.reason))
            }
            InvoiceType.ACCEPTED -> {
                listState.add(2, VerticalFieldLabelState(R.string.invoiceDetail_label_cashback, invoice.cashBackAmount.formatToCurrency(true)))
            }
            InvoiceType.PROCESS -> {
                listState.add(2, VerticalFieldLabelState(R.string.invoiceDetail_label_cashback, "-"))
            }
        }

        _invoiceDetail.value = listState
    }
}
