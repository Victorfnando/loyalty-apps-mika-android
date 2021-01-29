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

import android.content.Context
import com.dre.loyalty.AndroidApplication
import com.dre.loyalty.BuildConfig
import com.dre.loyalty.core.networking.interceptor.TokenInterceptor
import com.dre.loyalty.core.platform.util.preferences.AuthenticationManager
import com.dre.loyalty.features.movies.MoviesRepository
import com.facebook.stetho.okhttp3.StethoInterceptor
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class ApplicationModule(private val application: AndroidApplication) {

    @Provides
    @Singleton
    fun provideApplicationContext(): Context = application

    @Provides
    @Singleton
    fun provideRetrofit(
        authenticationManager: AuthenticationManager
    ): Retrofit {
        return Retrofit.Builder()
                .baseUrl("https://443c2858-d57a-43ce-8ab9-4372283dfca8.mock.pstmn.io/")
                .client(createClient(authenticationManager))
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }

    private fun createClient(authenticationManager: AuthenticationManager): OkHttpClient {
        val okHttpClientBuilder: OkHttpClient.Builder = OkHttpClient.Builder()
        if (BuildConfig.DEBUG) {
            val loggingInterceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
            okHttpClientBuilder.addInterceptor(loggingInterceptor)
        }
        return okHttpClientBuilder
            .addNetworkInterceptor(StethoInterceptor())
            .connectTimeout(20, TimeUnit.SECONDS)
            .readTimeout(20, TimeUnit.SECONDS)
            .addInterceptor(TokenInterceptor(authenticationManager))
            .authenticator(TokenInterceptor(authenticationManager))
            .build()
    }

    @Provides
    @Singleton
    fun provideMoviesRepository(dataSource: MoviesRepository.Network): MoviesRepository = dataSource
}
