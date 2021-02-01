/*
 * Created by Andreas Oen on 1/10/21 11:16 PM
 * Copyright (c) 2021 . All rights reserved.
 * Last modified 1/10/21 11:16 PM
 * github: https://github.com/oandrz
 */

package com.dre.loyalty.features.profile.presentation.profile.screen

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dre.loyalty.core.model.User
import com.dre.loyalty.core.networking.exception.Failure
import com.dre.loyalty.core.networking.response.BasicResponse
import com.dre.loyalty.core.platform.functional.Event
import com.dre.loyalty.core.platform.BaseViewModel
import com.dre.loyalty.core.platform.util.preferences.AuthenticationManager
import com.dre.loyalty.features.authentication.domain.usecase.LogOutUseCase
import com.dre.loyalty.features.profile.data.entity.failure.LogoutFailure
import com.dre.loyalty.features.profile.data.entity.failure.UploadPhotoFailure
import com.dre.loyalty.features.profile.domain.usecase.GetProfileUseCase
import com.dre.loyalty.features.profile.domain.usecase.UpdateProfilePictureUseCase
import com.dre.loyalty.features.profile.presentation.profile.entity.ProfileState
import javax.inject.Inject

class ProfileViewModel @Inject constructor(
    private val authenticationManager: AuthenticationManager,
    private val getProfileUseCase: GetProfileUseCase,
    private val updatePhotoProfileUseCase: UpdateProfilePictureUseCase,
    private val logOutUseCase: LogOutUseCase
) : BaseViewModel() {

    private val _navigateChangeProfile: MutableLiveData<Event<User>> = MutableLiveData()
    val navigateChangeProfile: LiveData<Event<User>> = _navigateChangeProfile

    private val _navigateChangePassword: MutableLiveData<Event<Boolean>> = MutableLiveData()
    val navigateChangePassword: LiveData<Event<Boolean>> = _navigateChangePassword

    private val _navigateFaq: MutableLiveData<Event<Boolean>> = MutableLiveData()
    val navigateFaq: LiveData<Event<Boolean>> = _navigateFaq

    private val _navigateContact: MutableLiveData<Event<Boolean>> = MutableLiveData()
    val navigateContact: LiveData<Event<Boolean>> = _navigateContact

    private val _navigateTnc: MutableLiveData<Event<Boolean>> = MutableLiveData()
    val navigateTnc: LiveData<Event<Boolean>> = _navigateTnc

    private val _logoutConfirmationSheet: MutableLiveData<Event<Boolean>> = MutableLiveData()
    val logoutConfirmationSheet: LiveData<Event<Boolean>> = _logoutConfirmationSheet

    private val _navigateToAuthSelector: MutableLiveData<Event<Boolean>> = MutableLiveData()
    val navigateToAuthSelector: LiveData<Event<Boolean>> = _navigateToAuthSelector

    private val _profilePictureClickedEvent: MutableLiveData<Event<Boolean>> = MutableLiveData()
    val profilePictureClickedEvent: LiveData<Event<Boolean>> = _profilePictureClickedEvent

    private val _userProfileState: MutableLiveData<ProfileState> = MutableLiveData()
    val userProfileState: LiveData<ProfileState> = _userProfileState

    private lateinit var user: User
    private lateinit var userId: String
    private lateinit var selectedImageUri: String

    fun init() {
        userId = authenticationManager.getUserId().orEmpty()
        refresh()
    }

    fun handleProfilePictureClicked() {
        _profilePictureClickedEvent.value = Event(true)
    }

    fun handleChangeProfileMenuClicked() {
        _navigateChangeProfile.value = Event(user)
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
        _logoutConfirmationSheet.value = Event(true)
    }

    fun handleLogoutConfirmationClicked() {
        _loading.value = View.VISIBLE
        logOutUseCase(LogOutUseCase.Param(userId)) { result ->
            result.fold(::handleFailureLogout, ::handleSuccessLogout)
        }
    }

    fun handleProfilePictureSelected(uri: String) {
        _loading.value = View.VISIBLE
        selectedImageUri = uri
        uploadProfilePicture()
    }

    fun refresh() {
        _loading.value = View.VISIBLE
        getProfileUseCase(userId) {
            it.fold(::handleFailure, ::handleSuccessGetProfile)
        }
    }

    fun uploadProfilePicture() {
        _loading.value = View.VISIBLE
        updatePhotoProfileUseCase(selectedImageUri) {
            it.fold(::handleFailedUploadPhoto, ::handleSuccessPhotoProfile)
        }
    }

    private fun handleSuccessGetProfile(user: User) {
        _loading.value = View.GONE
        this.user = user
        _userProfileState.value = ProfileState(
            user.profilePictureImageUrl,
            "${user.firstName} ${user.lastName}",
            user.email
        )
    }

    private fun handleSuccessPhotoProfile(imageUrl: String) {
        _loading.value = View.GONE
        _userProfileState.value = _userProfileState.value?.copy(profileImageUrl = imageUrl)
    }

    private fun handleFailedUploadPhoto(failure: Failure) {
        _loading.value = View.GONE
        handleFailure(UploadPhotoFailure())
    }

    private fun handleFailureLogout(failure: Failure) {
        _loading.value = View.GONE
        handleFailure(LogoutFailure())
    }

    private fun handleSuccessLogout(response: BasicResponse) {
        _loading.value = View.GONE
        authenticationManager.clear()
        _navigateToAuthSelector.value = Event(true)
    }
}
