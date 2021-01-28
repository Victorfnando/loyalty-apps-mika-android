package com.dre.loyalty.core.di.module

import com.dre.loyalty.features.authentication.data.repository.datasource.cloud.AuthenticationCloudDataSource
import com.dre.loyalty.features.authentication.data.repository.datasource.cloud.AuthenticationCloudDataSourceContract
import com.dre.loyalty.features.authentication.data.repository.datasource.local.AuthenticationLocalDataSource
import com.dre.loyalty.features.authentication.data.repository.datasource.local.AuthenticationLocalDataSourceContract
import com.dre.loyalty.features.faq.data.repository.datasource.FaqCloudDataSource
import com.dre.loyalty.features.faq.data.repository.datasource.FaqCloudDataSourceContract
import com.dre.loyalty.features.home.data.repository.datasource.cloud.HomeCloudDataSource
import com.dre.loyalty.features.home.data.repository.datasource.cloud.HomeCloudDataSourceContract
import com.dre.loyalty.features.home.data.repository.datasource.local.HomeLocalDataSource
import com.dre.loyalty.features.home.data.repository.datasource.local.HomeLocalDataSourceContract
import com.dre.loyalty.features.hospital.data.repository.datasource.HospitalListCloudDataSource
import com.dre.loyalty.features.hospital.data.repository.datasource.HospitalListCloudDataSourceContract
import com.dre.loyalty.features.invoice.data.repository.datasource.InvoiceCloudDataSource
import com.dre.loyalty.features.invoice.data.repository.datasource.InvoiceCloudDataSourceContract
import com.dre.loyalty.features.news.data.repository.datasource.NewsCloudDataSource
import com.dre.loyalty.features.news.data.repository.datasource.NewsCloudDataSourceContract
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
    abstract fun provideFaqCloudDataSource(dataSource: FaqCloudDataSource): FaqCloudDataSourceContract

    @Binds
    @Singleton
    abstract fun provideHospitalListCloudDataSource(dataSource: HospitalListCloudDataSource): HospitalListCloudDataSourceContract

    @Binds
    @Singleton
    abstract fun provideNewsCloudDataSource(dataSource: NewsCloudDataSource): NewsCloudDataSourceContract

    @Binds
    @Singleton
    abstract fun provideInvoiceCloudDataSource(dataSource: InvoiceCloudDataSource): InvoiceCloudDataSourceContract

    @Binds
    @Singleton
    abstract fun provideAuthCloudDataSource(dataSource: AuthenticationCloudDataSource): AuthenticationCloudDataSourceContract

    @Binds
    @Singleton
    abstract fun provideAuthLocalDataSource(dataSource: AuthenticationLocalDataSource): AuthenticationLocalDataSourceContract
}