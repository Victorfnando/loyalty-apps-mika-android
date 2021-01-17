/*
 * Created by Andreas Oen on 1/14/21 8:42 PM
 * Copyright (c) 2021 . All rights reserved.
 * Last modified 1/14/21 8:42 PM
 * github: https://github.com/oandrz
 */

package com.dre.loyalty.core.view.sheet

import android.os.Parcelable
import androidx.annotation.DimenRes
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SheetListState(
    val title: String,
    val listContent: List<String>,
    val selectedItem: String?,
    @DimenRes val peekHeight: Int? = null
) : Parcelable
