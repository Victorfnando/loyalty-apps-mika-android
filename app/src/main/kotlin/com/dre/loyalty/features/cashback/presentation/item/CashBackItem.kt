/*
 * Created by Andreas Oen on 1/2/21 2:27 PM
 * Copyright (c) 2021 . All rights reserved.
 * Last modified 1/2/21 2:27 PM
 * github: https://github.com/oandrz
 */

package com.dre.loyalty.features.cashback.presentation.item

import android.view.LayoutInflater
import android.view.ViewGroup
import com.dre.loyalty.R
import com.dre.loyalty.core.model.CashBack
import com.dre.loyalty.core.platform.util.extension.formatToCurrency
import com.dre.loyalty.databinding.ItemCashbackBinding
import com.mikepenz.fastadapter.binding.AbstractBindingItem

class CashBackItem(val item: CashBack) : AbstractBindingItem<ItemCashbackBinding>() {
    override val type: Int
        get() = R.id.invoiceItem

    override fun createBinding(inflater: LayoutInflater, parent: ViewGroup?): ItemCashbackBinding {
        return ItemCashbackBinding.inflate(inflater, parent, false)
    }

    override fun bindView(binding: ItemCashbackBinding, payloads: List<Any>) {
        super.bindView(binding, payloads)
        binding.run {
            tvInvoice.text = item.id
            tvPrice.text = item.amount.formatToCurrency(true)
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
