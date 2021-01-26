/*
 *
 * Created by Andreas on 1/26/21 5:13 PM
 * Copyright (c) 2021 . All rights reserved.
 * Last modified 1/26/21 2:51 PM
 * Github Link: https://github.com/oandrz
 * Email: oentoro.andreas@gmail.com
 *
 */

package com.dre.loyalty.features.home.data.entity.mapper

import com.dre.loyalty.core.model.*
import com.dre.loyalty.core.response.BaseResponse
import com.dre.loyalty.features.home.data.entity.response.*
import javax.inject.Inject

class HomeResponseMapper @Inject constructor() {
    fun transform(response: BaseResponse<HomeResponse>): Home {
        return Home(
            transform(response.data.cardResponse.first()),
            response.data.cashBackResponse.map { transform(it)},
            response.data.newsResponse.map { transform(it) }
        )
    }

    private fun transform(response: CardResponse): Card {
        return Card(
            response.id,
            response.cardName,
            Hospital(response.hospitalId, response.hospitalName)
        )
    }

    private fun transform(response: CashBackResponse): CashBack {
        return CashBack(
            response.id,
            response.cashBack,
            response.date,
            Hospital(response.hospitalId, response.hospitalName)
        )
    }

    private fun transform(response: NewsResponse): News {
        return News(
            response.id,
            response.title,
            response.description,
            response.dateTime,
            response.imgUrl
        )
    }
}