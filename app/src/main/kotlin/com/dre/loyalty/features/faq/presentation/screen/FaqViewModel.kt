package com.dre.loyalty.features.faq.presentation.screen

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dre.loyalty.core.platform.BaseViewModel
import com.dre.loyalty.features.faq.domain.entity.FrequentlyAskedQuestion
import com.dre.loyalty.features.faq.domain.usecase.GetFaqQuestion
import com.dre.loyalty.features.faq.domain.usecase.GetFaqQuestion.Param
import javax.inject.Inject

class FaqViewModel @Inject constructor(
    private val getFaqQuestion: GetFaqQuestion
) : BaseViewModel() {

    private val _faqList: MutableLiveData<List<FrequentlyAskedQuestion>> = MutableLiveData()
    val faqList: LiveData<List<FrequentlyAskedQuestion>> = _faqList

    fun init() {
        _loading.value = View.VISIBLE
        getFaqQuestion(Param("test", "test")) {
            it.fold(::handleFailure, ::handleSuccessGetFaq)
        }
    }

    private fun handleSuccessGetFaq(faq: List<FrequentlyAskedQuestion>) {
        _loading.value = View.GONE
        _faqList.value = faq
    }
}