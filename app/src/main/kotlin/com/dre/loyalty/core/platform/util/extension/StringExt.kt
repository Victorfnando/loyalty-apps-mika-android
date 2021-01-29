/*
 * Created by Andreas Oen on 1/18/21 6:49 PM
 * Copyright (c) 2021 . All rights reserved.
 * Last modified 1/18/21 6:49 PM
 * github: https://github.com/oandrz
 */

package com.dre.loyalty.core.platform.util.extension

private const val MINUS = "-"

fun String.showIfNotEmpty(): String {
    return if (this.isEmpty()) {
        MINUS
    } else {
        this
    }
}
