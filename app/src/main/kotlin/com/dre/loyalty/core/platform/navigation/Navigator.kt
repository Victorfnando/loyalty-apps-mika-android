/**
 * Copyright (C) 2020 Fernando Cejas Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.dre.loyalty.core.platform.navigation

import android.content.Context
import android.content.Intent
import android.content.Intent.ACTION_DIAL
import android.content.Intent.ACTION_VIEW
import android.net.Uri
import android.provider.Settings
import android.view.View
import com.dre.loyalty.core.model.User
import com.dre.loyalty.features.authentication.data.repository.Authenticator
import com.dre.loyalty.features.authentication.presentation.inputpassword.enumtype.InputPasswordType
import com.dre.loyalty.features.authentication.presentation.inputpassword.screen.InputPasswordActivity
import com.dre.loyalty.features.authentication.presentation.inputuserdetail.screen.UserDetailFormActivity
import com.dre.loyalty.features.authentication.presentation.login.screen.LoginActivity
import com.dre.loyalty.features.authentication.presentation.otp.enumType.OtpType
import com.dre.loyalty.features.authentication.presentation.otp.screen.OtpActivity
import com.dre.loyalty.features.authentication.presentation.register.screen.RegisterActivity
import com.dre.loyalty.features.authentication.presentation.resetpassword.screen.ResetPasswordActivity
import com.dre.loyalty.features.authentication.presentation.updatepassword.screen.UpdatePasswordActivity
import com.dre.loyalty.features.authenticationselector.presentation.AuthenticationSelectorActivity
import com.dre.loyalty.features.cashback.presentation.screen.CashBackListActivity
import com.dre.loyalty.features.profile.presentation.contactus.ContactUsActivity
import com.dre.loyalty.features.createpin.presentation.CreatePinActivity
import com.dre.loyalty.features.createpin.presentation.enums.CreatePinType
import com.dre.loyalty.features.ewallet.presentation.screen.EWalletActivity
import com.dre.loyalty.features.faq.presentation.screen.FaqActivity
import com.dre.loyalty.features.home.presentation.screen.HomeActivity
import com.dre.loyalty.features.invoice.presentation.detail.screen.InvoiceDetailActivity
import com.dre.loyalty.features.invoice.presentation.entity.UploadInvoiceState
import com.dre.loyalty.features.invoice.presentation.upload.screen.UploadInvoiceActivity
import com.dre.loyalty.features.news.presentation.detail.NewsDetailActivity
import com.dre.loyalty.features.news.presentation.list.NewsListActivity
import com.dre.loyalty.features.photoview.PhotoViewActivity
import com.dre.loyalty.features.pin.presentation.InputPinActivity
import com.dre.loyalty.features.profile.presentation.changeprofile.screen.UpdateProfileActivity
import com.dre.loyalty.features.splash.presentation.SplashScreenActivity
import com.dre.loyalty.features.webview.WebViewActivity
import javax.inject.Inject
import javax.inject.Singleton


@Singleton class Navigator @Inject constructor(private val authenticator: Authenticator) {

    fun showMain(context: Context) {
        when (authenticator.userLoggedIn()) {
            true -> showHome(context)
            false -> showAuthSelector(context)
        }
    }

    fun showLogin(context: Context, clearTask: Boolean = false) =
        context.startActivity(
            LoginActivity.callingIntent(context).also {
                if (clearTask) {
                    it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                }
            }
        )
    fun showRegister(context: Context) = context.startActivity(
        RegisterActivity.callingIntent(
            context
        )
    )

    fun showAuthSelector(context: Context) =
        context.startActivity(AuthenticationSelectorActivity.callingIntent(context).also {
            it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        })

    fun showPin(context: Context) {
        context.startActivity(InputPinActivity.callingIntent(context))
    }

    fun showOtp(context: Context, email: String, type: OtpType) {
        context.startActivity(OtpActivity.callingIntent(context, email, type))
    }

    fun showSplashScreen(context: Context) {
        context.startActivity(SplashScreenActivity.callingIntent(context))
    }

    fun showUserDetailForm(context: Context, email: String) {
        context.startActivity(UserDetailFormActivity.callingIntent(context, email))
    }

    fun showResetPasswordScreen(context: Context) {
        context.startActivity(ResetPasswordActivity.callingIntent(context))
    }

    fun showCreatePin(context: Context) {
        context.startActivity(CreatePinActivity.callingIntent(context, CreatePinType.CREATE))
    }

    fun showCreatePinFromScratch(context: Context) {
        context.startActivity(CreatePinActivity.callingIntent(context, CreatePinType.RESET))
    }

    fun showHome(context: Context) {
        context.startActivity(
            HomeActivity
                .callingActivity(context)
                .also {
                    it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                }
        )
    }

    fun showCashBackList(context: Context) {
        context.startActivity(
            CashBackListActivity.callingIntent(context)
        )
    }

    fun showNewsList(context: Context) {
        context.startActivity(
            NewsListActivity.callingIntent(context)
        )
    }

    fun showNewsDetail(context: Context, id: String) {
        context.startActivity(
            NewsDetailActivity.callingIntent(context, id)
        )
    }

    fun showUploadInvoice(context: Context, imageUri: Uri) {
        context.startActivity(
            UploadInvoiceActivity.callingIntent(context, imageUri.toString())
        )
    }

    fun showDialPage(context: Context, phoneNumber: String) {
        val intent = Intent(ACTION_DIAL).also {
            it.data = Uri.parse("tel:$phoneNumber")
        }
        context.startActivity(intent)
    }

    fun showChangeProfile(context: Context, user: User) {
        context.startActivity(UpdateProfileActivity.callingIntent(context, user))
    }

    fun showUpdatePasswordActivity(context: Context) {
        context.startActivity(UpdatePasswordActivity.callingIntent(context))
    }

    fun showFaqActivity(context: Context) {
        context.startActivity(FaqActivity.callingIntent(context))
    }

    fun showContactUs(context: Context) {
        context.startActivity(ContactUsActivity.callingIntent(context))
    }

    fun showInvoiceDetail(context: Context, id: String) {
        context.startActivity(InvoiceDetailActivity.callingIntent(context, id))
    }

    fun showPhotoView(context: Context, url: String) {
        context.startActivity(PhotoViewActivity.callingIntent(context, url))
    }

    fun showInputPasswordScreen(context: Context, user: User, type: InputPasswordType) {
        context.startActivity(InputPasswordActivity.callingIntent(context, user, type))
    }

    fun showWalletScreen(context: Context, state: UploadInvoiceState) {
        context.startActivity(EWalletActivity.callingIntent(context, state))
    }

    fun showSetting(context: Context) {
        context.startActivity(Intent(Settings.ACTION_SETTINGS))
    }

    fun showMap(context: Context, latLong: Triple<Double, Double, String>) {
        val latitude = latLong.first
        val longitude = latLong.second
        val label = latLong.third
        val uriBegin = "geo:$latitude,$longitude"
        val query = "$latitude,$longitude($label)"
        val encodedQuery = Uri.encode(query)
        val uriString = "$uriBegin?q=$encodedQuery&z=16"
        context.startActivity(
            Intent(ACTION_VIEW, Uri.parse(uriString))
        )
    }

    fun callingWebView(context: Context, url: String) {
        context.startActivity(WebViewActivity.callingIntent(context, url))
    }

    class Extras(val transitionSharedElement: View)
}


