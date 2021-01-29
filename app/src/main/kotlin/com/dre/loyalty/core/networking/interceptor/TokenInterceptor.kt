/*
 *
 * Created by Andreas on 1/29/21 7:04 PM
 * Copyright (c) 2021 . All rights reserved.
 * Last modified 1/29/21 7:04 PM
 * Github Link: https://github.com/oandrz
 * Email: oentoro.andreas@gmail.com
 *
 */

package com.dre.loyalty.core.networking.interceptor

import com.dre.loyalty.core.platform.util.preferences.AuthenticationManager
import okhttp3.*

class TokenInterceptor(private val authorizationManager: AuthenticationManager) : Interceptor, Authenticator {
    override fun intercept(chain: Interceptor.Chain): Response {
        val requestBuilder = chain.request().newBuilder()
            .addHeader("Content-Type", "application/json")
            .addHeader("Accept", "application/json")
        val token = authorizationManager.getToken()
        if (token != null) {
            requestBuilder.addHeader("Authorization", token)
        }
        return chain.proceed(requestBuilder.build())
    }

    override fun authenticate(route: Route?, response: Response): Request? {
        when (hasAuthorizationToken(response)) {
            false -> {
                return null
            }
            true -> {
                val retryCount = response.request.header("RetryCount")?.toInt() ?: 0
                if (retryCount > 2) {
                    return null
                }
                return response.request.newBuilder()
                    .header("Authorization", authorizationManager.getToken().orEmpty())
                    .header("RetryCount", "$retryCount")
                    .build()

            }
        }
    }

    private fun hasAuthorizationToken(response: Response?): Boolean {
        response?.let {
            return it.request.header("Authorization") != null
        }
        return false
    }

}