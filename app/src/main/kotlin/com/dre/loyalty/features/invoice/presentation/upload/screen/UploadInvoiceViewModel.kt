/*
 *
 * Created by Andreas on 1/27/21 1:24 PM
 * Copyright (c) 2021 . All rights reserved.
 * Last modified 1/22/21 9:23 AM
 * Github Link: https://github.com/oandrz
 * Email: oentoro.andreas@gmail.com
 *
 */

package com.dre.loyalty.features.invoice.presentation.upload.screen

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dre.loyalty.core.interactor.UseCase
import com.dre.loyalty.core.model.Hospital
import com.dre.loyalty.core.platform.functional.Event
import com.dre.loyalty.core.platform.BaseViewModel
import com.dre.loyalty.core.platform.util.preferences.AuthenticationManager
import com.dre.loyalty.core.platform.util.validator.type.ValidationType
import com.dre.loyalty.features.hospital.domain.usecase.GetHospitalListUseCase
import com.dre.loyalty.features.invoice.presentation.entity.UploadInvoiceState
import com.dre.loyalty.features.invoice.presentation.upload.entity.HospitalBranchState
import com.dre.loyalty.features.invoice.presentation.upload.entity.TotalAmountState
import com.dre.loyalty.features.invoice.presentation.upload.entity.UploadButtonState
import javax.inject.Inject

class UploadInvoiceViewModel @Inject constructor(
    private val authenticationManager: AuthenticationManager,
    private val getHospitalListUseCase: GetHospitalListUseCase
) : BaseViewModel() {

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

    private val _changePhotoClicked: MutableLiveData<Event<Boolean>> = MutableLiveData()
    val changePhotoClicked: LiveData<Event<Boolean>> = _changePhotoClicked

    private val _nextButtonClicked: MutableLiveData<Event<UploadInvoiceState>> = MutableLiveData()
    val nextButtonClicked: LiveData<Event<UploadInvoiceState>> = _nextButtonClicked

    private val _imageUri: MutableLiveData<String> = MutableLiveData()
    val imageUri: LiveData<String> = _imageUri

    private var hospitalList: List<Hospital> = emptyList()

    init {
        _totalAmountInputState.value = TotalAmountState(-1)
        _uploadButtonState.value = UploadButtonState(false)
    }

    fun init(imageUri: String) {
        handleImageSelected(imageUri)
        refreshHospitalList()
    }

    fun handleTransactionDateEtClicked() {
        _showDateSelector.value = Event(_selectedDate.value)
    }

    fun handleFormBranchEtClicked() {
        _showBranchListSheet.value = Event(
            HospitalBranchState(
                hospitalList.map { it.name },
                _selectedBranch.value
            ))
    }

    fun refreshHospitalList() {
        _loading.value = View.VISIBLE
        getHospitalListUseCase(UseCase.None()) {
            it.fold(::handleFailure, ::handleSuccessGetHospitalList)
        }
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

    fun handleChangePhotoClicked() {
        _changePhotoClicked.value = Event(true)
    }

    fun handleNextButtonClicked(
        hospitalName: String,
        date: String,
        price: String
    ) {
        _nextButtonClicked.value = Event(
            UploadInvoiceState(
                authenticationManager.getUserId().orEmpty(),
                "",
                hospitalList.find { it.name == hospitalName }?.id.orEmpty(),
                price.toLong(),
                "",
                _imageUri.value.orEmpty(),
                date
            )
        )
    }

    fun handleImageSelected(uri: String) {
        _imageUri.value = uri
    }

    private fun validateButtonState() {
        _uploadButtonState.value = UploadButtonState(isValidForm())
    }

    private fun isValidForm() = _totalAmountInputState.value?.error == null
            && _selectedBranch.value != null
            && _selectedDate.value != null

    private fun handleSuccessGetHospitalList(hospitalList: List<Hospital>) {
        _loading.value = View.GONE
        this.hospitalList = hospitalList
    }
}
