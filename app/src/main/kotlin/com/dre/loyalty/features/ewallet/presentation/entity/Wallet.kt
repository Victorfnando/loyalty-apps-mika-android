/*
 * Created by Andreas Oen on 1/21/21 7:22 PM
 * Copyright (c) 2021 . All rights reserved.
 * Last modified 1/21/21 7:22 PM
 * github: https://github.com/oandrz
 */

package com.dre.loyalty.features.ewallet.presentation.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Wallet(
    val text: String,
    val imageUrl: String
) : Parcelable
