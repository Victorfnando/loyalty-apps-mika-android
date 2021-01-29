/*
 * Created by Andreas Oen on 12/27/20 3:02 PM
 * Copyright (c) 2020 . All rights reserved.
 * Last modified 12/27/20 2:50 PM
 * github: https://github.com/oandrz
 */

package com.dre.loyalty.core.platform.util.validator.type

import android.util.Patterns
import com.dre.loyalty.R
import com.dre.loyalty.core.platform.util.validator.ValidationResult
import javax.inject.Inject

class EmailValidation @Inject constructor() : ValidationStrategy {
    override fun validate(text: String): ValidationResult {
        if (text.isEmpty()) {
            return ValidationResult(false, R.string.validation_failed_empty)
        } else if (!isValidEmail(text)) {
            return ValidationResult(false, R.string.validation_failed_format_email)
        }
        return ValidationResult(true)
    }

    private fun isValidEmail(text: String) = Patterns.EMAIL_ADDRESS.matcher(text).matches()
}
