/*
 * Created by Andreas Oen on 1/10/21 7:36 AM
 * Copyright (c) 2021 . All rights reserved.
 * Last modified 1/10/21 7:36 AM
 * github: https://github.com/oandrz
 */

package com.dre.loyalty.features.hospital.presentation

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.dre.loyalty.core.platform.BaseViewModel
import com.dre.loyalty.features.hospital.presentation.entity.EmptyViewState
import com.dre.loyalty.features.hospital.presentation.entity.Hospital
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class HospitalListViewModel @Inject constructor() : BaseViewModel() {

    private val _emptyViewState: MutableLiveData<EmptyViewState> = MutableLiveData()
    val emptyViewState: LiveData<EmptyViewState> = _emptyViewState

    init {
        _emptyViewState.value = EmptyViewState(View.GONE)
    }

    fun handleItemWereFiltered(results: List<Hospital>) {
        _emptyViewState.value = if (results.isEmpty()) {
            EmptyViewState(View.VISIBLE)
        } else {
            EmptyViewState(View.GONE)
        }
    }

    fun handleListReset() {
        viewModelScope.launch(Dispatchers.Main) {
            _emptyViewState.value = EmptyViewState(View.GONE)
        }
    }
}
