/*
 * Created by Andreas Oen on 1/12/21 9:20 PM
 * Copyright (c) 2021 . All rights reserved.
 * Last modified 1/12/21 9:20 PM
 * github: https://github.com/oandrz
 */

package com.dre.loyalty.core.platform.util.validator.type

import com.dre.loyalty.R
import com.dre.loyalty.core.platform.util.validator.ValidationResult
import javax.inject.Inject

class PasswordValidation @Inject constructor() : ValidationStrategy {
    override fun validate(text: String): ValidationResult {
        if (text.isEmpty()) {
            return ValidationResult(false, R.string.validation_failed_empty)
        }
        return ValidationResult(true)
    }
}
