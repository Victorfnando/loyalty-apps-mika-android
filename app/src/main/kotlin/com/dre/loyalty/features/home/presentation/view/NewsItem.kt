/*
 * Created by Andreas Oen on 1/2/21 3:03 PM
 * Copyright (c) 2021 . All rights reserved.
 * Last modified 1/2/21 3:03 PM
 * github: https://github.com/oandrz
 */

package com.dre.loyalty.features.home.presentation.view

import android.view.LayoutInflater
import android.view.ViewGroup
import com.dre.loyalty.R
import com.dre.loyalty.databinding.ItemNewsBinding
import com.dre.loyalty.features.home.presentation.entity.News
import com.mikepenz.fastadapter.binding.AbstractBindingItem

class NewsItem(val item: News) : AbstractBindingItem<ItemNewsBinding>() {

    override val type: Int
        get() = R.id.newsItem

    override fun createBinding(inflater: LayoutInflater, parent: ViewGroup?): ItemNewsBinding {
        return ItemNewsBinding.inflate(inflater, parent, false)
    }

    override fun bindView(binding: ItemNewsBinding, payloads: List<Any>) {
        super.bindView(binding, payloads)
        binding.apply {
            tvDate.text = item.date
            tvDesc.text = item.desc
            tvTitle.text = item.title
        }
    }

    override fun unbindView(binding: ItemNewsBinding) {
        super.unbindView(binding)
        binding.tvDate.text = null
        binding.tvDesc.text = null
        binding.tvTitle.text = null
    }
}
