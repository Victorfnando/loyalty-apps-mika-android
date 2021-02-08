/*
 *
 * Created by Andreas on 2/8/21 9:48 PM
 * Copyright (c) 2021 . All rights reserved.
 * Last modified 2/8/21 9:48 PM
 * Github Link: https://github.com/oandrz
 * Email: oentoro.andreas@gmail.com
 *
 */

package com.dre.loyalty.features.profile.data.entity.request

data class UpdateProfileImageRequest(
    val userId: Int,
    val uri: String
)