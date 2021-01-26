package com.dre.loyalty.core.di.module

import com.dre.loyalty.features.faq.data.repository.datasource.FaqCloudDataSource
import com.dre.loyalty.features.faq.data.repository.datasource.FaqCloudDataSourceContract
import com.dre.loyalty.features.home.data.repository.datasource.cloud.HomeCloudDataSource
import com.dre.loyalty.features.home.data.repository.datasource.cloud.HomeCloudDataSourceContract
import com.dre.loyalty.features.home.data.repository.datasource.local.HomeLocalDataSource
import com.dre.loyalty.features.home.data.repository.datasource.local.HomeLocalDataSourceContract
import com.dre.loyalty.features.hospital.data.repository.datasource.HospitalListCloudDataSource
import com.dre.loyalty.features.hospital.data.repository.datasource.HospitalListCloudDataSourceContract
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class DataSourceModule {

    @Binds
    @Singleton
    abstract fun provideHomeCloudDataSource(dataSource: HomeCloudDataSource): HomeCloudDataSourceContract

    @Binds
    @Singleton
    abstract fun provideHomeLocalDataSource(dataSource: HomeLocalDataSource): HomeLocalDataSourceContract

    @Binds
    @Singleton
    abstract fun provideFaqDataSource(dataSource: FaqCloudDataSource): FaqCloudDataSourceContract

    @Binds
    @Singleton
    abstract fun provideHospitalListDataSource(dataSource: HospitalListCloudDataSource): HospitalListCloudDataSourceContract
}