/*
 * Created by Andreas Oen on 12/25/20 12:14 PM
 * Copyright (c) 2020 . All rights reserved.
 * Last modified 12/25/20 12:14 PM
 * github: https://github.com/oandrz
 */

package com.dre.loyalty.features.userdetailform.presentation

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.HtmlCompat
import com.dre.loyalty.R
import com.dre.loyalty.core.extension.observe
import com.dre.loyalty.core.extension.viewModel
import com.dre.loyalty.core.functional.Event
import com.dre.loyalty.core.navigation.Navigator
import com.dre.loyalty.core.platform.BaseFragment
import com.dre.loyalty.databinding.FragmentUserDetailFormBinding
import com.dre.loyalty.features.userdetailform.presentation.dialog.DatePickerDialogFragment
import com.dre.loyalty.features.userdetailform.presentation.entity.EmailInputState
import com.dre.loyalty.features.userdetailform.presentation.entity.FirstNameInputState
import com.dre.loyalty.features.userdetailform.presentation.entity.KTPInputState
import com.dre.loyalty.features.userdetailform.presentation.entity.LastNameInputState
import com.dre.loyalty.features.userdetailform.presentation.entity.RegisterButtonState
import com.dre.loyalty.features.userdetailform.presentation.sheet.ConfirmationSheetModal
import com.dre.loyalty.features.userdetailform.presentation.sheet.GenderSheetModal
import javax.inject.Inject

class UserDetailFormFragment : BaseFragment() {

    @Inject
    lateinit var navigator: Navigator

    private lateinit var vm: UserDetailFormViewModel

    private var binding: FragmentUserDetailFormBinding? = null

    private val firstNameWatcher: TextWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        override fun afterTextChanged(s: Editable?) { vm.handleFirstNameTextChangedListener(s.toString()) }
    }

    private val lastNameWatcher: TextWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        override fun afterTextChanged(s: Editable?) { vm.handleLastNameTextChangeListener(s.toString()) }
    }

    private val emailWatcher: TextWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        override fun afterTextChanged(s: Editable?) { vm.handleEmailTextChangedListener(s.toString()) }
    }

    private val ktpWatcher: TextWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        override fun afterTextChanged(s: Editable?) { vm.handleKtpTextChangedListener(s.toString()) }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent.inject(this)
        vm = viewModel(viewModelFactory) {
            observe(showGenderSheet, ::showGenderModal)
            observe(showDateSheet, ::showDateDialog)
            observe(selectedGender, ::renderSelectedGenderText)
            observe(selectedDate, ::renderSelectedDate)
            observe(firstNameInputState, ::renderFirstName)
            observe(lastNameInputState, ::renderLastName)
            observe(ktpInputState, ::renderKtp)
            observe(emailInputState, ::renderEmail)
            observe(registerButtonState, ::renderRegisterButton)
            observe(showConfirmationSheet, ::showConfirmationSheet)
            observe(navigateToCreateSecurity, ::navigateCreatePinScreen)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentUserDetailFormBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindToolbar()
        binding?.tvFormTnc?.text = HtmlCompat.fromHtml(
            getString(R.string.userdetailform_screen_label_tnc),
            HtmlCompat.FROM_HTML_MODE_LEGACY
        )
        binding?.etFormFirstname?.editText?.addTextChangedListener(firstNameWatcher)
        binding?.etFormLastname?.editText?.addTextChangedListener(lastNameWatcher)
        binding?.etFormEmail?.editText?.addTextChangedListener(emailWatcher)
        binding?.etFormKtp?.editText?.addTextChangedListener(ktpWatcher)
        bindEtFormGender()
        bindEtDate()
        binding?.btnRegister?.setOnClickListener {
            vm.handleRegisterButtonClicked()
        }
    }

    override fun onDetach() {
        binding?.etFormFirstname?.editText?.removeTextChangedListener(firstNameWatcher)
        binding?.etFormLastname?.editText?.removeTextChangedListener(lastNameWatcher)
        binding?.etFormEmail?.editText?.removeTextChangedListener(emailWatcher)
        binding?.etFormKtp?.editText?.removeTextChangedListener(ktpWatcher)
        (activity as AppCompatActivity).setSupportActionBar(null)
        binding = null
        super.onDetach()
    }

    private fun bindToolbar() {
        (activity as AppCompatActivity).run {
            setSupportActionBar(binding?.toolbarLayout?.toolbar)
            supportActionBar?.setDisplayShowTitleEnabled(false)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
    }

    private fun bindEtFormGender() {
        binding?.etFormGender?.editText?.run {
            isFocusableInTouchMode = false
            isClickable = true
            setOnClickListener {
                vm.handleGenderFormClicked()
            }
        }
    }

    private fun bindEtDate() {
        binding?.etFormDob?.editText?.run {
            isFocusableInTouchMode = false
            isClickable = true
            setOnClickListener {
                vm.handleDateOfBirthClicked()
            }
        }
    }

    private fun showGenderModal(event: Event<String?>?) {
        event?.getIfNotHandled().let {
            val modal = GenderSheetModal.newInstance(it)
            modal.onItemClickListener = { selected ->
                vm.handleSelectedGender(selected)
            }
            modal.show(activity!!.supportFragmentManager, GenderSheetModal.TAG)
        }
    }

    private fun showDateDialog(event: Event<String?>?) {
        event?.getIfNotHandled().let {
            val dialog = DatePickerDialogFragment.newInstance(it)
            dialog.listener = { selectedDate ->
                vm.handleSelectedDate(selectedDate)
            }
            dialog.show(activity!!.supportFragmentManager, DatePickerDialogFragment.TAG)
        }
    }

    private fun renderSelectedGenderText(value: String?) {
        binding?.etFormGender?.editText?.setText(value)
    }

    private fun renderRegisterButton(state: RegisterButtonState?) {
        binding?.btnRegister?.isEnabled = state?.isEnabled ?: false
    }

    private fun renderFirstName(state: FirstNameInputState?) {
        if (state?.error == -1) return
        binding?.etFormFirstname?.error = if (state?.error == null) {
            ""
        } else {
            getString(state.error)
        }
    }

    private fun renderLastName(state: LastNameInputState?) {
        if (state?.error == -1) return
        binding?.etFormLastname?.error = if (state?.error == null) {
            ""
        } else {
            getString(state.error)
        }
    }

    private fun renderKtp(state: KTPInputState?) {
        if (state?.error == -1) return
        binding?.etFormKtp?.error = if (state?.error == null) {
            ""
        } else {
            getString(state.error)
        }
    }

    private fun renderEmail(state: EmailInputState?) {
        if (state?.error == -1) return
        binding?.etFormEmail?.error = if (state?.error == null) {
            ""
        } else {
            getString(state.error)
        }
    }

    private fun renderSelectedDate(date: String?) {
        binding?.etFormDob?.text = date
    }

    private fun showConfirmationSheet(event: Event<Boolean?>?) {
        event?.getIfNotHandled()?.let {
            val modal = ConfirmationSheetModal.newInstance()
            modal.onClickListener = {
                vm.handleConfirmationSheetConfirmButtonClicked()
            }
            modal.show(activity!!.supportFragmentManager, ConfirmationSheetModal.TAG)
        }
    }

    private fun navigateCreatePinScreen(event: Event<Boolean>?) {
        event?.getIfNotHandled()?.let {
            navigator.showCreatePin(requireContext())
        }
    }

    companion object {
        fun newInstance(): UserDetailFormFragment {
            return UserDetailFormFragment()
        }
    }
}
