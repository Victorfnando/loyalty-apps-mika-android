package com.dre.loyalty.core.service

import com.dre.loyalty.features.home.data.entity.response.BaseResponse
import com.dre.loyalty.features.hospital.data.entity.response.HospitalListResponse
import com.dre.loyalty.features.hospital.domain.usecase.GetHospitalListUseCase.Param
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

private const val HOSPITAL_LIST_ENDPOINT = "hospitals"
interface HospitalService {
    @POST(HOSPITAL_LIST_ENDPOINT) fun getHospitalList(
        @Body param: Param
    ) : Call<BaseResponse<HospitalListResponse>>
}