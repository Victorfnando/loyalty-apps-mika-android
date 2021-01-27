/*
 * Created by Andreas Oen on 1/12/21 9:03 PM
 * Copyright (c) 2021 . All rights reserved.
 * Last modified 1/12/21 9:03 PM
 * github: https://github.com/oandrz
 */

package com.dre.loyalty.features.authentication.presentation.updatepassword.entity

import androidx.annotation.StringRes

data class PasswordInputState(
    val isShowPassword: Boolean,
    @StringRes val error: Int? = null
)
