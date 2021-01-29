/*
 * Created by Andreas Oen on 1/18/21 4:42 PM
 * Copyright (c) 2021 . All rights reserved.
 * Last modified 1/18/21 4:42 PM
 * github: https://github.com/oandrz
 */

package com.dre.loyalty.features.invoice.presentation.detail.item

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.dre.loyalty.R
import com.dre.loyalty.core.platform.util.extension.addBorder
import com.dre.loyalty.core.platform.util.extension.showIfNotEmpty
import com.dre.loyalty.databinding.ItemVerticalFieldLabelBinding
import com.dre.loyalty.features.invoice.presentation.entity.VerticalFieldLabelState
import com.dre.loyalty.features.invoice.presentation.detail.enumtype.VerticalValueType
import com.mikepenz.fastadapter.binding.AbstractBindingItem

class VerticalFieldLabelItem(
    val state: VerticalFieldLabelState
) : AbstractBindingItem<ItemVerticalFieldLabelBinding>() {

    override val type: Int
        get() = R.id.verticalFieldLabelItem

    override fun createBinding(
        inflater: LayoutInflater,
        parent: ViewGroup?
    ): ItemVerticalFieldLabelBinding {
        return ItemVerticalFieldLabelBinding.inflate(inflater, parent, false)
    }

    override fun bindView(binding: ItemVerticalFieldLabelBinding, payloads: List<Any>) {
        super.bindView(binding, payloads)
        binding.run {
            tvLabel.text = tvLabel.context.resources.getString(state.label)
            tvContent.text = state.value.showIfNotEmpty()
            if (state.typeVertical == VerticalValueType.STATUS) {
                tvContent.addBorder(ContextCompat.getColor(tvContent.context, state.borderColor ?: 0))
            }
        }
    }

    override fun unbindView(binding: ItemVerticalFieldLabelBinding) {
        super.unbindView(binding)
        binding.run {
            tvLabel.text = null
            tvContent.text = null
        }
    }
}
