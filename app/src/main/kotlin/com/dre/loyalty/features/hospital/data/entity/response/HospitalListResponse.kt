package com.dre.loyalty.features.hospital.data.entity.response

import com.google.gson.annotations.SerializedName

data class HospitalListResponse(
    @SerializedName("hospitals")
    val content: List<HospitalResponse>
)