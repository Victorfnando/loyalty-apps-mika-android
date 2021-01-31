/*
 * Created by Andreas Oen on 1/11/21 8:47 PM
 * Copyright (c) 2021 . All rights reserved.
 * Last modified 1/11/21 8:47 PM
 * github: https://github.com/oandrz
 */

package com.dre.loyalty.features.profile.presentation.changeprofile.screen

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dre.loyalty.core.model.User
import com.dre.loyalty.core.networking.response.BasicResponse
import com.dre.loyalty.core.platform.functional.Event
import com.dre.loyalty.core.platform.BaseViewModel
import com.dre.loyalty.core.platform.util.preferences.AuthenticationManager
import com.dre.loyalty.core.platform.util.validator.type.ValidationType
import com.dre.loyalty.features.profile.domain.usecase.UpdateProfileUseCase
import com.dre.loyalty.features.profile.domain.usecase.UpdateProfileUseCase.Param
import com.dre.loyalty.features.profile.presentation.changeprofile.entity.DescriptionEtState
import com.dre.loyalty.features.profile.presentation.changeprofile.entity.SendButtonState
import javax.inject.Inject

class UpdateProfileViewModel @Inject constructor(
    private val authenticationManager: AuthenticationManager,
    private val updateProfileUseCase: UpdateProfileUseCase
) : BaseViewModel() {

    private val _descInputState: MutableLiveData<DescriptionEtState> = MutableLiveData()
    val descInputState: LiveData<DescriptionEtState> = _descInputState

    private val _sendButtonState: MutableLiveData<SendButtonState> = MutableLiveData()
    val sendButtonState: LiveData<SendButtonState> = _sendButtonState

    private val _successUpdateProfileSheet: MutableLiveData<Event<Boolean>> = MutableLiveData()
    val successUpdateProfileSheet: LiveData<Event<Boolean>> = _successUpdateProfileSheet

    private val _userState: MutableLiveData<User> = MutableLiveData()
    val userState: LiveData<User> = _userState

    init {
        _descInputState.value = DescriptionEtState(-1)
        _sendButtonState.value = SendButtonState(false)
    }

    private lateinit var user: User

    fun init(user: User) {
        this.user = user
        _userState.value = user
    }

    fun handleEtDescriptionChanged(text: String) {
        val result = ValidationType.EMPTY.strategy.validate(text)
        if (result.isPass) {
            _descInputState.value = DescriptionEtState(null)
        } else {
            _descInputState.value = DescriptionEtState(result.errorMessage)
        }
        checkButtonState()
    }

    fun handleSubmitButtonClicked(message: String) {
        _loading.value = View.VISIBLE
        _sendButtonState.value = SendButtonState(false)
        updateProfileUseCase(Param(authenticationManager.getUserId().orEmpty(), message)) {
            it.fold(::handleFailure, ::handleSuccessSubmitMessage)
        }
    }

    private fun checkButtonState() {
        _sendButtonState.value = SendButtonState(_descInputState.value?.error == null)
    }

    private fun handleSuccessSubmitMessage(response: BasicResponse) {
        _loading.value = View.GONE
        _sendButtonState.value = SendButtonState(true)
        _successUpdateProfileSheet.value = Event(true)
    }
}
