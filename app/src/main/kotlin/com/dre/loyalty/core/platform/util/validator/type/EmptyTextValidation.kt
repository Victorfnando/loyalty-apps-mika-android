/*
 * Created by Andreas Oen on 12/27/20 3:03 PM
 * Copyright (c) 2020 . All rights reserved.
 * Last modified 12/27/20 3:03 PM
 * github: https://github.com/oandrz
 */

package com.dre.loyalty.core.platform.util.validator.type

import com.dre.loyalty.R
import com.dre.loyalty.core.platform.util.validator.ValidationResult
import javax.inject.Inject

class EmptyTextValidation @Inject constructor() : ValidationStrategy {
    override fun validate(text: String): ValidationResult {
        if (text.isEmpty()) {
            return ValidationResult(false, R.string.validation_failed_empty)
        }
        return ValidationResult(true)
    }
}
