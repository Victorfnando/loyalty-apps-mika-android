/*
 * Created by Andreas Oen on 12/26/20 10:17 AM
 * Copyright (c) 2020 . All rights reserved.
 * Last modified 12/26/20 10:17 AM
 * github: https://github.com/oandrz
 */

package com.dre.loyalty.features.userdetailform.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dre.loyalty.core.functional.Event
import com.dre.loyalty.core.platform.BaseViewModel
import javax.inject.Inject

class UserDetailFormViewModel @Inject constructor() : BaseViewModel() {

    private val _showGenderSheet: MutableLiveData<Event<String?>> = MutableLiveData()
    val showGenderSheet: LiveData<Event<String?>> = _showGenderSheet

    private val _selectedGender: MutableLiveData<String> = MutableLiveData()
    val selectedGender: LiveData<String> = _selectedGender

    fun handleGenderFormClicked() {
        _showGenderSheet.value = Event(_selectedGender.value)
    }

    fun handleSelectedGender(selected: String) {
        _selectedGender.value = selected
    }
}
