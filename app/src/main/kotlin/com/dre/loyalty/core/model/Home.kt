package com.dre.loyalty.core.model

data class Home(
    val card: Card,
    val cashBack: List<CashBack>,
    val news: List<News>
)