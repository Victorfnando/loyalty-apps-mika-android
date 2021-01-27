/*
 * Created by Andreas Oen on 1/3/21 2:34 PM
 * Copyright (c) 2021 . All rights reserved.
 * Last modified 1/3/21 2:34 PM
 * github: https://github.com/oandrz
 */

package com.dre.loyalty.features.home.presentation.screen

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dre.loyalty.core.exception.Failure
import com.dre.loyalty.core.functional.Event
import com.dre.loyalty.core.model.Card
import com.dre.loyalty.core.model.CashBack
import com.dre.loyalty.core.model.Home
import com.dre.loyalty.core.model.News
import com.dre.loyalty.core.platform.BaseViewModel
import com.dre.loyalty.features.home.domain.usecase.GetHomeDataUseCase
import com.dre.loyalty.features.home.domain.usecase.GetHomeDataUseCase.Param
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val getHomeDataUseCase: GetHomeDataUseCase
): BaseViewModel() {

    private val _cardState: MutableLiveData<Card> = MutableLiveData()
    val cardState: LiveData<Card> = _cardState

    private val _cashBackSection: MutableLiveData<List<CashBack>> = MutableLiveData()
    val cashBackSection: LiveData<List<CashBack>> = _cashBackSection

    private val _newsSection: MutableLiveData<List<News>> = MutableLiveData()
    val newsSection: LiveData<List<News>> = _newsSection

    private val _navigateCashBackList: MutableLiveData<Event<String>> = MutableLiveData()
    val navigateCashBackList: LiveData<Event<String>> = _navigateCashBackList

    private val _navigateNewsList: MutableLiveData<Event<String>> = MutableLiveData()
    val navigateNewsList: LiveData<Event<String>> = _navigateNewsList

    private val _navigateNewsDetail: MutableLiveData<Event<String>> = MutableLiveData()
    val navigateNewsDetail: LiveData<Event<String>> = _navigateNewsDetail

    private val _navigateToCamera: MutableLiveData<Event<String>> = MutableLiveData()
    val navigateToCamera: LiveData<Event<String>> = _navigateToCamera

    private val _navigateInvoiceDetail: MutableLiveData<Event<String>> = MutableLiveData()
    val navigateInvoiceDetail: LiveData<Event<String>> = _navigateInvoiceDetail

    fun loadData() {
        _loading.value = View.VISIBLE
        getHomeDataUseCase(Param("test", "tes")) {
            it.fold(::handleFailure, ::handleSuccessGetHome)
        }
    }

    private fun handleSuccessGetHome(response: Home) {
        _loading.value = View.GONE
        _cardState.value = response.card
        _cashBackSection.value = response.cashBack
        _newsSection.value = response.news
    }

    fun handleCashBackItemClicked(id: String) {
        _navigateInvoiceDetail.value = Event(id)
    }

    fun handleNewsItemClicked(id: String) {
        _navigateNewsDetail.value = Event(id)
    }

    fun handleSeeAllCashBackClicked() {
        _navigateCashBackList.value = Event("")
    }

    fun handleSeeAllNewsClicked() {
        _navigateNewsList.value = Event("")
    }

    fun handleUploadInvoiceClicked() {
        _navigateToCamera.value = Event("")
    }
}
