/*
 *
 * Created by Andreas on 1/27/21 1:29 PM
 * Copyright (c) 2021 . All rights reserved.
 * Last modified 1/24/21 5:30 PM
 * Github Link: https://github.com/oandrz
 * Email: oentoro.andreas@gmail.com
 *
 */

package com.dre.loyalty.features.profile.presentation.changeprofile.screen

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.dre.loyalty.R
import com.dre.loyalty.core.model.User
import com.dre.loyalty.core.networking.exception.Failure
import com.dre.loyalty.core.platform.extension.observe
import com.dre.loyalty.core.platform.extension.viewModel
import com.dre.loyalty.core.platform.functional.Event
import com.dre.loyalty.core.platform.BaseFragment
import com.dre.loyalty.core.platform.navigation.Navigator
import com.dre.loyalty.core.platform.util.enumtype.ConfirmationSheetType
import com.dre.loyalty.core.view.sheet.ConfirmationSheetModal
import com.dre.loyalty.databinding.FragmentChangeProfileBinding
import com.dre.loyalty.features.profile.presentation.changeprofile.entity.DescriptionEtState
import com.dre.loyalty.features.profile.presentation.changeprofile.entity.SendButtonState
import javax.inject.Inject

class UpdateProfileFragment : BaseFragment() {

    @Inject
    lateinit var navigator: Navigator

    private var binding: FragmentChangeProfileBinding? = null

    private lateinit var vm: UpdateProfileViewModel

    private val descWatcher: TextWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        override fun afterTextChanged(s: Editable?) { vm.handleEtDescriptionChanged(s.toString()) }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent.inject(this)
        vm = viewModel(viewModelFactory) {
            observe(descInputState, ::updateDescriptionEtState)
            observe(sendButtonState, ::updateSendButtonState)
            observe(successUpdateProfileSheet, ::showEditProfileSuccessModal)
            observe(userState, ::renderProfile)
            observe(loading, ::renderLoading)
            observe(failure, ::showFailureSheet)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentChangeProfileBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindToolbar()
        binding?.etDescription?.addTextChangedListener(descWatcher)
        bindSubmitButton()
        arguments?.getParcelable<User>(ARG_USER)?.also {
            vm.init(it)
        }
    }

    override fun onDestroyView() {
        binding?.etDescription?.removeTextChangedListener(descWatcher)
        binding = null
        super.onDestroyView()
    }

    private fun bindToolbar() {
        (activity as AppCompatActivity).run {
            setSupportActionBar(binding?.toolbarLayout?.toolbar)
            supportActionBar?.title = getString(R.string.updateprofile_screen_title)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
    }

    private fun bindSubmitButton() {
        binding?.btnSend?.setOnClickListener {
            vm.handleSubmitButtonClicked(
                binding?.etDescription?.text.toString()
            )
        }
    }

    private fun updateDescriptionEtState(state: DescriptionEtState?) {
        if (state?.error != null && state.error != -1) {
            binding?.tvError?.run {
                visibility = View.VISIBLE
                text = getString(state.error)
            }
        } else {
            binding?.tvError?.visibility = View.GONE
        }
    }

    private fun updateSendButtonState(state: SendButtonState?) {
        binding?.btnSend?.isEnabled = state?.isEnable ?: false
    }

    private fun showEditProfileSuccessModal(event: Event<Boolean>?) {
        event?.getIfNotHandled()?.let {
            val modal = ConfirmationSheetModal.newInstance(ConfirmationSheetType.CHANGE_PROFILE_SUCCESS_SHEET)
            modal.primaryButtonClickListener = {
                requireActivity().finish()
            }
            modal.show(requireActivity().supportFragmentManager, ConfirmationSheetModal.TAG)
        }
    }

    private fun renderProfile(user: User?) {
        user?.let {
            binding?.tvFullname?.text = "${it.firstName} ${it.lastName}"
            binding?.tvIdentification?.text = it.cardId
            binding?.tvGender?.text = it.gender
            binding?.tvDob?.text = it.birthDate
        }
    }

    private fun renderLoading(visibility: Int?) {
        visibility?.let {
            binding?.progress?.visibility = it
        }
    }

    private fun showFailureSheet(failure: Failure?) {
        if (failure == null) return
        binding?.btnSend?.isEnabled = true
        val sheet = getNetworkErrorSheet(failure)?.also {
            it.primaryButtonClickListener = {
                vm.handleSubmitButtonClicked(binding?.etDescription?.text.toString())
            }
            it.secondaryButtonClickListener = {
                navigator.showSetting(requireContext())
            }
        }
        sheet?.show(requireActivity().supportFragmentManager, ConfirmationSheetModal.TAG)
    }

    companion object {
        private const val ARG_USER = "ARG_USER"

        fun newInstance(user: User): UpdateProfileFragment {
            val bundle = Bundle()
            bundle.putParcelable(ARG_USER, user)
            return UpdateProfileFragment().also {
                it.arguments = bundle
            }
        }
    }
}
