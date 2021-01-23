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
package com.dre.loyalty.core.di

import com.dre.loyalty.AndroidApplication
import com.dre.loyalty.core.di.viewmodel.ViewModelModule
import com.dre.loyalty.core.navigation.RouteActivity
import com.dre.loyalty.features.authenticationselector.presentation.AuthenticationSelectorFragment
import com.dre.loyalty.features.cashback.presentation.screen.CashBackListFragment
import com.dre.loyalty.features.changeprofile.presentation.UpdateProfileFragment
import com.dre.loyalty.features.contactus.presentation.ContactUsFragment
import com.dre.loyalty.features.createpin.presentation.CreatePinFragment
import com.dre.loyalty.features.ewallet.presentation.screen.EWalletFragment
import com.dre.loyalty.features.home.presentation.HomeFragment
import com.dre.loyalty.features.hospital.presentation.HospitalListFragment
import com.dre.loyalty.features.invoice.presentation.screen.InvoiceListPagerFragment
import com.dre.loyalty.features.invoicedetail.presentation.InvoiceDetailFragment
import com.dre.loyalty.features.login.presentation.ui.LoginFragment
import com.dre.loyalty.features.register.presentation.ui.RegisterFragment
import com.dre.loyalty.features.movies.MovieDetailsFragment
import com.dre.loyalty.features.movies.MoviesFragment
import com.dre.loyalty.features.otp.OtpFragment
import com.dre.loyalty.features.passwordinput.presentation.screen.InputPasswordFragment
import com.dre.loyalty.features.pin.presentation.InputPinFragment
import com.dre.loyalty.features.profile.presentation.ProfileFragment
import com.dre.loyalty.features.resetpassword.presentation.ResetPasswordFragment
import com.dre.loyalty.features.splash.presentation.SplashScreenActivity
import com.dre.loyalty.features.updatepassword.presentation.UpdatePasswordFragment
import com.dre.loyalty.features.uploadinvoice.presentation.UploadInvoiceFragment
import com.dre.loyalty.features.userdetailform.presentation.UserDetailFormFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class, ViewModelModule::class])
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

    fun inject(moviesFragment: MoviesFragment)
    fun inject(movieDetailsFragment: MovieDetailsFragment)
}
