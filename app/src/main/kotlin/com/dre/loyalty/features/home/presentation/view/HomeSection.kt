/*
 * Created by Andreas Oen on 1/2/21 1:25 PM
 * Copyright (c) 2021 . All rights reserved.
 * Last modified 1/2/21 1:22 PM
 * github: https://github.com/oandrz
 */

package com.dre.loyalty.features.home.presentation.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SnapHelper
import com.dre.loyalty.R
import com.dre.loyalty.databinding.SectionHomeBinding
import com.dre.loyalty.features.cashback.presentation.item.CashBackItem
import com.dre.loyalty.features.news.presentation.view.NewsItem
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.adapters.ItemAdapter
import com.mikepenz.fastadapter.binding.AbstractBindingItem
import com.mikepenz.fastadapter.binding.BindingViewHolder

class HomeSection(
    val categoryName: String,
    private val injectedLayoutManager: RecyclerView.LayoutManager,
    private val body: List<ItemAdapter<*>>
) : AbstractBindingItem<SectionHomeBinding>() {

    var dividerItemDecoration: RecyclerView.ItemDecoration? = null
    var itemClickListener: ((String) -> Unit)? = null
    var seeAllClickListener: (() -> Unit)? = null
    var snapHelper: SnapHelper? = null

    override val type: Int
        get() = R.id.homeSection

    override fun bindView(holder: BindingViewHolder<SectionHomeBinding>, payloads: List<Any>) {
        super.bindView(holder, payloads)
        snapHelper?.attachToRecyclerView(holder.binding.listBody)
        holder.binding.listBody.run {
            dividerItemDecoration?.let { addItemDecoration(it) }
            layoutManager = injectedLayoutManager
            adapter = FastAdapter.with(body).also {
                it.onClickListener = { _, _, item, _ ->
                    if (item is CashBackItem) {
                        itemClickListener?.invoke(item.item.receiptId)
                    } else if (item is NewsItem) {
                        itemClickListener?.invoke(item.item.id)
                    }
                    true
                }
            }
        }
        holder.binding.tvCategoryName.text = categoryName
        holder.binding.tvSeeLabel.setOnClickListener {
            seeAllClickListener?.invoke()
        }
    }

    override fun unbindView(holder: BindingViewHolder<SectionHomeBinding>) {
        super.unbindView(holder)
        holder.binding.listBody.adapter = null
        holder.binding.tvCategoryName.text = null
    }

    override fun createBinding(inflater: LayoutInflater, parent: ViewGroup?): SectionHomeBinding {
        return SectionHomeBinding.inflate(inflater, parent, false)
    }
}
