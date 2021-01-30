/*
 * Created by Andreas Oen on 1/18/21 6:22 PM
 * Copyright (c) 2021 . All rights reserved.
 * Last modified 1/18/21 6:22 PM
 * github: https://github.com/oandrz
 */

package com.dre.loyalty.features.invoice.presentation.detail.screen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dre.loyalty.R
import com.dre.loyalty.core.platform.functional.Event
import com.dre.loyalty.features.invoice.presentation.list.enumtype.InvoiceType
import com.dre.loyalty.features.invoice.presentation.entity.VerticalFieldLabelState
import com.dre.loyalty.features.invoice.presentation.detail.enumtype.VerticalValueType
import javax.inject.Inject

class InvoiceDetailViewModel @Inject constructor() : ViewModel() {

    private val _invoiceDetail: MutableLiveData<List<VerticalFieldLabelState>> = MutableLiveData()
    val invoiceDetail: LiveData<List<VerticalFieldLabelState>> = _invoiceDetail

    private val _bannerImage: MutableLiveData<String> = MutableLiveData()
    val bannerImage: LiveData<String> = _bannerImage

    private val _photoView: MutableLiveData<Event<String>> = MutableLiveData()
    val photoView: LiveData<Event<String>> = _photoView

    fun init(id: String) {
    }

    fun handleBannerImageClicked(url: String) {
        _photoView.value = Event(url)
    }
}
