/*
 * Created by Andreas Oen on 1/18/21 6:22 PM
 * Copyright (c) 2021 . All rights reserved.
 * Last modified 1/18/21 6:22 PM
 * github: https://github.com/oandrz
 */

package com.dre.loyalty.features.invoicedetail.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dre.loyalty.R
import com.dre.loyalty.core.functional.Event
import com.dre.loyalty.core.util.extension.formatToCurrency
import com.dre.loyalty.features.invoice.presentation.entity.Invoice
import com.dre.loyalty.features.invoice.presentation.enumtype.InvoiceType
import com.dre.loyalty.features.invoicedetail.presentation.entity.VerticalFieldLabelState
import com.dre.loyalty.features.invoicedetail.presentation.enumtype.VerticalValueType
import javax.inject.Inject

class InvoiceDetailViewModel @Inject constructor() : ViewModel() {

    private val _invoiceDetail: MutableLiveData<List<VerticalFieldLabelState>> = MutableLiveData()
    val invoiceDetail: LiveData<List<VerticalFieldLabelState>> = _invoiceDetail

    private val _bannerImage: MutableLiveData<String> = MutableLiveData()
    val bannerImage: LiveData<String> = _bannerImage

    private val _photoView: MutableLiveData<Event<String>> = MutableLiveData()
    val photoView: LiveData<Event<String>> = _photoView

    fun init(id: String) {
        when {
            id.contains("red") -> {
                val invoice = Invoice("https://miro.medium.com/max/1200/1*mk1-6aYaf_Bes1E3Imhc0A.jpeg", "4", 50_000L, "23 Desember 2020", "Depok", InvoiceType.DENIED)
                _bannerImage.value = invoice.imageUrl
                _invoiceDetail.value = listOf(
                    VerticalFieldLabelState(R.string.invoiceDetail_label_id, invoice.invoiceId),
                    VerticalFieldLabelState(R.string.invoiceDetail_label_status, invoice.status.status, VerticalValueType.STATUS, invoice.status.borderColor),
                    VerticalFieldLabelState(R.string.invoiceDetail_label_reason, "sakit perut"),
                    VerticalFieldLabelState(R.string.invoiceDetail_label_eWallet, "Gopay"),
                    VerticalFieldLabelState(R.string.invoiceDetail_label_phone, "0897723121"),
                    VerticalFieldLabelState(R.string.invoiceDetail_label_transactionAmount, invoice.price.formatToCurrency()),
                    VerticalFieldLabelState(R.string.invoiceDetail_label_transactionDate, invoice.date),
                    VerticalFieldLabelState(R.string.invoiceDetail_label_branch, invoice.location),
                )
            }
            id.contains("yel") -> {
                val invoice = Invoice("https://miro.medium.com/max/1200/1*mk1-6aYaf_Bes1E3Imhc0A.jpeg", "3", 420_000L, "22 Desember 2020", "Planet", InvoiceType.PROCESS)
                _bannerImage.value = invoice.imageUrl
                _invoiceDetail.value = listOf(
                    VerticalFieldLabelState(R.string.invoiceDetail_label_id, invoice.invoiceId),
                    VerticalFieldLabelState(R.string.invoiceDetail_label_status, invoice.status.status, VerticalValueType.STATUS, invoice.status.borderColor),
                    VerticalFieldLabelState(R.string.invoiceDetail_label_cashback, ""),
                    VerticalFieldLabelState(R.string.invoiceDetail_label_eWallet, "Gopay"),
                    VerticalFieldLabelState(R.string.invoiceDetail_label_phone, "0897723121"),
                    VerticalFieldLabelState(R.string.invoiceDetail_label_transactionAmount, invoice.price.formatToCurrency()),
                    VerticalFieldLabelState(R.string.invoiceDetail_label_transactionDate, invoice.date),
                    VerticalFieldLabelState(R.string.invoiceDetail_label_branch, invoice.location),
                )
            }
            id.contains("gre") -> {
                val invoice = Invoice("https://miro.medium.com/max/1200/1*mk1-6aYaf_Bes1E3Imhc0A.jpeg", "2", 30_000L, "21 Desember 2020", "Jakarta", InvoiceType.ACCEPTED)
                _bannerImage.value = invoice.imageUrl
                _invoiceDetail.value = listOf(
                    VerticalFieldLabelState(R.string.invoiceDetail_label_id, invoice.invoiceId),
                    VerticalFieldLabelState(R.string.invoiceDetail_label_status, invoice.status.status, VerticalValueType.STATUS, invoice.status.borderColor),
                    VerticalFieldLabelState(R.string.invoiceDetail_label_cashback, invoice.price.formatToCurrency(true)),
                    VerticalFieldLabelState(R.string.invoiceDetail_label_eWallet, "Gopay"),
                    VerticalFieldLabelState(R.string.invoiceDetail_label_phone, "0897723121"),
                    VerticalFieldLabelState(R.string.invoiceDetail_label_transactionAmount, invoice.price.formatToCurrency()),
                    VerticalFieldLabelState(R.string.invoiceDetail_label_transactionDate, invoice.date),
                    VerticalFieldLabelState(R.string.invoiceDetail_label_branch, invoice.location),
                )
            }
            else -> Invoice("", "4", 50_000L, "23 Desember 2020", "Depok", InvoiceType.DENIED)
        }
    }

    fun handleBannerImageClicked(url: String) {
        _photoView.value = Event(url)
    }
}
