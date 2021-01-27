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
package com.dre.loyalty.core.di.component

import com.dre.loyalty.AndroidApplication
import com.dre.loyalty.core.di.module.*
import com.dre.loyalty.core.navigation.RouteActivity
import com.dre.loyalty.features.authenticationselector.presentation.AuthenticationSelectorFragment
import com.dre.loyalty.features.cashback.presentation.screen.CashBackListFragment
import com.dre.loyalty.features.profile.presentation.changeprofile.screen.UpdateProfileFragment
import com.dre.loyalty.features.contactus.presentation.ContactUsFragment
import com.dre.loyalty.features.createpin.presentation.CreatePinFragment
import com.dre.loyalty.features.ewallet.presentation.screen.EWalletFragment
import com.dre.loyalty.features.faq.presentation.screen.FaqFragment
import com.dre.loyalty.features.home.presentation.screen.HomeFragment
import com.dre.loyalty.features.hospital.presentation.screen.HospitalListFragment
import com.dre.loyalty.features.invoice.presentation.list.screen.InvoiceListPagerFragment
import com.dre.loyalty.features.invoice.presentation.detail.screen.InvoiceDetailFragment
import com.dre.loyalty.features.authentication.presentation.login.presentation.ui.LoginFragment
import com.dre.loyalty.features.authentication.presentation.register.screen.RegisterFragment
import com.dre.loyalty.features.movies.MovieDetailsFragment
import com.dre.loyalty.features.movies.MoviesFragment
import com.dre.loyalty.features.news.presentation.detail.NewsDetailFragment
import com.dre.loyalty.features.news.presentation.list.NewsListFragment
import com.dre.loyalty.features.authentication.presentation.otp.screen.OtpFragment
import com.dre.loyalty.features.authentication.presentation.inputpassword.screen.InputPasswordFragment
import com.dre.loyalty.features.pin.presentation.InputPinFragment
import com.dre.loyalty.features.profile.presentation.profile.screen.ProfileFragment
import com.dre.loyalty.features.authentication.presentation.resetpassword.screen.ResetPasswordFragment
import com.dre.loyalty.features.splash.presentation.SplashScreenActivity
import com.dre.loyalty.features.authentication.presentation.updatepassword.screen.UpdatePasswordFragment
import com.dre.loyalty.features.invoice.presentation.upload.screen.UploadInvoiceFragment
import com.dre.loyalty.features.authentication.presentation.inputuserdetail.screen.UserDetailFormFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [
    ApplicationModule::class,
    ViewModelModule::class,
    ServiceModule::class,
    RepositoryModule::class,
    DataSourceModule::class
])
interface ApplicationComponent {
    fun inject(application: AndroidApplication)
    fun inject(routeActivity: RouteActivity)
    fun inject(activity: SplashScreenActivity)

    fun inject(fragment: RegisterFragment)
    fun inject(fragment: LoginFragment)
    fun inject(fragmentInput: InputPinFragment)
    fun inject(fragment: AuthenticationSelectorFragment)
    fun inject(fragment: OtpFragment)
    fun inject(fragment: UserDetailFormFragment)
    fun inject(fragment: ResetPasswordFragment)
    fun inject(fragment: CreatePinFragment)
    fun inject(fragment: HomeFragment)
    fun inject(fragment: UploadInvoiceFragment)
    fun inject(fragment: HospitalListFragment)
    fun inject(fragment: ProfileFragment)
    fun inject(fragment: UpdateProfileFragment)
    fun inject(fragment: UpdatePasswordFragment)
    fun inject(fragment: ContactUsFragment)
    fun inject(fragment: InvoiceListPagerFragment)
    fun inject(fragment: InvoiceDetailFragment)
    fun inject(fragment: InputPasswordFragment)
    fun inject(fragment: EWalletFragment)
    fun inject(fragment: CashBackListFragment)
    fun inject(fragment: NewsListFragment)
    fun inject(fragment: FaqFragment)
    fun inject(fragment: NewsDetailFragment)

    fun inject(moviesFragment: MoviesFragment)
    fun inject(movieDetailsFragment: MovieDetailsFragment)
}
