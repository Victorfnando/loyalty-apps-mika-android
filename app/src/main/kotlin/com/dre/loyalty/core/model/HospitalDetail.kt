package com.dre.loyalty.core.model

data class HospitalDetail(
    val city: String,
    val address: String,
    val contactInfo: String,
    val contactEmergency: String,
    val latitude: Double,
    val longitude: Double
)