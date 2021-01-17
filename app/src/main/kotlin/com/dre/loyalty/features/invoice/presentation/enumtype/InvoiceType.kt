/*
 * Created by Andreas Oen on 1/16/21 4:19 PM
 * Copyright (c) 2021 . All rights reserved.
 * Last modified 1/16/21 4:19 PM
 * github: https://github.com/oandrz
 */

package com.dre.loyalty.features.invoice.presentation.enumtype

import androidx.annotation.ColorRes
import com.dre.loyalty.R

enum class InvoiceType(
    val position: Int,
    val status: String,
    @ColorRes val borderColor: Int
) {
    ALL(0, "Semua", R.color.white),
    PROCESS(1, "Diproses", R.color.warning_darker),
    ACCEPTED(2, "Diterima", R.color.success_darker),
    DENIED(3, "Ditolak", R.color.error_darker),
    UNKNOWN(-1, "Unknown", R.color.white);

    companion object {
        fun fromValue(position: Int) : InvoiceType {
            val types = values()
            return types.find { it.position == position } ?: UNKNOWN
        }
    }
}
