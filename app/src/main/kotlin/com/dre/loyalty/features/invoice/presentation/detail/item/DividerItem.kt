/*
 * Created by Andreas Oen on 1/18/21 4:38 PM
 * Copyright (c) 2021 . All rights reserved.
 * Last modified 1/18/21 4:38 PM
 * github: https://github.com/oandrz
 */

package com.dre.loyalty.features.invoice.presentation.detail.item

import android.view.LayoutInflater
import android.view.ViewGroup
import com.dre.loyalty.R
import com.dre.loyalty.databinding.ItemDividerBinding
import com.mikepenz.fastadapter.binding.AbstractBindingItem

class DividerItem : AbstractBindingItem<ItemDividerBinding>() {

    override val type: Int
        get() = R.id.dividerItem

    override fun createBinding(inflater: LayoutInflater, parent: ViewGroup?): ItemDividerBinding {
        return ItemDividerBinding.inflate(inflater, parent, false)
    }
}
