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
package com.dre.loyalty.core.di.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dre.loyalty.features.changeprofile.presentation.UpdateProfileViewModel
import com.dre.loyalty.features.contactus.presentation.ContactUsViewModel
import com.dre.loyalty.features.createpin.presentation.CreatePinViewModel
import com.dre.loyalty.features.home.presentation.HomeViewModel
import com.dre.loyalty.features.hospital.presentation.HospitalListViewModel
import com.dre.loyalty.features.invoice.presentation.screen.InvoiceListPagerViewModel
import com.dre.loyalty.features.invoicedetail.presentation.InvoiceDetailViewModel
import com.dre.loyalty.features.login.presentation.ui.LoginViewModel
import com.dre.loyalty.features.movies.MovieDetailsViewModel
import com.dre.loyalty.features.movies.MoviesViewModel
import com.dre.loyalty.features.pin.presentation.InputPinViewModel
import com.dre.loyalty.features.profile.presentation.ProfileViewModel
import com.dre.loyalty.features.register.presentation.ui.RegisterViewModel
import com.dre.loyalty.features.resetpin.presentation.ResetPinViewModel
import com.dre.loyalty.features.updatepassword.presentation.UpdatePasswordViewModel
import com.dre.loyalty.features.uploadinvoice.presentation.UploadInvoiceViewModel
import com.dre.loyalty.features.userdetailform.presentation.UserDetailFormViewModel
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
    @ViewModelKey(ResetPinViewModel::class)
    abstract fun provideResetPinViewModel(vm: ResetPinViewModel): ViewModel

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
    @ViewModelKey(MoviesViewModel::class)
    abstract fun bindsMoviesViewModel(moviesViewModel: MoviesViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MovieDetailsViewModel::class)
    abstract fun bindsMovieDetailsViewModel(movieDetailsViewModel: MovieDetailsViewModel): ViewModel
}
