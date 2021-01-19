/*
 * Created by Andreas Oen on 12/27/20 10:27 AM
 * Copyright (c) 2020 . All rights reserved.
 * Last modified 12/27/20 10:27 AM
 * github: https://github.com/oandrz
 */

package com.dre.loyalty.features.login.presentation.entity

import androidx.annotation.StringRes

data class LoginPasswordInputState(
    @StringRes var error: Int? = null,
    var isShowingPassword: Boolean = false
)
