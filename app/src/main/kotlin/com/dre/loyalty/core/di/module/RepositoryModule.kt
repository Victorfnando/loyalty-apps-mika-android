package com.dre.loyalty.core.di.module

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
}