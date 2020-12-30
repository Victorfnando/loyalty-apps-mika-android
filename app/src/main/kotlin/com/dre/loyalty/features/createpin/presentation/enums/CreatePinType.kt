/*
 * Created by Andreas Oen on 12/29/20 7:48 PM
 * Copyright (c) 2020 . All rights reserved.
 * Last modified 12/29/20 7:48 PM
 * github: https://github.com/oandrz
 */

package com.dre.loyalty.features.createpin.presentation.enums

import androidx.annotation.StringRes
import com.dre.loyalty.R

enum class CreatePinType(
    @StringRes val screenTitle: Int,
    @StringRes val screenDesc: Int,
    @StringRes val btnText: Int
) {
    CREATE(R.string.createpin_screen_title_create, R.string.createpin_screen_desc_create, R.string.createpin_screen_btn_create),
    RESET(R.string.createpin_screen_title_reset, R.string.createpin_screen_desc_create, R.string.createpin_screen_btn_create),
    CONFIRM(R.string.createpin_screen_title_confirm, R.string.createpin_screen_desc_confirm, R.string.createpin_screen_btn_confirm)
}
