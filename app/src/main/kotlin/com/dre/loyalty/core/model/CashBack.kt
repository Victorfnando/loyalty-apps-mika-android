package com.dre.loyalty.core.model

data class CashBack(
    val id: String,
    val receiptId: String,
    val amount: Long,
    val date: String,
    val phone: String,
    val walletName: String
)