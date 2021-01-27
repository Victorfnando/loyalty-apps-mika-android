/*
 * Created by Andreas Oen on 1/20/21 6:30 PM
 * Copyright (c) 2021 . All rights reserved.
 * Last modified 1/20/21 6:30 PM
 * github: https://github.com/oandrz
 */

package com.dre.loyalty.features.authentication.presentation.inputpassword.enumtype

import com.dre.loyalty.features.authentication.presentation.inputpassword.screen.InputPasswordViewModel
import com.dre.loyalty.features.authentication.presentation.inputpassword.screen.create.InputPasswordCreateViewModel
import com.dre.loyalty.features.authentication.presentation.inputpassword.screen.reset.InputPasswordResetViewModel

enum class InputPasswordType(val reference: Class<out InputPasswordViewModel>) {
    RESET(InputPasswordResetViewModel::class.java),
    CREATE(InputPasswordCreateViewModel::class.java),
    UNKNOWN(InputPasswordCreateViewModel::class.java)
}
