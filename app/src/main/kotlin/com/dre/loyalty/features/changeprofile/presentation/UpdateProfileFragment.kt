/*
 * Created by Andreas Oen on 1/11/21 6:46 PM
 * Copyright (c) 2021 . All rights reserved.
 * Last modified 1/11/21 6:46 PM
 * github: https://github.com/oandrz
 */

package com.dre.loyalty.features.changeprofile.presentation

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.dre.loyalty.R
import com.dre.loyalty.core.extension.observe
import com.dre.loyalty.core.extension.viewModel
import com.dre.loyalty.core.platform.BaseFragment
import com.dre.loyalty.databinding.FragmentChangeProfileBinding
import com.dre.loyalty.features.changeprofile.presentation.entity.DescriptionEtState
import com.dre.loyalty.features.changeprofile.presentation.entity.SendButtonState

class UpdateProfileFragment : BaseFragment() {

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
    }

    override fun onDetach() {
        binding?.etDescription?.removeTextChangedListener(descWatcher)
        binding = null
        super.onDetach()
    }

    private fun bindToolbar() {
        (activity as AppCompatActivity).run {
            setSupportActionBar(binding?.toolbarLayout?.toolbar)
            supportActionBar?.title = getString(R.string.updateprofile_screen_title)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
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

    companion object {
        fun newInstance(): UpdateProfileFragment {
            return UpdateProfileFragment()
        }
    }
}
