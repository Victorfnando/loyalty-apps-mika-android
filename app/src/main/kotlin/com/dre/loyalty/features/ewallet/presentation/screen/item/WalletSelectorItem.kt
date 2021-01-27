/*
 * Created by Andreas Oen on 1/21/21 7:02 PM
 * Copyright (c) 2021 . All rights reserved.
 * Last modified 1/21/21 7:02 PM
 * github: https://github.com/oandrz
 */

package com.dre.loyalty.features.ewallet.presentation.screen.item

import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.dre.loyalty.R
import com.dre.loyalty.databinding.ItemWalletBinding
import com.dre.loyalty.features.ewallet.presentation.entity.Wallet
import com.mikepenz.fastadapter.binding.AbstractBindingItem

class WalletSelectorItem(val wallet: Wallet) : AbstractBindingItem<ItemWalletBinding>() {

    override val type: Int
        get() = R.id.walletItem

    override fun createBinding(inflater: LayoutInflater, parent: ViewGroup?): ItemWalletBinding {
        return ItemWalletBinding.inflate(inflater, parent, false)
    }

    override fun bindView(binding: ItemWalletBinding, payloads: List<Any>) {
        super.bindView(binding, payloads)
        binding.tvText.text = wallet.text
        Glide.with(binding.ivLogo.context)
            .load(wallet.imageUrl)
            .into(binding.ivLogo)
    }

    override fun unbindView(binding: ItemWalletBinding) {
        super.unbindView(binding)
        binding.tvText.text = null
        Glide.with(binding.ivLogo.context).clear(binding.ivLogo)
    }
}
