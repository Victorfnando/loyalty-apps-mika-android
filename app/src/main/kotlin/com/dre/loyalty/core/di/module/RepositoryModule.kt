package com.dre.loyalty.core.di.module

import com.dre.loyalty.features.authentication.data.repository.AuthenticationRepository
import com.dre.loyalty.features.authentication.domain.AuthenticationRepositoryContract
import com.dre.loyalty.features.cashback.data.repository.CashBackListRepository
import com.dre.loyalty.features.cashback.domain.CashBackListRepositoryContract
import com.dre.loyalty.features.ewallet.data.repository.EWalletRepository
import com.dre.loyalty.features.ewallet.domain.EWalletRepositoryContract
import com.dre.loyalty.features.faq.data.repository.FaqRepository
import com.dre.loyalty.features.faq.domain.FaqRepositoryContract
import com.dre.loyalty.features.home.data.repository.HomeRepository
import com.dre.loyalty.features.home.domain.HomeRepositoryContract
import com.dre.loyalty.features.hospital.data.repository.HospitalListRepository
import com.dre.loyalty.features.hospital.domain.HospitalListRepositoryContract
import com.dre.loyalty.features.invoice.data.repository.InvoiceRepository
import com.dre.loyalty.features.invoice.domain.InvoiceRepositoryContract
import com.dre.loyalty.features.news.data.repository.NewsRepository
import com.dre.loyalty.features.news.domain.NewsRepositoryContract
import com.dre.loyalty.features.profile.data.repository.UserRepository
import com.dre.loyalty.features.profile.domain.UserRepositoryContract
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun provideHomeRepository(repository: HomeRepository): HomeRepositoryContract

    @Binds
    @Singleton
    abstract fun provideFaqRepository(repository: FaqRepository): FaqRepositoryContract

    @Binds
    @Singleton
    abstract fun provideHospitalListRepository(repository: HospitalListRepository): HospitalListRepositoryContract

    @Binds
    @Singleton
    abstract fun provideNewsRepository(repository: NewsRepository): NewsRepositoryContract

    @Binds
    @Singleton
    abstract fun provideInvoiceRepository(repository: InvoiceRepository): InvoiceRepositoryContract

    @Binds
    @Singleton
    abstract fun provideAuthRepository(repository: AuthenticationRepository): AuthenticationRepositoryContract

    @Binds
    @Singleton
    abstract fun provideCashBackListRepository(repository: CashBackListRepository): CashBackListRepositoryContract

    @Binds
    @Singleton
    abstract fun provideEWalletRepository(repository: EWalletRepository): EWalletRepositoryContract

    @Binds
    @Singleton
    abstract fun provideUserRepository(repository: UserRepository): UserRepositoryContract
}