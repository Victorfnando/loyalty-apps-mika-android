package com.dre.loyalty.core.model

data class CashBack(
    val id: String,
    val amount: Long,
    val date: String,
    val hospital: Hospital
)