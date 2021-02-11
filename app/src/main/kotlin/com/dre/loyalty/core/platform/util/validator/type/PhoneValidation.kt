/*
 * Created by Andreas Oen on 12/27/20 3:03 PM
 * Copyright (c) 2020 . All rights reserved.
 * Last modified 12/27/20 3:03 PM
 * github: https://github.com/oandrz
 */

package com.dre.loyalty.core.platform.util.validator.type

import androidx.core.text.isDigitsOnly
import com.dre.loyalty.R
import com.dre.loyalty.core.platform.util.validator.ValidationResult
import javax.inject.Inject

class PhoneValidation @Inject constructor() : ValidationStrategy {
    override fun validate(text: String): ValidationResult {
        if (text.isEmpty()) {
            return ValidationResult(false, R.string.validation_failed_empty)
        } else if (!isValidPhoneNumber(text)) {
            return ValidationResult(false, R.string.validation_failed_format_phone)
        } else if (text.length < 9) {
            return ValidationResult(false, R.string.validation_failed_format_phone)
        }
        return ValidationResult(true)
    }

    private fun isValidPhoneNumber(text: String) = text.isDigitsOnly()
            && (text.length >= 2 && text.substring(0, 2) == "62" ||
            text.length >= 2 && text.substring(0, 2) == "08")
}
