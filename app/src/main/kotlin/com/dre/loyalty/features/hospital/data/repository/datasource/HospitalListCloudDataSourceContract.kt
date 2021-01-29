package com.dre.loyalty.features.hospital.data.repository.datasource

import com.dre.loyalty.core.networking.response.Response
import com.dre.loyalty.features.hospital.data.entity.response.HospitalResponse
import com.dre.loyalty.features.hospital.domain.usecase.GetHospitalListUseCase
import retrofit2.Call

interface HospitalListCloudDataSourceContract {
    fun getHospitalList(param: GetHospitalListUseCase.Param): Call<Response<List<HospitalResponse>>>
}