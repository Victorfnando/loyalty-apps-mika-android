package com.dre.loyalty.core.di.module

import com.dre.loyalty.core.service.FaqService
import com.dre.loyalty.core.service.HomeService
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
}