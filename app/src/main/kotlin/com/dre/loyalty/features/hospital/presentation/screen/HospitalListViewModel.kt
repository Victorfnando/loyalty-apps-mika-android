/*
 * Created by Andreas Oen on 1/10/21 7:36 AM
 * Copyright (c) 2021 . All rights reserved.
 * Last modified 1/10/21 7:36 AM
 * github: https://github.com/oandrz
 */

package com.dre.loyalty.features.hospital.presentation.screen

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.dre.loyalty.core.interactor.UseCase
import com.dre.loyalty.core.platform.BaseViewModel
import com.dre.loyalty.features.hospital.presentation.entity.EmptyViewState
import com.dre.loyalty.core.model.Hospital
import com.dre.loyalty.core.platform.functional.Event
import com.dre.loyalty.features.hospital.domain.usecase.GetHospitalListUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class HospitalListViewModel @Inject constructor(
    private val getHospitalListUseCase: GetHospitalListUseCase
) : BaseViewModel() {

    private val _hospitalList: MutableLiveData<List<Hospital>> = MutableLiveData()
    val hospitalList: LiveData<List<Hospital>> = _hospitalList

    private val _emptyViewState: MutableLiveData<EmptyViewState> = MutableLiveData()
    val emptyViewState: LiveData<EmptyViewState> = _emptyViewState

    private val _navigateMap: MutableLiveData<Event<Triple<Double, Double, String>>> = MutableLiveData()
    val navigateMap: LiveData<Event<Triple<Double, Double, String>>> = _navigateMap

    init {
        _emptyViewState.value = EmptyViewState(View.GONE)
    }

    fun loadData() {
        _loading.value = View.VISIBLE
        getHospitalListUseCase(UseCase.None()) {
            it.fold(::handleFailure, ::handleSuccessGetHospitalList)
        }
    }

    fun handleItemWereFiltered(results: List<Hospital>) {
        checkEmptyView(results)
    }

    fun handleListReset() {
        viewModelScope.launch(Dispatchers.Main) {
            _emptyViewState.value = if (_hospitalList.value != null && _hospitalList.value?.size == 0) {
                EmptyViewState(View.VISIBLE)
            } else {
                EmptyViewState(View.GONE)
            }
        }
    }

    fun handleItemClicked(lat: Double, long: Double, hospitalName: String) {
        _navigateMap.value = Event(Triple(lat, long, hospitalName))
    }

    private fun handleSuccessGetHospitalList(hospitals: List<Hospital>) {
        _loading.value = View.GONE
        _hospitalList.value = hospitals
    }

    private fun checkEmptyView(result: List<Hospital>) {
        _emptyViewState.value = if (result.isEmpty()) {
            EmptyViewState(View.VISIBLE)
        } else {
            EmptyViewState(View.GONE)
        }
    }
}
