/*
 *
 * Created by Andreas on 1/29/21 4:29 PM
 * Copyright (c) 2021 . All rights reserved.
 * Last modified 1/29/21 4:29 PM
 * Github Link: https://github.com/oandrz
 * Email: oentoro.andreas@gmail.com
 *
 */

package com.dre.loyalty.features.authentication.domain.entity

data class OtpCode(
    val email: String,
    val code: String
)