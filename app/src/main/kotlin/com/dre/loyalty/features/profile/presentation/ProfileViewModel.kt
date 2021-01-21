/*
 * Created by Andreas Oen on 1/10/21 11:16 PM
 * Copyright (c) 2021 . All rights reserved.
 * Last modified 1/10/21 11:16 PM
 * github: https://github.com/oandrz
 */

package com.dre.loyalty.features.profile.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dre.loyalty.core.functional.Event
import com.dre.loyalty.core.platform.BaseViewModel
import javax.inject.Inject

class ProfileViewModel @Inject constructor() : BaseViewModel() {

    private val _navigateChangeProfile: MutableLiveData<Event<Boolean>> = MutableLiveData()
    val navigateChangeProfile: LiveData<Event<Boolean>> = _navigateChangeProfile

    private val _navigateChangePassword: MutableLiveData<Event<Boolean>> = MutableLiveData()
    val navigateChangePassword: LiveData<Event<Boolean>> = _navigateChangePassword

    private val _navigateFaq: MutableLiveData<Event<Boolean>> = MutableLiveData()
    val navigateFaq: LiveData<Event<Boolean>> = _navigateFaq

    private val _navigateContact: MutableLiveData<Event<Boolean>> = MutableLiveData()
    val navigateContact: LiveData<Event<Boolean>> = _navigateContact

    private val _navigateTnc: MutableLiveData<Event<Boolean>> = MutableLiveData()
    val navigateTnc: LiveData<Event<Boolean>> = _navigateTnc

    private val _profilePictureClickedEvent: MutableLiveData<Event<Boolean>> = MutableLiveData()
    val profilePictureClickedEvent: LiveData<Event<Boolean>> = _profilePictureClickedEvent

    fun handleProfilePictureClicked() {
        _profilePictureClickedEvent.value = Event(true)
    }

    fun handleChangeProfileMenuClicked() {
        _navigateChangeProfile.value = Event(true)
    }

    fun handleChangePasswordMenuClicked() {
        _navigateChangePassword.value = Event(true)
    }

    fun handleFaqMenuClicked() {
        _navigateFaq.value = Event(true)
    }

    fun handleContactMenuClicked() {
        _navigateContact.value = Event(true)
    }

    fun handleTnCMenuClicked() {
        _navigateTnc.value = Event(true)
    }

    fun handleLogoutMenuClicked() {

    }
}
