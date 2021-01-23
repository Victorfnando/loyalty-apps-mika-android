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
package com.dre.loyalty.core.navigation

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.content.Intent.ACTION_DIAL
import android.net.Uri
import android.view.View
import android.widget.ImageView
import androidx.core.app.ActivityOptionsCompat
import androidx.fragment.app.FragmentActivity
import com.dre.loyalty.core.extension.empty
import com.dre.loyalty.features.authenticationselector.presentation.AuthenticationSelectorActivity
import com.dre.loyalty.features.cashback.presentation.CashBackListActivity
import com.dre.loyalty.features.changeprofile.presentation.UpdateProfileActivity
import com.dre.loyalty.features.contactus.presentation.ContactUsActivity
import com.dre.loyalty.features.createpin.presentation.CreatePinActivity
import com.dre.loyalty.features.createpin.presentation.enums.CreatePinType
import com.dre.loyalty.features.ewallet.presentation.screen.EWalletActivity
import com.dre.loyalty.features.faq.presentation.FaqActivity
import com.dre.loyalty.features.home.presentation.HomeActivity
import com.dre.loyalty.features.invoicedetail.presentation.InvoiceDetailActivity
import com.dre.loyalty.features.login.data.Authenticator
import com.dre.loyalty.features.login.presentation.ui.LoginActivity
import com.dre.loyalty.features.movies.MovieDetailsActivity
import com.dre.loyalty.features.movies.MovieView
import com.dre.loyalty.features.movies.MoviesActivity
import com.dre.loyalty.features.news.presentation.detail.NewsDetailActivity
import com.dre.loyalty.features.news.presentation.list.NewsListActivity
import com.dre.loyalty.features.otp.OtpActivity
import com.dre.loyalty.features.passwordinput.presentation.enumtype.InputPasswordType
import com.dre.loyalty.features.passwordinput.presentation.screen.InputPasswordActivity
import com.dre.loyalty.features.photoview.PhotoViewActivity
import com.dre.loyalty.features.pin.presentation.InputPinActivity
import com.dre.loyalty.features.register.presentation.ui.RegisterActivity
import com.dre.loyalty.features.resetpassword.presentation.ResetPinActivity
import com.dre.loyalty.features.splash.presentation.SplashScreenActivity
import com.dre.loyalty.features.updatepassword.presentation.UpdatePasswordActivity
import com.dre.loyalty.features.uploadinvoice.presentation.UploadInvoiceActivity
import com.dre.loyalty.features.userdetailform.presentation.UserDetailFormActivity
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class Navigator @Inject constructor(private val authenticator: Authenticator) {

    fun showMain(context: Context) {
        when (authenticator.userLoggedIn()) {
            true -> showHome(context)
            false -> showAuthSelector(context)
        }
    }

    fun showLogin(context: Context) = context.startActivity(LoginActivity.callingIntent(context))
    fun showRegister(context: Context) = context.startActivity(RegisterActivity.callingIntent(context))

    private fun showAuthSelector(context: Context) =
        context.startActivity(AuthenticationSelectorActivity.callingIntent(context))

    private fun showMovies(context: Context) = context.startActivity(MoviesActivity.callingIntent(context))

    fun showMovieDetails(activity: FragmentActivity, movie: MovieView, navigationExtras: Extras) {
        val intent = MovieDetailsActivity.callingIntent(activity, movie)
        val sharedView = navigationExtras.transitionSharedElement as ImageView
        val activityOptions = ActivityOptionsCompat
                .makeSceneTransitionAnimation(activity, sharedView, sharedView.transitionName)
        activity.startActivity(intent, activityOptions.toBundle())
    }

    private val VIDEO_URL_HTTP = "http://www.youtube.com/watch?v="
    private val VIDEO_URL_HTTPS = "https://www.youtube.com/watch?v="

    fun openVideo(context: Context, videoUrl: String) {
        try {
            context.startActivity(createYoutubeIntent(videoUrl))
        } catch (ex: ActivityNotFoundException) {
            context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(videoUrl)))
        }
    }

    private fun createYoutubeIntent(videoUrl: String): Intent {
        val videoId = when {
            videoUrl.startsWith(VIDEO_URL_HTTP) -> videoUrl.replace(VIDEO_URL_HTTP, String.empty())
            videoUrl.startsWith(VIDEO_URL_HTTPS) -> videoUrl.replace(VIDEO_URL_HTTPS, String.empty())
            else -> videoUrl
        }

        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:$videoId"))
        intent.putExtra("force_fullscreen", true)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)

        return intent
    }

    fun showPin(context: Context) {
        context.startActivity(InputPinActivity.callingIntent(context))
    }

    fun showOtp(context: Context) {
        context.startActivity(OtpActivity.callingIntent(context))
    }

    fun showSplashScreen(context: Context) {
        context.startActivity(SplashScreenActivity.callingIntent(context))
    }

    fun showUserDetailForm(context: Context) {
        context.startActivity(UserDetailFormActivity.callingIntent(context))
    }

    fun showResetPin(context: Context) {
        context.startActivity(ResetPinActivity.callingIntent(context))
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
                .also { it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK }
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

    fun showNewsDetail(context: Context) {
        context.startActivity(
            NewsDetailActivity.callingIntent(context)
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

    fun showChangeProfile(context: Context) {
        context.startActivity(UpdateProfileActivity.callingIntent(context))
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

    fun showInputPasswordScreen(context: Context, passwordType: InputPasswordType) {
        context.startActivity(InputPasswordActivity.callingIntent(context, passwordType))
    }

    fun showWalletScreen(context: Context) {
        context.startActivity(EWalletActivity.callingIntent(context))
    }

    class Extras(val transitionSharedElement: View)
}


