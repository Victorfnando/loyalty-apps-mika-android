/*
 * Created by Andreas Oen on 1/3/21 4:35 PM
 * Copyright (c) 2021 . All rights reserved.
 * Last modified 1/3/21 4:35 PM
 * github: https://github.com/oandrz
 */

package com.dre.loyalty.features.news.presentation.view

import android.os.Build
import android.text.Html
import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.load.model.LazyHeaders
import com.dre.loyalty.R
import com.dre.loyalty.core.model.News
import com.dre.loyalty.databinding.ItemNewsVerticalBinding
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
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                tvTitle.text = Html.fromHtml(item.title, Html.FROM_HTML_MODE_COMPACT)
                tvDesc.text = Html.fromHtml(item.desc, Html.FROM_HTML_MODE_COMPACT)
            } else {
                tvTitle.text = item.title
                tvDesc.text = item.desc
            }

            val url = GlideUrl(
                    item.imageUrl,
                    LazyHeaders.Builder()
                            .addHeader("User-Agent", "appllication")
                            .build()
            )
            Glide.with(ivBanner.context)
                    .load(url)
                    .into(ivBanner)
        }
    }

    override fun unbindView(binding: ItemNewsVerticalBinding) {
        super.unbindView(binding)
        binding.tvDate.text = null
        binding.tvDesc.text = null
        binding.tvTitle.text = null
        Glide.with(binding.ivBanner.context)
                .clear(binding.ivBanner)
    }
}
