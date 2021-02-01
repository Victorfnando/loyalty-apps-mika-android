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

import com.dre.loyalty.core.networking.UserService
import com.dre.loyalty.core.networking.response.BasicResponse
import com.dre.loyalty.core.networking.response.LoyaltyResponse
import com.dre.loyalty.features.profile.data.entity.request.ContactUsRequest
import com.dre.loyalty.features.profile.data.entity.request.UpdateProfileRequest
import com.dre.loyalty.features.profile.data.entity.response.ImageResponse
import com.dre.loyalty.features.profile.data.entity.response.UserResponse
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import retrofit2.Call
import java.io.File
import javax.inject.Inject


class UserCloudDataSource @Inject constructor(
    private val service: UserService
) : UserCloudDataSourceContract {

    override fun getUser(userId: String): Call<LoyaltyResponse<UserResponse>> {
        return service.getUser(userId)
    }

    override fun changeProfileImage(uri: String): Call<LoyaltyResponse<ImageResponse>> {
        val file = File(uri)
        val requestFile: RequestBody = file.asRequestBody("multipart/form-data".toMediaTypeOrNull())
        val body: MultipartBody.Part = MultipartBody.Part.createFormData("profileImage", file.name, requestFile)
        return service.changePhotoProfile(body)
    }

    override fun updateProfile(request: UpdateProfileRequest): Call<BasicResponse> {
        return service.updateProfile(request)
    }

    override fun submitContactUs(request: ContactUsRequest): Call<BasicResponse> {
        return service.submitContactUs(request)
    }
}