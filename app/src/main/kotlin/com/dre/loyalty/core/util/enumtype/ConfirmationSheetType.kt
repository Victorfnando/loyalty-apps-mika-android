package com.dre.loyalty.core.util.enumtype

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.dre.loyalty.R

enum class ConfirmationSheetType(
    @DrawableRes val image: Int,
    @StringRes val title: Int,
    @StringRes val description: Int,
    @StringRes val primaryButtonText: Int,
    @StringRes val secondaryButtonText: Int? = null
) {
    USER_DETAIL_FORM_CONFIRM(
        R.drawable.ic_img_confirmation_detail,
        R.string.confirmation_screen_title,
        R.string.confirmation_screen_desc,
        R.string.confirmation_screen_btn_confirm,
        R.string.confirmation_screen_btn_change
    )
}