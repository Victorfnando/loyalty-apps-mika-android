/*
 *
 * Created by Andreas on 1/31/21 5:45 PM
 * Copyright (c) 2021 . All rights reserved.
 * Last modified 1/31/21 5:45 PM
 * Github Link: https://github.com/oandrz
 * Email: oentoro.andreas@gmail.com
 *
 */

package com.dre.loyalty.features.profile.data.repository.datasource

import com.dre.loyalty.core.networking.response.BasicResponse
import com.dre.loyalty.core.networking.response.LoyaltyResponse
import com.dre.loyalty.features.profile.data.entity.request.ContactUsRequest
import com.dre.loyalty.features.profile.data.entity.request.UpdateProfileRequest
import com.dre.loyalty.features.profile.data.entity.response.ImageResponse
import com.dre.loyalty.features.profile.data.entity.response.UserResponse
import retrofit2.Call

interface UserCloudDataSourceContract {
    fun getUser(userId: String): Call<LoyaltyResponse<UserResponse>>
    fun changeProfileImage(uri: String): Call<LoyaltyResponse<ImageResponse>>
    fun updateProfile(request: UpdateProfileRequest): Call<BasicResponse>
    fun submitContactUs(request: ContactUsRequest): Call<BasicResponse>
}