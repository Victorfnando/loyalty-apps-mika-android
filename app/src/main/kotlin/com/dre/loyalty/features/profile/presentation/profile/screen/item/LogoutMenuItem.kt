/*
 * Created by Andreas Oen on 1/10/21 7:39 PM
 * Copyright (c) 2021 . All rights reserved.
 * Last modified 1/10/21 7:39 PM
 * github: https://github.com/oandrz
 */

package com.dre.loyalty.features.profile.presentation.profile.screen.item

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dre.loyalty.R
import com.dre.loyalty.databinding.ItemMenuProfileBinding
import com.mikepenz.fastadapter.binding.AbstractBindingItem

class LogoutMenuItem(private val listener: () -> Unit) : AbstractBindingItem<ItemMenuProfileBinding>() {

    override val type: Int
        get() = R.id.logoutMenuItem

    override fun createBinding(
        inflater: LayoutInflater,
        parent: ViewGroup?
    ): ItemMenuProfileBinding {
        return ItemMenuProfileBinding.inflate(inflater, parent, false)
    }

    override fun bindView(binding: ItemMenuProfileBinding, payloads: List<Any>) {
        super.bindView(binding, payloads)
        binding.run {
            tvMenu.setText(R.string.profile_menu_logout)
            tvMenu.setTextColor(Color.RED)
            ivChevron.visibility = View.GONE
            ivMenu.setImageResource(R.drawable.ic_logout)
            container.setOnClickListener {
                listener.invoke()
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
