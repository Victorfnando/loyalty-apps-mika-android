/*
 * Created by Andreas Oen on 1/16/21 7:43 PM
 * Copyright (c) 2021 . All rights reserved.
 * Last modified 1/16/21 7:43 PM
 * github: https://github.com/oandrz
 */

package com.dre.loyalty.core.util.extension

import java.text.NumberFormat
import java.util.*

val LOCALE_ID = Locale("in", "ID", "")
val CURRENCY_FORMATTER: NumberFormat = NumberFormat.getCurrencyInstance(LOCALE_ID)

fun Any.formatToCurrency(): String {
    return "Rp ${CURRENCY_FORMATTER.format(this)}"
}
