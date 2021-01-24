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
import com.dre.loyalty.core.util.validator.type.ValidationType
import com.dre.loyalty.core.view.sheet.SheetListState
import com.dre.loyalty.features.contactus.presentation.entity.InputMessageState
import com.dre.loyalty.features.contactus.presentation.entity.SendMessageButtonState
import javax.inject.Inject

class ContactUsViewModel @Inject constructor() : BaseViewModel() {

    private val _showContactCategorySheet: MutableLiveData<Event<SheetListState>> = MutableLiveData()
    val showContactCategorySheet: LiveData<Event<SheetListState>> = _showContactCategorySheet

    private val _sendButtonState: MutableLiveData<SendMessageButtonState> = MutableLiveData()
    val sendButtonState: LiveData<SendMessageButtonState> = _sendButtonState

    private val _inputMessageState: MutableLiveData<InputMessageState> = MutableLiveData()
    val inputMessageState: LiveData<InputMessageState> = _inputMessageState

    private val _selectedContactCategory: MutableLiveData<String> = MutableLiveData()
    val selectedContactCategory: LiveData<String> = _selectedContactCategory

    private val _phoneButtonClicked: MutableLiveData<Event<String>> = MutableLiveData()
    val phoneButtonClicked: LiveData<Event<String>> = _phoneButtonClicked

    private val _submitMessageClicked: MutableLiveData<Event<Boolean>> = MutableLiveData()
    val submitMessageClicked: LiveData<Event<Boolean>> = _submitMessageClicked

    init {
        _inputMessageState.value = InputMessageState(-1)
        _sendButtonState.value = SendMessageButtonState(false)
    }

    fun handleCategoryClicked() {
        _showContactCategorySheet.value = Event(
            SheetListState(
                "Kategori Pesan",
                listOf("Klaim cashback", "Saran", "Keluhan", "Ubah Profil"),
                _selectedContactCategory.value
            )
        )
    }

    fun handleSubmitMessageClicked() {
        _submitMessageClicked.value = Event(true)
    }

    fun handleSelectedCategory(selected: String) {
        _selectedContactCategory.value = selected
        checkButtonState()
    }

    fun handlePhoneButtonClicked() {
        _phoneButtonClicked.value = Event("081221123213131")
    }

    fun handleMessageChanged(text: String) {
        val result = ValidationType.EMPTY.strategy.validate(text)
        if (result.isPass) {
            _inputMessageState.value = _inputMessageState.value?.copy(error = null)
        } else {
            _inputMessageState.value = _inputMessageState.value?.copy(error = result.errorMessage)
        }
        checkButtonState()
    }

    private fun checkButtonState() {
        _sendButtonState.value = _sendButtonState.value?.copy(
            isEnabled = _inputMessageState.value?.error == null &&
                    _selectedContactCategory.value != null
        )
    }
}
