/*
 * Created by Andreas Oen on 12/27/20 3:02 PM
 * Copyright (c) 2020 . All rights reserved.
 * Last modified 12/27/20 2:50 PM
 * github: https://github.com/oandrz
 */

package com.dre.loyalty.core.platform.util.validator.type

import com.dre.loyalty.core.platform.util.validator.ValidationResult

interface ValidationStrategy {
    fun validate(text: String): ValidationResult
}
