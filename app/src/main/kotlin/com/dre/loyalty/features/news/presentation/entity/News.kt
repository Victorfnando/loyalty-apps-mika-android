/*
 * Created by Andreas Oen on 1/2/21 3:03 PM
 * Copyright (c) 2021 . All rights reserved.
 * Last modified 1/2/21 3:03 PM
 * github: https://github.com/oandrz
 */

package com.dre.loyalty.features.news.presentation.entity

data class News(
    val id: String,
    val date: String,
    val title: String,
    val desc: String,
    val bannerImageUrl: String,
)
