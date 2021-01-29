package com.dre.loyalty.core.networking

import com.dre.loyalty.core.networking.response.Response
import com.dre.loyalty.features.hospital.data.entity.response.HospitalResponse
import com.dre.loyalty.features.hospital.domain.usecase.GetHospitalListUseCase.Param
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

private const val HOSPITAL_LIST_ENDPOINT = "hospital_list"
interface HospitalService {
    @POST(HOSPITAL_LIST_ENDPOINT) fun getHospitalList(
        @Body param: Param
    ) : Call<Response<List<HospitalResponse>>>
}