/*
 * Created by Andreas Oen on 1/14/21 8:16 PM
 * Copyright (c) 2021 . All rights reserved.
 * Last modified 1/14/21 8:16 PM
 * github: https://github.com/oandrz
 */

package com.dre.loyalty.features.contactus.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.dre.loyalty.R
import com.dre.loyalty.core.extension.observe
import com.dre.loyalty.core.extension.viewModel
import com.dre.loyalty.core.functional.Event
import com.dre.loyalty.core.platform.BaseFragment
import com.dre.loyalty.core.view.sheet.SheetListModal
import com.dre.loyalty.core.view.sheet.SheetListState
import com.dre.loyalty.databinding.FragmentContactUsBinding

class ContactUsFragment : BaseFragment() {

    private var binding: FragmentContactUsBinding? = null

    private lateinit var vm: ContactUsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent.inject(this)
        vm = viewModel(viewModelFactory) {
            observe(showContactCategorySheet, ::showCategorySheet)
            observe(selectedContactCategory, ::updateCategoryEt)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentContactUsBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindToolbar()
        bindEtCategory()
    }

    override fun onDetach() {
        binding = null
        super.onDetach()
    }

    private fun bindEtCategory() {
        binding?.etCategory?.editText?.run {
            isFocusableInTouchMode = false
            isClickable = true
            setOnClickListener {
                vm.handleCategoryClicked()
            }
        }
    }

    private fun showCategorySheet(event: Event<SheetListState>?) {
        event?.getIfNotHandled()?.let { state ->
            val sheet = SheetListModal.newInstance(state).also { modal ->
                modal.onItemClickListener = {
                    vm.handleSelectedCategory(it)
                }
            }
            sheet.show(requireActivity().supportFragmentManager, SheetListModal.TAG)
        }
    }

    private fun updateCategoryEt(selected: String?) {
        selected?.let {
            binding?.etCategory?.text = it
        }
    }

    private fun bindToolbar() {
        (activity as AppCompatActivity).run {
            setSupportActionBar(binding?.toolbarLayout?.toolbar)
            supportActionBar?.setTitle(R.string.contactUs_screen_title)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
    }

    companion object {
        fun newInstance(): ContactUsFragment {
            return ContactUsFragment()
        }
    }
}
