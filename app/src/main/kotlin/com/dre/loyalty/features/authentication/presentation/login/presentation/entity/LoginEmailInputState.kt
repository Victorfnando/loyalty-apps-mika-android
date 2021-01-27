/*
 * Created by Andreas Oen on 12/27/20 10:27 AM
 * Copyright (c) 2020 . All rights reserved.
 * Last modified 12/27/20 10:27 AM
 * github: https://github.com/oandrz
 */

package com.dre.loyalty.features.authentication.presentation.login.presentation.entity

import androidx.annotation.StringRes

data class LoginEmailInputState(
    @StringRes var error: Int? = null
)
