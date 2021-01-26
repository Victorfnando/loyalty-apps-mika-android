package com.dre.loyalty.features.faq.data.entity.response

import com.google.gson.annotations.SerializedName

data class FaqDataResponse(
    @SerializedName("faqs")
    val content: List<FaqResponse>
)