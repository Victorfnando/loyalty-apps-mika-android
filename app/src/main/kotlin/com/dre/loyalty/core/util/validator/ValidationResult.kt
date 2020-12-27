/*
 * Created by Andreas Oen on 12/27/20 2:48 PM
 * Copyright (c) 2020 . All rights reserved.
 * Last modified 12/27/20 2:48 PM
 * github: https://github.com/oandrz
 */

package com.dre.loyalty.core.util.validator

import androidx.annotation.StringRes

data class ValidationResult(
    val isPass: Boolean,
    @StringRes val errorMessage: Int? = null,
)
