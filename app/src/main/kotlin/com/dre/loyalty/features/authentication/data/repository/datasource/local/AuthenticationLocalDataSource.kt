/*
 *
 * Created by Andreas on 1/28/21 4:21 PM
 * Copyright (c) 2021 . All rights reserved.
 * Last modified 1/28/21 4:21 PM
 * Github Link: https://github.com/oandrz
 * Email: oentoro.andreas@gmail.com
 *
 */

package com.dre.loyalty.features.authentication.data.repository.datasource.local

import com.dre.loyalty.core.platform.util.preferences.AuthenticationManager
import javax.inject.Inject

class AuthenticationLocalDataSource @Inject constructor(
    private val authenticationManager: AuthenticationManager
) : AuthenticationLocalDataSourceContract {

    override var userId: String?
        get() = authenticationManager.getUserId()
        set(value) {
            if (value != null) {
                authenticationManager.saveUserId(value)
            }
        }
    override var token: String?
        get() = authenticationManager.getToken()
        set(value) {
            if (value != null) {
                authenticationManager.saveToken(value)
            }
        }
}