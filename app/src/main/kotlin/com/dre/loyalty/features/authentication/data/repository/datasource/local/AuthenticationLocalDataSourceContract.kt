/*
 *
 * Created by Andreas on 1/28/21 12:06 PM
 * Copyright (c) 2021 . All rights reserved.
 * Last modified 1/28/21 12:06 PM
 * Github Link: https://github.com/oandrz
 * Email: oentoro.andreas@gmail.com
 *
 */

package com.dre.loyalty.features.authentication.data.repository.datasource.local

interface AuthenticationLocalDataSourceContract {
    var userId: String?
    var token: String?
}