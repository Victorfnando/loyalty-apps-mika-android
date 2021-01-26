package com.dre.loyalty.features.hospital.data.repository.datasource

import com.dre.loyalty.core.response.BaseResponse
import com.dre.loyalty.features.hospital.data.entity.response.HospitalListResponse
import com.dre.loyalty.features.hospital.domain.usecase.GetHospitalListUseCase
import retrofit2.Call

interface HospitalListCloudDataSourceContract {
    fun getHospitalList(param: GetHospitalListUseCase.Param): Call<BaseResponse<HospitalListResponse>>
}