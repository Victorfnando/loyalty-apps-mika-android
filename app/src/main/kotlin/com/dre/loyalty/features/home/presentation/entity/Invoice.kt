/*
 * Created by Andreas Oen on 1/2/21 2:30 PM
 * Copyright (c) 2021 . All rights reserved.
 * Last modified 1/2/21 2:30 PM
 * github: https://github.com/oandrz
 */

package com.dre.loyalty.features.home.presentation.entity

data class Invoice(
    val id: String,
    val price: Long,
    val date: String
)
