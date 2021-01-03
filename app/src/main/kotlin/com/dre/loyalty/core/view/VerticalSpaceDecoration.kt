/*
 * Created by Andreas Oen on 1/2/21 4:07 PM
 * Copyright (c) 2021 . All rights reserved.
 * Last modified 1/2/21 4:07 PM
 * github: https://github.com/oandrz
 */

package com.dre.loyalty.core.view

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class VerticalSpaceDecoration(
    private val verticalSpaceHeight: Int
) : RecyclerView.ItemDecoration() {

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
        if (parent.getChildAdapterPosition(view) == 0) {
            outRect.top = verticalSpaceHeight
        }
        outRect.bottom = verticalSpaceHeight
    }
}
