/*
 * Created by Andreas Oen on 12/26/20 10:17 AM
 * Copyright (c) 2020 . All rights reserved.
 * Last modified 12/26/20 10:17 AM
 * github: https://github.com/oandrz
 */

package com.dre.loyalty.features.userdetailform.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import com.dre.loyalty.core.functional.Event
import com.dre.loyalty.core.platform.BaseViewModel
import com.dre.loyalty.core.util.validator.type.ValidationType
import com.dre.loyalty.features.userdetailform.presentation.entity.EmailInputState
import com.dre.loyalty.features.userdetailform.presentation.entity.FirstNameInputState
import com.dre.loyalty.features.userdetailform.presentation.entity.KTPInputState
import com.dre.loyalty.features.userdetailform.presentation.entity.LastNameInputState
import com.dre.loyalty.features.userdetailform.presentation.entity.RegisterButtonState
import javax.inject.Inject

class UserDetailFormViewModel @Inject constructor() : BaseViewModel() {

    private val _showGenderSheet: MutableLiveData<Event<String?>> = MutableLiveData()
    val showGenderSheet: LiveData<Event<String?>> = _showGenderSheet

    private val _showDateSheet: MutableLiveData<Event<String?>> = MutableLiveData()
    val showDateSheet: LiveData<Event<String?>> = _showDateSheet

    private val _showConfirmationSheet: MutableLiveData<Event<Boolean?>> = MutableLiveData()
    val showConfirmationSheet: LiveData<Event<Boolean?>> = _showConfirmationSheet

    private val _selectedGender: MutableLiveData<String> = MutableLiveData()
    val selectedGender: LiveData<String> = _selectedGender

    private val _selectedDate: MutableLiveData<String> = MutableLiveData()
    val selectedDate: LiveData<String> = _selectedDate

    private val _firstNameInputState: MutableLiveData<FirstNameInputState> = MutableLiveData()
    val firstNameInputState: LiveData<FirstNameInputState> = _firstNameInputState

    private val _lastNameInputState: MutableLiveData<LastNameInputState> = MutableLiveData()
    val lastNameInputState: LiveData<LastNameInputState> = _lastNameInputState

    private val _ktpInputState: MutableLiveData<KTPInputState> = MutableLiveData()
    val ktpInputState: LiveData<KTPInputState> = _ktpInputState

    private val _emailInputState: MutableLiveData<EmailInputState> = MutableLiveData()
    val emailInputState: LiveData<EmailInputState> = _emailInputState

    private val _registerButtonState: MutableLiveData<RegisterButtonState> = MediatorLiveData()
    val registerButtonState: LiveData<RegisterButtonState> = _registerButtonState

    private val _navigateToCreateSecurity: MutableLiveData<Event<Boolean>> = MutableLiveData()
    val navigateToCreateSecurity: LiveData<Event<Boolean>> = _navigateToCreateSecurity

    init {
        _firstNameInputState.value = FirstNameInputState(-1)
        _lastNameInputState.value = LastNameInputState(-1)
        _ktpInputState.value = KTPInputState(-1)
        _emailInputState.value = EmailInputState(-1)
        _registerButtonState.value = RegisterButtonState(false)
    }

    fun handleDateOfBirthClicked() {
        _showDateSheet.value = Event(_selectedDate.value)
    }

    fun handleGenderFormClicked() {
        _showGenderSheet.value = Event(_selectedGender.value)
    }

    fun handleRegisterButtonClicked() {
        _showConfirmationSheet.value = Event(true)
    }

    fun handleSelectedGender(selected: String) {
        _selectedGender.value = selected
        checkButtonState()
    }

    fun handleSelectedDate(date: String) {
        _selectedDate.value = date
        checkButtonState()
    }

    fun handleFirstNameTextChangedListener(text: String) {
        val result = ValidationType.EMPTY.strategy.validate(text)
        if (result.isPass) {
            _firstNameInputState.value = FirstNameInputState(null)
        } else {
            _firstNameInputState.value = FirstNameInputState(result.errorMessage)
        }
        checkButtonState()
    }

    fun handleLastNameTextChangeListener(text: String) {
        val result = ValidationType.EMPTY.strategy.validate(text)
        if (result.isPass) {
            _lastNameInputState.value = LastNameInputState(null)
        } else {
            _lastNameInputState.value = LastNameInputState(result.errorMessage)
        }
        checkButtonState()
    }

    fun handleKtpTextChangedListener(text: String) {
        val result = ValidationType.KTP.strategy.validate(text)
        if (result.isPass) {
            _ktpInputState.value = KTPInputState(null)
        } else {
            _ktpInputState.value = KTPInputState(result.errorMessage)
        }
        checkButtonState()
    }

    fun handleEmailTextChangedListener(text: String) {
        val result = ValidationType.EMAIL.strategy.validate(text)
        if (result.isPass) {
            _emailInputState.value = EmailInputState(null)
        } else {
            _emailInputState.value = EmailInputState(result.errorMessage)
        }
        checkButtonState()
    }

    fun handleConfirmationSheetConfirmButtonClicked() {
        _navigateToCreateSecurity.value = Event(true)
    }

    private fun checkButtonState() {
        _registerButtonState.value = RegisterButtonState(isRegisterButtonEnabled())
    }

    private fun isRegisterButtonEnabled(): Boolean =
            _firstNameInputState.value?.error == null
            && _lastNameInputState.value?.error == null
            && _ktpInputState.value?.error == null
            && _emailInputState.value?.error == null
            && _selectedGender.value != null
            && _selectedDate.value != null
}
