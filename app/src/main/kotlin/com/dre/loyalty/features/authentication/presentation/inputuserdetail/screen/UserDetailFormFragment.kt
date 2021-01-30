/*
 *
 * Created by Andreas on 1/27/21 2:00 PM
 * Copyright (c) 2021 . All rights reserved.
 * Last modified 1/27/21 1:31 PM
 * Github Link: https://github.com/oandrz
 * Email: oentoro.andreas@gmail.com
 *
 */

package com.dre.loyalty.features.authentication.presentation.inputuserdetail.screen

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.HtmlCompat
import com.dre.loyalty.R
import com.dre.loyalty.core.platform.extension.observe
import com.dre.loyalty.core.platform.extension.viewModel
import com.dre.loyalty.core.platform.functional.Event
import com.dre.loyalty.core.model.User
import com.dre.loyalty.core.platform.navigation.Navigator
import com.dre.loyalty.core.platform.BaseFragment
import com.dre.loyalty.core.platform.util.enumtype.ConfirmationSheetType
import com.dre.loyalty.databinding.FragmentUserDetailFormBinding
import com.dre.loyalty.features.authentication.presentation.inputpassword.enumtype.InputPasswordType
import com.dre.loyalty.features.authentication.presentation.inputuserdetail.screen.dialog.DatePickerDialogFragment
import com.dre.loyalty.core.view.sheet.ConfirmationSheetModal
import com.dre.loyalty.features.authentication.presentation.inputuserdetail.entity.*
import com.dre.loyalty.features.authentication.presentation.inputuserdetail.screen.sheet.GenderSheetModal
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

    private val phoneWatcher: TextWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        override fun afterTextChanged(s: Editable?) { vm.handlePhoneTextChanged(s.toString()) }
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
            observe(phoneInputState, ::updatePhoneInputState)
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
        binding?.etFormFirstname?.editText?.addTextChangedListener(firstNameWatcher)
        binding?.etFormLastname?.editText?.addTextChangedListener(lastNameWatcher)
        binding?.etFormEmail?.editText?.addTextChangedListener(emailWatcher)
        binding?.etFormKtp?.editText?.addTextChangedListener(ktpWatcher)
        binding?.etPhone?.editText?.addTextChangedListener(phoneWatcher)
        bindEtFormGender()
        bindEtDate()
        bindFooter()
    }

    override fun onDestroyView() {
        binding?.etFormFirstname?.editText?.removeTextChangedListener(firstNameWatcher)
        binding?.etFormLastname?.editText?.removeTextChangedListener(lastNameWatcher)
        binding?.etFormEmail?.editText?.removeTextChangedListener(emailWatcher)
        binding?.etFormKtp?.editText?.removeTextChangedListener(ktpWatcher)
        binding?.etPhone?.editText?.removeTextChangedListener(phoneWatcher)
        (activity as AppCompatActivity).setSupportActionBar(null)
        binding = null
        super.onDestroyView()
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
            modal.show(requireActivity().supportFragmentManager, GenderSheetModal.TAG)
        }
    }

    private fun showDateDialog(event: Event<String?>?) {
        event?.getIfNotHandled().let {
            val dialog = DatePickerDialogFragment.newInstance(it)
            dialog.listener = { selectedDate ->
                vm.handleSelectedDate(selectedDate)
            }
            dialog.show(requireActivity().supportFragmentManager, DatePickerDialogFragment.TAG)
        }
    }

    private fun renderSelectedGenderText(value: String?) {
        binding?.etFormGender?.editText?.setText(value)
    }

    private fun renderRegisterButton(state: RegisterButtonState?) {
        binding?.stickyButton?.isButtonEnabled = state?.isEnabled ?: false
    }

    private fun renderFirstName(state: FirstNameInputState?) {
        binding?.etFormFirstname?.error = if (state?.error == null || state.error == -1) {
            ""
        } else {
            getString(state.error)
        }
    }

    private fun renderLastName(state: LastNameInputState?) {
        binding?.etFormLastname?.error = if (state?.error == null || state.error == -1) {
            ""
        } else {
            getString(state.error)
        }
    }

    private fun renderKtp(state: KTPInputState?) {
        binding?.etFormKtp?.error = if (state?.error == null || state.error == -1) {
            ""
        } else {
            getString(state.error)
        }
    }

    private fun updatePhoneInputState(state: PhoneInputState?) {
        binding?.etPhone?.error = if (state?.error == null || state.error == -1) {
            ""
        } else {
            getString(state.error)
        }
    }

    private fun renderEmail(state: EmailInputState?) {
        binding?.etFormEmail?.error = if (state?.error == null || state.error == -1) {
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
            val modal = ConfirmationSheetModal.newInstance(ConfirmationSheetType.USER_DETAIL_FORM_CONFIRM)
            modal.primaryButtonClickListener = {
                vm.handleConfirmationSheetConfirmButtonClicked()
            }
            modal.show(requireActivity().supportFragmentManager, ConfirmationSheetModal.TAG)
        }
    }

    private fun navigateCreatePinScreen(event: Event<User>?) {
        event?.getIfNotHandled()?.let {
            navigator.showInputPasswordScreen(requireContext(), it, InputPasswordType.CREATE)
        }
    }

    private fun bindFooter() {
        binding?.stickyButton?.run {
            tvFooterText = HtmlCompat.fromHtml(
                getString(R.string.userDetail_label_tnc),
                HtmlCompat.FROM_HTML_MODE_LEGACY
            )
            tvFooterClickListener = {

            }
            buttonClickListener = {
                vm.handleRegisterButtonClicked(
                    binding?.etFormFirstname?.editText?.text.toString(),
                    binding?.etFormLastname?.editText?.text.toString(),
                    binding?.etFormKtp?.editText?.text.toString(),
                    binding?.etPhone?.editText?.text.toString(),
                    binding?.etFormEmail?.editText?.text.toString(),
                    binding?.etFormGender?.editText?.text.toString(),
                    binding?.etFormDob?.editText?.text.toString()
                )
            }
        }
    }

    companion object {
        private const val ARG_EMAIL = "ARG_EMAIL"

        fun newInstance(email: String): UserDetailFormFragment {
            return UserDetailFormFragment().also {
                val bundle = Bundle()
                bundle.putString(ARG_EMAIL, email)
                it.arguments = bundle
            }
        }
    }
}
