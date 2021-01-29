/*
 * Created by Andreas Oen on 12/29/20 7:09 PM
 * Copyright (c) 2020 . All rights reserved.
 * Last modified 12/29/20 7:09 PM
 * github: https://github.com/oandrz
 */

package com.dre.loyalty.features.createpin.presentation

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.dre.loyalty.core.platform.extension.observe
import com.dre.loyalty.core.platform.extension.viewModel
import com.dre.loyalty.core.platform.functional.Event
import com.dre.loyalty.core.platform.navigation.Navigator
import com.dre.loyalty.core.platform.BaseFragment
import com.dre.loyalty.databinding.FragmentCreatePinBinding
import com.dre.loyalty.features.createpin.presentation.enums.CreatePinType
import com.dre.loyalty.features.createpin.presentation.sheet.PostCreatePinConfirmationModal
import javax.inject.Inject

class CreatePinFragment : BaseFragment() {

    @Inject
    lateinit var navigator: Navigator

    private val textWatcher: TextWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        }

        override fun afterTextChanged(s: Editable?) {
            vm.handleTextChanged(s.toString())
        }
    }

    private var binding: FragmentCreatePinBinding? = null
    private lateinit var vm: CreatePinViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent.inject(this)
        vm = viewModel(viewModelFactory) {
            observe(currentType, ::renderViewBasedOnType)
            observe(showError, ::showErrorMessage)
            observe(createButtonState, ::enableDisableButton)
            observe(showConfirmationSheet, ::showConfirmationBottomSheet)
        }
        val type = arguments?.getSerializable(TYPE_KEY) as CreatePinType
        vm.init(type)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCreatePinBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindToolbar()
        bindInputPin()
        binding?.btnCreatePin?.setOnClickListener {
            vm.handleCreatePinButtonClicked(binding?.etPin?.text?.toString().orEmpty())
        }
    }

    override fun onDetach() {
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

    private fun bindInputPin() {
        binding?.etPin?.run {
            requestFocus()
            addTextChangedListener(textWatcher)
        }
    }

    private fun renderViewBasedOnType(type: CreatePinType?) {
        binding?.run {
            etPin.setText("")
            if (type != null) {
                tvPinTitle.setText(type.screenTitle)
                tvDesc.setText(type.screenDesc)
                btnCreatePin.setText(type.btnText)
            }
        }
    }

    private fun showErrorMessage(text: Int?) {
        binding?.etPin?.error = getString(text ?: 0)
    }

    private fun enableDisableButton(isEnable: Boolean?) {
        binding?.btnCreatePin?.isEnabled = isEnable ?: false
    }

    private fun showConfirmationBottomSheet(event: Event<CreatePinType>?) {
        event?.getIfNotHandled()?.let {
            val sheet = PostCreatePinConfirmationModal.newInstance(it)
            sheet.onClickListener = {
                navigator.showLogin(requireContext())
                activity?.finish()
            }
            sheet.show(requireActivity().supportFragmentManager, PostCreatePinConfirmationModal.TAG)
        }
    }

    companion object {
        private const val TYPE_KEY = "TYPE_KEY"

        fun newInstance(type: CreatePinType): CreatePinFragment {
            val fragment = CreatePinFragment()
            val bundle = Bundle().also {
                it.putSerializable(TYPE_KEY, type)
            }
            fragment.arguments = bundle
            return fragment
        }
    }
}
