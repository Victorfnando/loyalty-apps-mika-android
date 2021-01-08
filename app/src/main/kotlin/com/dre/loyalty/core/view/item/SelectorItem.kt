/*
 * Created by Andreas Oen on 1/7/21 7:41 PM
 * Copyright (c) 2021 . All rights reserved.
 * Last modified 1/7/21 7:41 PM
 * github: https://github.com/oandrz
 */

package com.dre.loyalty.core.view.item

import android.view.LayoutInflater
import android.view.ViewGroup
import com.dre.loyalty.R
import com.dre.loyalty.databinding.ItemSelectorBinding
import com.mikepenz.fastadapter.binding.AbstractBindingItem

class SelectorItem(val text: String) : AbstractBindingItem<ItemSelectorBinding>() {
    override val type: Int
        get() = R.id.selectorItem

    override fun createBinding(inflater: LayoutInflater, parent: ViewGroup?): ItemSelectorBinding {
        return ItemSelectorBinding.inflate(inflater, parent, false)
    }

    override fun bindView(binding: ItemSelectorBinding, payloads: List<Any>) {
        super.bindView(binding, payloads)
        binding.run {
            tvText.text = text
            radioSelector.isChecked = isSelected
        }
    }

    override fun unbindView(binding: ItemSelectorBinding) {
        super.unbindView(binding)
        binding.run {
            tvText.text = null
            radioSelector.isChecked = false
        }
    }
}
