/*
 * Created by Andreas Oen on 1/7/21 6:40 PM
 * Copyright (c) 2021 . All rights reserved.
 * Last modified 1/7/21 6:40 PM
 * github: https://github.com/oandrz
 */

package com.dre.loyalty.features.uploadinvoice.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dre.loyalty.core.functional.Event
import com.dre.loyalty.core.platform.BaseViewModel
import com.dre.loyalty.core.util.validator.type.ValidationType
import com.dre.loyalty.features.uploadinvoice.presentation.entity.HospitalBranchState
import com.dre.loyalty.features.uploadinvoice.presentation.entity.TotalAmountState
import com.dre.loyalty.features.uploadinvoice.presentation.entity.UploadButtonState
import javax.inject.Inject

class UploadInvoiceViewModel @Inject constructor() : BaseViewModel() {

    private val _showDateSelector: MutableLiveData<Event<String?>> = MutableLiveData()
    val showDateSelector: LiveData<Event<String?>> = _showDateSelector

    private val _showBranchListSheet: MutableLiveData<Event<HospitalBranchState>> = MutableLiveData()
    val showBranchListSheet: LiveData<Event<HospitalBranchState>> = _showBranchListSheet

    private val _selectedBranch: MutableLiveData<String> = MutableLiveData()
    val selectedBranch: LiveData<String> = _selectedBranch

    private val _selectedDate: MutableLiveData<String> = MutableLiveData()
    val selectedDate: LiveData<String> = _selectedDate

    private val _uploadButtonState: MutableLiveData<UploadButtonState> = MutableLiveData()
    val uploadButtonState: LiveData<UploadButtonState> = _uploadButtonState

    private val _totalAmountInputState: MutableLiveData<TotalAmountState> = MutableLiveData()
    val totalAmountInputState: LiveData<TotalAmountState> = _totalAmountInputState

    init {
        _totalAmountInputState.value = TotalAmountState(-1)
        _uploadButtonState.value = UploadButtonState(false)
    }

    fun handleTransactionDateEtClicked() {
        _showDateSelector.value = Event(_selectedDate.value)
    }

    fun handleFormBranchEtClicked() {
        _showBranchListSheet.value = Event(HospitalBranchState(
            listOf(
                    "kemayoran", "jakarta",
                    "kemayoran", "jakarta",
                    "kemayoran", "jakarta",
                    "kemayoran", "jakarta",
                    "kemayoran", "jakarta",
                    "kemayoran", "jakarta",
                    "kemayoran", "jakarta",
            ),
            _selectedBranch.value
        ))
    }

    fun handleSelectedDate(selectedDate: String) {
        _selectedDate.value = selectedDate
        validateButtonState()
    }

    fun handleSelectedBranch(selectedBranch: String) {
        _selectedBranch.value = selectedBranch
        validateButtonState()
    }

    fun handleTextAmountChanged(amount: String) {
        val result = ValidationType.TOTAL_AMOUNT.strategy.validate(amount)
        if (result.isPass) {
            _totalAmountInputState.value = TotalAmountState(null)
        } else {
            _totalAmountInputState.value = TotalAmountState(result.errorMessage)
        }
        validateButtonState()
    }

    private fun validateButtonState() {
        _uploadButtonState.value = UploadButtonState(isValidForm())
    }

    private fun isValidForm() = _totalAmountInputState.value?.error == null
            && _selectedBranch.value != null
            && _selectedDate.value != null
}