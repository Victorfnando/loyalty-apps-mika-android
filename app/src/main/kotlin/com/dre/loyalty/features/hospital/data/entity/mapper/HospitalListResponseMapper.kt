package com.dre.loyalty.features.hospital.data.entity.mapper

import com.dre.loyalty.core.model.Hospital
import com.dre.loyalty.core.model.HospitalDetail
import com.dre.loyalty.core.response.BaseResponse
import com.dre.loyalty.features.hospital.data.entity.response.HospitalListResponse
import com.dre.loyalty.features.hospital.data.entity.response.HospitalResponse
import javax.inject.Inject

class HospitalListResponseMapper @Inject constructor() {

    fun transform(response: BaseResponse<HospitalListResponse>): List<Hospital> {
        return response.data.content.map { transform(it) }
    }

    private fun transform(response: HospitalResponse): Hospital {
        return Hospital(
            response.id,
            response.name,
            HospitalDetail(
                response.city,
                response.address,
                response.phoneInfo,
                response.phoneIgd
            )
        )
    }
}