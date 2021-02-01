/*
 * Created by Andreas Oen on 1/16/21 4:28 PM
 * Copyright (c) 2021 . All rights reserved.
 * Last modified 1/16/21 4:28 PM
 * github: https://github.com/oandrz
 */

package com.dre.loyalty.features.invoice.presentation.list.item

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.load.model.LazyHeaders
import com.dre.loyalty.R
import com.dre.loyalty.core.model.Invoice
import com.dre.loyalty.core.platform.util.extension.addBorder
import com.dre.loyalty.core.platform.util.extension.formatToCurrency
import com.dre.loyalty.databinding.ItemInvoiceBinding
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
                R.string.invoice_list_format_id, invoice.receiptId
            )
            tvPrice.text = invoice.transactionPrice.formatToCurrency()
            tvDate.text = invoice.date
            tvLocation.text = invoice.location
            val url = GlideUrl(
                    invoice.imageUrl,
                    LazyHeaders.Builder()
                            .addHeader("User-Agent", "appllication")
                            .build()
            )
            Glide.with(ivInvoice.context)
                    .load(url)
                    .into(ivInvoice)


        }
    }

    override fun unbindView(binding: ItemInvoiceBinding) {
        super.unbindView(binding)
        binding.run {
            tvId.text = null
            tvPrice.text = null
            tvDate.text = null
            tvLocation.text = null

            Glide.with(binding.ivInvoice.context)
                    .clear(binding.ivInvoice)
        }
    }
}
