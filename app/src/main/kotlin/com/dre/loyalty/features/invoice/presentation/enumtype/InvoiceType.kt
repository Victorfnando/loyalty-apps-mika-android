/*
 * Created by Andreas Oen on 1/16/21 4:19 PM
 * Copyright (c) 2021 . All rights reserved.
 * Last modified 1/16/21 4:19 PM
 * github: https://github.com/oandrz
 */

package com.dre.loyalty.features.invoice.presentation.enumtype

enum class InvoiceType(val position: Int) {
    ALL(0),
    PROCESS(1),
    ACCEPTED(2),
    DENIED(3),
    UNKNOWN(-1);

    companion object {
        fun fromValue(position: Int) : InvoiceType {
            val types = values()
            return types.find { it.position == position } ?: UNKNOWN
        }
    }
}
