/*
 * Created by Andreas Oen on 1/18/21 4:48 PM
 * Copyright (c) 2021 . All rights reserved.
 * Last modified 1/18/21 4:48 PM
 * github: https://github.com/oandrz
 */

package com.dre.loyalty.features.invoice.presentation.entity

import androidx.annotation.ColorRes
import androidx.annotation.StringRes
import com.dre.loyalty.features.invoice.presentation.detail.enumtype.VerticalValueType

class VerticalFieldLabelState (
    @StringRes val label: Int,
    val value: String,
    val typeVertical: VerticalValueType? = VerticalValueType.NORMAL,
    @ColorRes val borderColor: Int? = null
)
