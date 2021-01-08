/*
 * Created by Andreas Oen on 1/7/21 8:10 PM
 * Copyright (c) 2021 . All rights reserved.
 * Last modified 1/7/21 8:10 PM
 * github: https://github.com/oandrz
 */

package com.dre.loyalty.features.uploadinvoice.presentation.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class HospitalBranchState(
    val hospitalList: List<String>,
    val selectedItem: String?
) : Parcelable
