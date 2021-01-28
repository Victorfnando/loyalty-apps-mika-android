/*
 *
 * Created by Andreas on 1/28/21 12:16 PM
 * Copyright (c) 2021 . All rights reserved.
 * Last modified 1/28/21 12:16 PM
 * Github Link: https://github.com/oandrz
 * Email: oentoro.andreas@gmail.com
 *
 */

package com.dre.loyalty.features.authentication.data.entity.request

data class LoginRequest(
    val email: String,
    val password: String
)