/*
 *
 * Created by Andreas on 1/29/21 12:10 PM
 * Copyright (c) 2021 . All rights reserved.
 * Last modified 1/29/21 12:10 PM
 * Github Link: https://github.com/oandrz
 * Email: oentoro.andreas@gmail.com
 *
 */

package com.dre.loyalty.core.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User(
    val firstName: String,
    val lastName: String,
    val cardId: String,
    val phone: String,
    val gender: String,
    val birthDate: String,
    val email: String,
    val password: String
) : Parcelable