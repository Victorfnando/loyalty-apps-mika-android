/*
 * Created by Andreas Oen on 1/2/21 4:27 PM
 * Copyright (c) 2021 . All rights reserved.
 * Last modified 1/2/21 4:27 PM
 * github: https://github.com/oandrz
 */

package com.dre.loyalty.core.view

import android.content.Context
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView

class VerticalDividerDecoration (context: Context, orientation: Int): DividerItemDecoration(context, orientation) {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        if (parent.getChildAdapterPosition(view) == parent.adapter!!.itemCount - 1) {
            outRect.bottom = 0
        }
    }
}
