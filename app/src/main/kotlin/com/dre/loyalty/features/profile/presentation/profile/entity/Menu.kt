/*
 * Created by Andreas Oen on 1/10/21 4:58 PM
 * Copyright (c) 2021 . All rights reserved.
 * Last modified 1/10/21 4:58 PM
 * github: https://github.com/oandrz
 */

package com.dre.loyalty.features.profile.presentation.profile.entity

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.dre.loyalty.R

enum class Menu(
    @DrawableRes val menuImage: Int,
    @StringRes val text: Int
) {
    CHANGE_PROFILE(R.drawable.ic_person, R.string.profile_menu_person),
    CHANGE_PASSWORD(R.drawable.ic_lock, R.string.profile_menu_lock),
    FAQ(R.drawable.ic_question, R.string.profile_menu_question),
    CONTACT(R.drawable.ic_contact, R.string.profile_menu_contact),
    TNC(R.drawable.ic_tnc, R.string.profile_menu_tnc)
}
