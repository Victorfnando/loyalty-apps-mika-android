/*
 * Created by Andreas Oen on 1/2/21 2:27 PM
 * Copyright (c) 2021 . All rights reserved.
 * Last modified 1/2/21 2:27 PM
 * github: https://github.com/oandrz
 */

package com.dre.loyalty.features.home.presentation.view

import android.view.LayoutInflater
import android.view.ViewGroup
import com.dre.loyalty.R
import com.dre.loyalty.databinding.ItemCashbackBinding
import com.dre.loyalty.features.home.presentation.entity.Invoice
import com.mikepenz.fastadapter.binding.AbstractBindingItem

class CashBackItem(val item: Invoice) : AbstractBindingItem<ItemCashbackBinding>() {
    override val type: Int
        get() = R.id.invoiceItem

    override fun createBinding(inflater: LayoutInflater, parent: ViewGroup?): ItemCashbackBinding {
        return ItemCashbackBinding.inflate(inflater, parent, false)
    }

    override fun bindView(binding: ItemCashbackBinding, payloads: List<Any>) {
        super.bindView(binding, payloads)
        binding.run {
            tvInvoice.text = item.id
            tvPrice.text = item.price.toString()
            tvDate.text = item.date
        }
    }

    override fun unbindView(binding: ItemCashbackBinding) {
        super.unbindView(binding)
        binding.run {
            tvInvoice.text = null
            tvPrice.text = null
            tvDate.text = null
        }
    }
}
