package com.dre.loyalty.core.model

data class Hospital(
    val id: String,
    val name: String,
    val detail: HospitalDetail? = null
)