package com.dre.loyalty.features.hospital.data.repository.datasource

import com.dre.loyalty.core.service.HospitalService
import com.dre.loyalty.features.home.data.entity.response.BaseResponse
import com.dre.loyalty.features.hospital.data.entity.response.HospitalListResponse
import com.dre.loyalty.features.hospital.domain.usecase.GetHospitalListUseCase.Param
import retrofit2.Call
import javax.inject.Inject

class HospitalListCloudDataSource @Inject constructor(
    private val service: HospitalService
) : HospitalListCloudDataSourceContract {
    override fun getHospitalList(param: Param): Call<BaseResponse<HospitalListResponse>> {
        return service.getHospitalList(param)
    }
}