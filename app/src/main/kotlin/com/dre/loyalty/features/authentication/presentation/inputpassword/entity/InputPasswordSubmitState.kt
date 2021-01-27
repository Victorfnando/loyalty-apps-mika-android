/*
 * Created by Andreas Oen on 1/20/21 7:30 PM
 * Copyright (c) 2021 . All rights reserved.
 * Last modified 1/20/21 7:30 PM
 * github: https://github.com/oandrz
 */

package com.dre.loyalty.features.authentication.presentation.inputpassword.entity

import androidx.annotation.StringRes

data class InputPasswordSubmitState(
    @StringRes val text: Int,
    val isEnabled: Boolean
)
