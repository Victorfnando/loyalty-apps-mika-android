/*
 * Created by Andreas Oen on 1/10/21 4:47 PM
 * Copyright (c) 2021 . All rights reserved.
 * Last modified 1/10/21 4:47 PM
 * github: https://github.com/oandrz
 */

package com.dre.loyalty.features.profile.presentation.profile.screen.item

import android.view.LayoutInflater
import android.view.ViewGroup
import com.dre.loyalty.R
import com.dre.loyalty.databinding.ItemMenuProfileBinding
import com.dre.loyalty.features.profile.presentation.profile.entity.Menu
import com.mikepenz.fastadapter.binding.AbstractBindingItem

class ProfileMenuItem(
    private val menu: Menu,
    var listener: (() -> Unit)? = null
) : AbstractBindingItem<ItemMenuProfileBinding>() {

    override val type: Int
        get() = R.id.profileMenuItem

    override fun createBinding(
        inflater: LayoutInflater,
        parent: ViewGroup?
    ): ItemMenuProfileBinding {
        return ItemMenuProfileBinding.inflate(inflater, parent, false)
    }

    override fun bindView(binding: ItemMenuProfileBinding, payloads: List<Any>) {
        super.bindView(binding, payloads)
        binding.run {
            ivMenu.setImageResource(menu.menuImage)
            tvMenu.setText(menu.text)
            container.setOnClickListener {
                listener?.invoke()
            }
        }
    }

    override fun unbindView(binding: ItemMenuProfileBinding) {
        super.unbindView(binding)
        binding.run {
            tvMenu.text = null
            container.setOnClickListener(null)
        }
    }
}
