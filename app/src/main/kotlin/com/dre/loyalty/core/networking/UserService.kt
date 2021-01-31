/*
 *
 * Created by Andreas on 1/31/21 5:43 PM
 * Copyright (c) 2021 . All rights reserved.
 * Last modified 1/31/21 5:43 PM
 * Github Link: https://github.com/oandrz
 * Email: oentoro.andreas@gmail.com
 *
 */

package com.dre.loyalty.core.networking

import com.dre.loyalty.core.networking.response.LoyaltyResponse
import com.dre.loyalty.features.profile.data.entity.response.ImageResponse
import com.dre.loyalty.features.profile.data.entity.response.UserResponse
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.*

private const val USER_ENDPOINT = "profile/{userId}"
private const val CHANGE_PHOTO_PROFILE_ENDPOINT = "change_photo_profile"
interface UserService {
    @GET(USER_ENDPOINT)
    fun getUser(@Path("userId") userId: String): Call<LoyaltyResponse<UserResponse>>

    @Multipart
    @POST(CHANGE_PHOTO_PROFILE_ENDPOINT)
    fun changePhotoProfile(@Part image: MultipartBody.Part): Call<LoyaltyResponse<ImageResponse>>
}