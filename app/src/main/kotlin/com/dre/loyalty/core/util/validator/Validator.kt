/*
 * Created by Andreas Oen on 12/27/20 2:48 PM
 * Copyright (c) 2020 . All rights reserved.
 * Last modified 12/27/20 2:45 PM
 * github: https://github.com/oandrz
 */

package com.dre.loyalty.core.util.validator

import com.dre.loyalty.core.util.validator.type.ValidationStrategy
import javax.inject.Inject

class Validator @Inject constructor(
    var validationStrategy: ValidationStrategy
) {
    fun validate(text: String) {
        validationStrategy.validate(text)
    }
}
