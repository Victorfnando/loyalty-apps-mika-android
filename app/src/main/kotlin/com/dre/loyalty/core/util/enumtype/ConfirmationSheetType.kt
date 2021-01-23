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
    ),
    INPUT_PASSWORD_CREATE_SUCCESS_SHEET(
        R.drawable.ic_img_success_register,
        R.string.inputPassword_sheet_create_label_title,
        R.string.inputPassword_sheet_create_label_description,
        R.string.inputPassword_sheet_create_button_login
    ),
    INPUT_PASSWORD_RESET_SUCCESS_SHEET(
        R.drawable.ic_img_reset_password,
        R.string.inputPassword_sheet_create_label_title,
        R.string.inputPassword_sheet_create_label_description,
        R.string.inputPassword_sheet_create_button_login
    ),
    RESET_PASSWORD_LINK_SHEET(
        R.drawable.ic_img_reset_password,
        R.string.resetPassword_sheet_label_title,
        R.string.resetPassword_sheet_label_description,
        R.string.resetPassword_sheet_button_ok
    ),
    UPLOAD_INVOICE_SUCCESS_SHEET(
        R.drawable.ic_img_upload_invoice,
        R.string.ewallet_sheet_success_label_title,
        R.string.ewallet_sheet_success_label_description,
        R.string.ewallet_sheet_success_button_ok
    ),
    CHANGE_PROFILE_SUCCESS_SHEET(
        R.drawable.ic_img_change_profile,
        R.string.updateProfile_sheet_label_title,
        R.string.updateProfile_sheet_label_description,
        R.string.updateProfile_sheet_button_ok
    ),
    CONTACT_US_SUCCESS_SHEET(
        R.drawable.ic_img_change_profile,
        R.string.contactUs_sheet_label_title,
        R.string.contactUs_sheet_label_description,
        R.string.contactUs_sheet_button_ok
    ),
    UPDATE_PASSWORD_SUCCESS_SHEET(
        R.drawable.ic_img_reset_password,
        R.string.updatePassword_sheet_label_title,
        R.string.updatePassword_sheet_label_description,
        R.string.updatePassword_sheet_button_ok
    ),
    LOGOUT_CONFIRMATION_SHEET(
        R.drawable.ic_img_logout,
        R.string.profile_sheet_logout_label_title,
        R.string.profile_sheet_logout_label_description,
        R.string.profile_sheet_logout_button_exit,
        R.string.profile_sheet_logout_button_no
    )
}