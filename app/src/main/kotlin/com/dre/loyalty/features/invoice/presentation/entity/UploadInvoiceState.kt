/*
 *
 * Created by Andreas on 1/31/21 2:08 PM
 * Copyright (c) 2021 . All rights reserved.
 * Last modified 1/31/21 2:08 PM
 * Github Link: https://github.com/oandrz
 * Email: oentoro.andreas@gmail.com
 *
 */

package com.dre.loyalty.features.invoice.presentation.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UploadInvoiceState(
    val userId: String,
    val walletId: String,
    val hospitalId: String,
    val price: Long,
    val phoneNumber: String,
    val imageUri: String,
    val date: String
) : Parcelable