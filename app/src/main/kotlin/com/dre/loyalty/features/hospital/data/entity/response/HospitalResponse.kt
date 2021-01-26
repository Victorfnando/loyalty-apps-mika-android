package com.dre.loyalty.features.hospital.data.entity.response

import com.google.gson.annotations.SerializedName

data class HospitalResponse(
    @SerializedName("hospitalId")
    val id: String,
    @SerializedName("hospitalName")
    val name: String,
    @SerializedName("hospitalCity")
    val city: String,
    @SerializedName("hospitalAddress")
    val address: String,
    @SerializedName("phoneInfo")
    val phoneInfo: String,
    @SerializedName("phoneIgd")
    val phoneIgd: String
)