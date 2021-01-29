/*
 * Created by Andreas Oen on 12/27/20 3:02 PM
 * Copyright (c) 2020 . All rights reserved.
 * Last modified 12/27/20 2:50 PM
 * github: https://github.com/oandrz
 */

package com.dre.loyalty.core.platform.util.validator.type

import androidx.core.text.isDigitsOnly
import com.dre.loyalty.R
import com.dre.loyalty.core.platform.util.validator.ValidationResult
import javax.inject.Inject

class TotalAmountValidation @Inject constructor() : ValidationStrategy {
    override fun validate(text: String): ValidationResult {
        if (text.isEmpty()) {
            return ValidationResult(false, R.string.validation_failed_empty)
        } else if (!isValidNumber(text)) {
            return ValidationResult(false, R.string.validation_failed_format_number)
        }
        return ValidationResult(true)
    }

    private fun isValidNumber(text: String) = text.isDigitsOnly()
}
