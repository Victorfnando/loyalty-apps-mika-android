/*
 *
 * Created by Andreas on 1/31/21 3:34 PM
 * Copyright (c) 2021 . All rights reserved.
 * Last modified 1/31/21 3:34 PM
 * Github Link: https://github.com/oandrz
 * Email: oentoro.andreas@gmail.com
 *
 */

package com.dre.loyalty.core.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class EWallet(
    val id: String,
    val name: String,
    val imageUrl: String
) : Parcelable