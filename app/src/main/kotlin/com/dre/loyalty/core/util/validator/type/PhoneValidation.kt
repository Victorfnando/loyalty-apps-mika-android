/*
 * Created by Andreas Oen on 12/27/20 3:03 PM
 * Copyright (c) 2020 . All rights reserved.
 * Last modified 12/27/20 3:03 PM
 * github: https://github.com/oandrz
 */

package com.dre.loyalty.core.util.validator.type

import com.dre.loyalty.core.util.validator.ValidationResult
import javax.inject.Inject

class PhoneValidation @Inject constructor() : ValidationStrategy {
    override fun validate(text: String): ValidationResult {
        return ValidationResult(false)
    }
}
