/*
 * Created by Andreas Oen on 1/3/21 2:34 PM
 * Copyright (c) 2021 . All rights reserved.
 * Last modified 1/3/21 2:34 PM
 * github: https://github.com/oandrz
 */

package com.dre.loyalty.features.home.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dre.loyalty.core.functional.Event
import com.dre.loyalty.core.platform.BaseViewModel
import javax.inject.Inject

class HomeViewModel @Inject constructor(): BaseViewModel() {

    private val _navigateCashBackList: MutableLiveData<Event<String>> = MutableLiveData()
    val navigateCashBackList: LiveData<Event<String>> = _navigateCashBackList

    private val _navigateNewsList: MutableLiveData<Event<String>> = MutableLiveData()
    val navigateNewsList: LiveData<Event<String>> = _navigateNewsList

    private val _navigateNewsDetail: MutableLiveData<Event<String>> = MutableLiveData()
    val navigateNewsDetail: LiveData<Event<String>> = _navigateNewsDetail

    fun handleCashBackItemClicked() {
    }

    fun handleNewsItemClicked() {
        _navigateNewsDetail.value = Event("")
    }

    fun handleSeeAllCashBackClicked() {
        _navigateCashBackList.value = Event("")
    }

    fun handleSeeAllNewsClicked() {
        _navigateNewsList.value = Event("")
    }
}
