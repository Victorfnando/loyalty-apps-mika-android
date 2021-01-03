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

    fun handleSeeAllCashBackClicked() {
        _navigateCashBackList.value = Event("")
    }

    fun handleSeeAllNewsClicked() {

    }
}
