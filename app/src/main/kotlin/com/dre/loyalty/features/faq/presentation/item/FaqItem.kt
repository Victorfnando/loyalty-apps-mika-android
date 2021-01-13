/*
 * Created by Andreas Oen on 1/13/21 7:51 PM
 * Copyright (c) 2021 . All rights reserved.
 * Last modified 1/13/21 7:51 PM
 * github: https://github.com/oandrz
 */

package com.dre.loyalty.features.faq.presentation.item

import android.view.View
import com.dre.loyalty.R
import com.dre.loyalty.features.faq.presentation.item.FaqItem.ViewHolder
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.ISubItem
import com.mikepenz.fastadapter.expandable.items.AbstractExpandableItem
import kotlinx.android.synthetic.main.item_faq.view.*

class FaqItem(private val content: String) : AbstractExpandableItem<ViewHolder>(),
    ISubItem<ViewHolder> {

    override val type: Int
        get() = R.id.faqItem

    override val layoutRes: Int
        get() = R.layout.item_faq

    override fun getViewHolder(v: View): ViewHolder = ViewHolder(v)

    class ViewHolder(val view: View) : FastAdapter.ViewHolder<FaqItem>(view) {
        override fun bindView(item: FaqItem, payloads: List<Any>) {
            view.tv_content.text = item.content
        }

        override fun unbindView(item: FaqItem) {
            view.tv_content.text = null
        }
    }
}
