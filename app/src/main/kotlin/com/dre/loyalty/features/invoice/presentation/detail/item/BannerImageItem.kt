/*
 * Created by Andreas Oen on 1/18/21 4:21 PM
 * Copyright (c) 2021 . All rights reserved.
 * Last modified 1/18/21 4:21 PM
 * github: https://github.com/oandrz
 */

package com.dre.loyalty.features.invoice.presentation.detail.item

import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.dre.loyalty.R
import com.dre.loyalty.databinding.ItemBannerBinding
import com.mikepenz.fastadapter.binding.AbstractBindingItem

class BannerImageItem(val imageUrl: String) : AbstractBindingItem<ItemBannerBinding>() {

    override val type: Int
        get() = R.id.bannerItem

    override fun createBinding(inflater: LayoutInflater, parent: ViewGroup?): ItemBannerBinding {
        return ItemBannerBinding.inflate(inflater, parent, false)
    }

    override fun bindView(binding: ItemBannerBinding, payloads: List<Any>) {
        super.bindView(binding, payloads)
        Glide.with(binding.ivBanner.context)
            .load(imageUrl)
            .into(binding.ivBanner)
    }

    override fun unbindView(binding: ItemBannerBinding) {
        super.unbindView(binding)
        Glide.with(binding.ivBanner.context).clear(binding.ivBanner)
    }
}
