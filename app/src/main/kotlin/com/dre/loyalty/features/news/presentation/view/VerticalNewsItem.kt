/*
 * Created by Andreas Oen on 1/3/21 4:35 PM
 * Copyright (c) 2021 . All rights reserved.
 * Last modified 1/3/21 4:35 PM
 * github: https://github.com/oandrz
 */

package com.dre.loyalty.features.news.presentation.view

import android.view.LayoutInflater
import android.view.ViewGroup
import com.dre.loyalty.R
import com.dre.loyalty.databinding.ItemNewsVerticalBinding
import com.dre.loyalty.features.news.presentation.entity.News
import com.mikepenz.fastadapter.binding.AbstractBindingItem

class VerticalNewsItem(val item: News) : AbstractBindingItem<ItemNewsVerticalBinding>() {

    override val type: Int
        get() = R.id.newsItemVertical

    override fun createBinding(inflater: LayoutInflater, parent: ViewGroup?): ItemNewsVerticalBinding {
        return ItemNewsVerticalBinding.inflate(inflater, parent, false)
    }

    override fun bindView(binding: ItemNewsVerticalBinding, payloads: List<Any>) {
        super.bindView(binding, payloads)
        binding.apply {
            tvDate.text = item.date
            tvDesc.text = item.desc
            tvTitle.text = item.title
        }
    }

    override fun unbindView(binding: ItemNewsVerticalBinding) {
        super.unbindView(binding)
        binding.tvDate.text = null
        binding.tvDesc.text = null
        binding.tvTitle.text = null
    }
}
