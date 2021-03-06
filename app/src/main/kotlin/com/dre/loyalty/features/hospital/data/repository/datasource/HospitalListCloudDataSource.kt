package com.dre.loyalty.features.hospital.data.repository.datasource

import com.dre.loyalty.core.networking.HospitalService
import com.dre.loyalty.core.networking.response.LoyaltyResponse
import com.dre.loyalty.features.hospital.data.entity.response.HospitalResponse
import retrofit2.Call
import javax.inject.Inject

class HospitalListCloudDataSource @Inject constructor(
    private val service: HospitalService
) : HospitalListCloudDataSourceContract {
    override fun getHospitalList(): Call<LoyaltyResponse<List<HospitalResponse>>> {
        return service.getHospitalList()
    }
}