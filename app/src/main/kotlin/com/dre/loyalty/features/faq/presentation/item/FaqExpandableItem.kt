/*
 * Created by Andreas Oen on 1/13/21 7:18 PM
 * Copyright (c) 2021 . All rights reserved.
 * Last modified 1/13/21 7:18 PM
 * github: https://github.com/oandrz
 */

package com.dre.loyalty.features.faq.presentation.item

import android.view.View
import androidx.core.view.ViewCompat
import com.dre.loyalty.R
import com.dre.loyalty.features.faq.presentation.item.FaqExpandableItem.ViewHolder
import com.mikepenz.fastadapter.*
import com.mikepenz.fastadapter.expandable.items.AbstractExpandableItem
import kotlinx.android.synthetic.main.item_faq_expandable.view.*

class FaqExpandableItem(val text: String) : AbstractExpandableItem<ViewHolder>(),
    IClickable<FaqExpandableItem> {

    private var clickListener: ClickListener<FaqExpandableItem>? = null

    override var onItemClickListener: ClickListener<FaqExpandableItem>? = { v: View?, adapter: IAdapter<FaqExpandableItem>, item: FaqExpandableItem, position: Int ->
        if (item.subItems.isNotEmpty()) {
            v?.iv_chevron?.let {
                if (!item.isExpanded) {
                    ViewCompat.animate(it).rotation(0f).start()
                } else {
                    ViewCompat.animate(it).rotation(180f).start()
                }
            }
        }
        clickListener?.invoke(v, adapter, item, position) ?: true
    }
    set(value) {
        clickListener = value
        field = value
    }

    override var onPreItemClickListener: ClickListener<FaqExpandableItem>?
        get() = null
        set(value) {}

    override var isSelectable: Boolean
        get() = subItems.isEmpty()
        set(value) {
            super.isSelectable = value
        }

    override val layoutRes: Int
        get() = R.layout.item_faq_expandable

    override val type: Int
        get() = R.id.faqExpandableItem

    override fun getViewHolder(v: View): ViewHolder = ViewHolder(v)

    class ViewHolder(val view: View) : FastAdapter.ViewHolder<FaqExpandableItem>(view) {
        override fun bindView(item: FaqExpandableItem, payloads: List<Any>) {
            view.run {
                clearAnimation()
                tv_title.text = item.text
                if (item.subItems.isEmpty()) {
                    iv_chevron.visibility = View.GONE
                } else {
                    iv_chevron.visibility = View.VISIBLE
                }

                if (item.isExpanded) {
                    iv_chevron.rotation = 180f
                } else {
                    iv_chevron.rotation = 0f
                }
            }
        }

        override fun unbindView(item: FaqExpandableItem) {
            view.tv_title.text = null
            view.iv_chevron.clearAnimation()
        }
    }
}
