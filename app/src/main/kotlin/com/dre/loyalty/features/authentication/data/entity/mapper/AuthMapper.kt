/*
 *
 * Created by Andreas on 1/28/21 4:15 PM
 * Copyright (c) 2021 . All rights reserved.
 * Last modified 1/28/21 4:15 PM
 * Github Link: https://github.com/oandrz
 * Email: oentoro.andreas@gmail.com
 *
 */

package com.dre.loyalty.features.authentication.data.entity.mapper

import com.dre.loyalty.core.model.AuthCertificate
import com.dre.loyalty.features.authentication.data.entity.response.LoginResponse
import javax.inject.Inject

class AuthMapper @Inject constructor() {
    fun transform(response: LoginResponse): AuthCertificate {
        return AuthCertificate(response.userId, response.token)
    }
}