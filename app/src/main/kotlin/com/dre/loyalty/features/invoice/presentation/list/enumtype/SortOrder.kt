/*
 * Created by Andreas Oen on 1/17/21 3:31 PM
 * Copyright (c) 2021 . All rights reserved.
 * Last modified 1/17/21 3:31 PM
 * github: https://github.com/oandrz
 */

package com.dre.loyalty.features.invoice.presentation.list.enumtype

enum class SortOrder(val sort: String) {
    DESC("Terbaru ke Terlama"),
    ASC("Terlama ke Terbaru"),
    UNKNOWN("");

    companion object {
        fun fromValue(selected: String) : SortOrder {
            val types = values()
            return types.find { it.sort.equals(selected, true) } ?: UNKNOWN
        }
    }
}
