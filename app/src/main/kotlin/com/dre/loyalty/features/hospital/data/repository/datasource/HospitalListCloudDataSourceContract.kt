package com.dre.loyalty.features.hospital.data.repository.datasource

import com.dre.loyalty.core.networking.response.LoyaltyResponse
import com.dre.loyalty.features.hospital.data.entity.response.HospitalResponse
import retrofit2.Call

interface HospitalListCloudDataSourceContract {
    fun getHospitalList(): Call<LoyaltyResponse<List<HospitalResponse>>>
}