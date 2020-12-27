/*
 * Created by Andreas Oen on 12/27/20 3:01 PM
 * Copyright (c) 2020 . All rights reserved.
 * Last modified 12/27/20 3:01 PM
 * github: https://github.com/oandrz
 */

package com.dre.loyalty.core.util.validator.type

enum class ValidationType(val strategy: ValidationStrategy) {
    EMAIL(EmailValidation()),
    NAME(NameValidation()),
    KTP(KtpValidation()),
    PHONE(PhoneValidation())
}
