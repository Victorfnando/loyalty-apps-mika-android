/*
 * Created by Andreas Oen on 1/16/21 4:28 PM
 * Copyright (c) 2021 . All rights reserved.
 * Last modified 1/16/21 4:28 PM
 * github: https://github.com/oandrz
 */

package com.dre.loyalty.features.invoice.presentation.item

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.dre.loyalty.R
import com.dre.loyalty.core.util.extension.addBorder
import com.dre.loyalty.core.util.extension.formatToCurrency
import com.dre.loyalty.databinding.ItemInvoiceBinding
import com.dre.loyalty.features.invoice.presentation.entity.Invoice
import com.mikepenz.fastadapter.binding.AbstractBindingItem

class InvoiceListItem(val invoice: Invoice) : AbstractBindingItem<ItemInvoiceBinding>() {

    override val type: Int
        get() = R.id.invoiceListItem

    override fun createBinding(inflater: LayoutInflater, parent: ViewGroup?): ItemInvoiceBinding {
        return ItemInvoiceBinding.inflate(inflater, parent, false)
    }

    override fun bindView(binding: ItemInvoiceBinding, payloads: List<Any>) {
        super.bindView(binding, payloads)
        binding.run {
            tvStatus.text = invoice.status.status
            tvStatus.addBorder(ContextCompat.getColor(tvStatus.context, invoice.status.borderColor))
            tvId.text = tvId.context.resources.getString(
                R.string.invoice_list_format_id, invoice.invoiceId
            )
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
