package com.dre.loyalty.core.di.module

import com.dre.loyalty.core.networking.*
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class ServiceModule {
    @Provides
    @Singleton
    fun provideHomeService(retrofit: Retrofit): HomeService {
        return retrofit.create(HomeService::class.java)
    }

    @Provides
    @Singleton
    fun provideFaqService(retrofit: Retrofit): FaqService {
        return retrofit.create(FaqService::class.java)
    }

    @Provides
    @Singleton
    fun provideHospitalService(retrofit: Retrofit): HospitalService {
        return retrofit.create(HospitalService::class.java)
    }

    @Provides
    @Singleton
    fun provideNewsService(retrofit: Retrofit): NewsService {
        return retrofit.create(NewsService::class.java)
    }

    @Provides
    @Singleton
    fun provideAuthenticationService(retrofit: Retrofit): AuthenticationService {
        return retrofit.create(AuthenticationService::class.java)
    }

    @Provides
    @Singleton
    fun provideCashBackListService(retrofit: Retrofit): CashBackService {
        return retrofit.create(CashBackService::class.java)
    }

    @Provides
    @Singleton
    fun provideInvoiceService(retrofit: Retrofit): InvoiceService {
        return retrofit.create(InvoiceService::class.java)
    }

    @Provides
    @Singleton
    fun provideWalletService(retrofit: Retrofit): EWalletService {
        return retrofit.create(EWalletService::class.java)
    }

    @Provides
    @Singleton
    fun provideUserService(retrofit: Retrofit): UserService {
        return retrofit.create(UserService::class.java)
    }
}