/*
 * Created by Andreas Oen on 1/14/21 8:33 PM
 * Copyright (c) 2021 . All rights reserved.
 * Last modified 1/14/21 8:33 PM
 * github: https://github.com/oandrz
 */

package com.dre.loyalty.features.contactus.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dre.loyalty.core.functional.Event
import com.dre.loyalty.core.platform.BaseViewModel
import com.dre.loyalty.core.view.sheet.SheetListState
import javax.inject.Inject

class ContactUsViewModel @Inject constructor() : BaseViewModel() {

    private val _showContactCategorySheet: MutableLiveData<Event<SheetListState>> = MutableLiveData()
    val showContactCategorySheet: LiveData<Event<SheetListState>> = _showContactCategorySheet

    private val _selectedContactCategory: MutableLiveData<String> = MutableLiveData()
    val selectedContactCategory: LiveData<String> = _selectedContactCategory

    fun handleCategoryClicked() {
        _showContactCategorySheet.value = Event(
            SheetListState(
                "Kategori Pesan",
                listOf("Klaim cashback", "Saran", "Keluhan", "Ubah Profil"),
                _selectedContactCategory.value
            )
        )
    }

    fun handleSelectedCategory(selected: String) {
        _selectedContactCategory.value = selected
    }
}
