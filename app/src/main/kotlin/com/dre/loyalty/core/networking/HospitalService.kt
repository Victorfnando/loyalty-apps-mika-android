package com.dre.loyalty.core.networking

import com.dre.loyalty.core.networking.response.LoyaltyResponse
import com.dre.loyalty.features.hospital.data.entity.response.HospitalResponse
import retrofit2.Call
import retrofit2.http.GET

private const val HOSPITAL_LIST_ENDPOINT = "hospitals"
interface HospitalService {
    @GET(HOSPITAL_LIST_ENDPOINT) fun getHospitalList() : Call<LoyaltyResponse<List<HospitalResponse>>>
}