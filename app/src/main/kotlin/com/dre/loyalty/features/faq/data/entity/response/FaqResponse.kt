package com.dre.loyalty.features.faq.data.entity.response

import com.google.gson.annotations.SerializedName

data class FaqResponse(
    @SerializedName("faqId")
    val id: String,
    @SerializedName("question")
    val question: String,
    @SerializedName("answer")
    val answer: String
)