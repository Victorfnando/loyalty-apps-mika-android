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
package com.dre.loyalty.core.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dre.loyalty.core.di.viewmodel.ViewModelFactory
import com.dre.loyalty.core.di.viewmodel.ViewModelKey
import com.dre.loyalty.features.cashback.presentation.screen.CashBackListViewModel
import com.dre.loyalty.features.profile.presentation.changeprofile.screen.UpdateProfileViewModel
import com.dre.loyalty.features.contactus.presentation.ContactUsViewModel
import com.dre.loyalty.features.createpin.presentation.CreatePinViewModel
import com.dre.loyalty.features.ewallet.presentation.screen.EWalletViewModel
import com.dre.loyalty.features.faq.presentation.screen.FaqViewModel
import com.dre.loyalty.features.home.presentation.screen.HomeViewModel
import com.dre.loyalty.features.hospital.presentation.screen.HospitalListViewModel
import com.dre.loyalty.features.invoice.presentation.list.screen.InvoiceListPagerViewModel
import com.dre.loyalty.features.invoice.presentation.detail.screen.InvoiceDetailViewModel
import com.dre.loyalty.features.authentication.presentation.login.screen.LoginViewModel
import com.dre.loyalty.features.movies.MovieDetailsViewModel
import com.dre.loyalty.features.movies.MoviesViewModel
import com.dre.loyalty.features.news.presentation.detail.NewsDetailViewModel
import com.dre.loyalty.features.news.presentation.list.NewsListViewModel
import com.dre.loyalty.features.authentication.presentation.inputpassword.screen.create.InputPasswordCreateViewModel
import com.dre.loyalty.features.authentication.presentation.inputpassword.screen.reset.InputPasswordResetViewModel
import com.dre.loyalty.features.pin.presentation.InputPinViewModel
import com.dre.loyalty.features.profile.presentation.profile.screen.ProfileViewModel
import com.dre.loyalty.features.authentication.presentation.register.screen.RegisterViewModel
import com.dre.loyalty.features.authentication.presentation.resetpassword.screen.ResetPasswordViewModel
import com.dre.loyalty.features.authentication.presentation.updatepassword.screen.UpdatePasswordViewModel
import com.dre.loyalty.features.invoice.presentation.upload.screen.UploadInvoiceViewModel
import com.dre.loyalty.features.authentication.presentation.inputuserdetail.screen.UserDetailFormViewModel
import com.dre.loyalty.features.authentication.presentation.otp.screen.OtpViewModel
import com.dre.loyalty.features.authentication.presentation.otp.screen.create.OtpRegisterViewModel
import com.dre.loyalty.features.authentication.presentation.otp.screen.forgot.OtpForgotPasswordViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {
    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel::class)
    abstract fun provideLoginViewModel(vm: LoginViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(RegisterViewModel::class)
    abstract fun provideRegisterViewModel(vm: RegisterViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(InputPinViewModel::class)
    abstract fun providePinViewModel(vm: InputPinViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(UserDetailFormViewModel::class)
    abstract fun provideUserDetailFormViewModel(vm: UserDetailFormViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CreatePinViewModel::class)
    abstract fun provideCreatePinViewModel(vm: CreatePinViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ResetPasswordViewModel::class)
    abstract fun provideResetPinViewModel(vm: ResetPasswordViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    abstract fun provideHomeViewModel(vm: HomeViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(UploadInvoiceViewModel::class)
    abstract fun provideUploadInvoiceVIewModel(vm: UploadInvoiceViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(HospitalListViewModel::class)
    abstract fun provideHospitalViewModel(vm: HospitalListViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ProfileViewModel::class)
    abstract fun provideProfileViewModel(vm: ProfileViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(UpdateProfileViewModel::class)
    abstract fun provideUpdateProfileViewModel(vm: UpdateProfileViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(UpdatePasswordViewModel::class)
    abstract fun provideUpdatePasswordViewModel(vm: UpdatePasswordViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ContactUsViewModel::class)
    abstract fun provideContactUsViewModel(vm: ContactUsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(InvoiceListPagerViewModel::class)
    abstract fun provideInvoiceListPagerViewModel(vm: InvoiceListPagerViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(InvoiceDetailViewModel::class)
    abstract fun provideInvoiceDetailViewModel(vm: InvoiceDetailViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(InputPasswordCreateViewModel::class)
    abstract fun provideInputPasswordCreateViewModel(vm: InputPasswordCreateViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(InputPasswordResetViewModel::class)
    abstract fun provideInputPasswordResetViewModel(vm: InputPasswordResetViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(EWalletViewModel::class)
    abstract fun provideEWalletViewModel(vm: EWalletViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CashBackListViewModel::class)
    abstract fun provideCashBackListViewModel(vm: CashBackListViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(NewsListViewModel::class)
    abstract fun provideNewsListViewModel(vm: NewsListViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(FaqViewModel::class)
    abstract fun provideFaqViewModel(vm: FaqViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(NewsDetailViewModel::class)
    abstract fun provideNewsDetailViewModel(vm: NewsDetailViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(OtpRegisterViewModel::class)
    abstract fun provideOtpRegisterViewModel(vm: OtpRegisterViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(OtpForgotPasswordViewModel::class)
    abstract fun provideOtpForgotPasswordViewModel(vm: OtpForgotPasswordViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MoviesViewModel::class)
    abstract fun bindsMoviesViewModel(moviesViewModel: MoviesViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MovieDetailsViewModel::class)
    abstract fun bindsMovieDetailsViewModel(movieDetailsViewModel: MovieDetailsViewModel): ViewModel
}
