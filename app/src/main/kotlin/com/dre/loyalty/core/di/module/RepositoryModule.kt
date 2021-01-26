package com.dre.loyalty.core.di.module

import com.dre.loyalty.features.faq.data.repository.FaqRepository
import com.dre.loyalty.features.faq.domain.FaqRepositoryContract
import com.dre.loyalty.features.home.data.repository.HomeRepository
import com.dre.loyalty.features.home.domain.HomeRepositoryContract
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
}