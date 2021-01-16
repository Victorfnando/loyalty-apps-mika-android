/*
 * Created by Andreas Oen on 1/16/21 4:28 PM
 * Copyright (c) 2021 . All rights reserved.
 * Last modified 1/16/21 4:28 PM
 * github: https://github.com/oandrz
 */

package com.dre.loyalty.features.invoice.presentation.item

import android.view.LayoutInflater
import android.view.ViewGroup
import com.dre.loyalty.R
import com.dre.loyalty.core.util.extension.formatToCurrency
import com.dre.loyalty.databinding.ItemInvoiceBinding
import com.dre.loyalty.features.invoice.presentation.entity.Invoice
import com.mikepenz.fastadapter.binding.AbstractBindingItem

class InvoiceListItem(private val invoice: Invoice) : AbstractBindingItem<ItemInvoiceBinding>() {

    override val type: Int
        get() = R.id.invoiceListItem

    override fun createBinding(inflater: LayoutInflater, parent: ViewGroup?): ItemInvoiceBinding {
        return ItemInvoiceBinding.inflate(inflater, parent, false)
    }

    override fun bindView(binding: ItemInvoiceBinding, payloads: List<Any>) {
        super.bindView(binding, payloads)
        binding.run {
            tvId.text = invoice.invoiceId
            tvPrice.text = invoice.price.formatToCurrency()
            tvDate.text = invoice.date
            tvLocation.text = invoice.location
        }
    }

    override fun unbindView(binding: ItemInvoiceBinding) {
        super.unbindView(binding)
        binding.run {
            tvId.text = null
            tvPrice.text = null
            tvDate.text = null
            tvLocation.text = null
        }
    }
}
