/*
 * Created by Andreas Oen on 12/25/20 12:14 PM
 * Copyright (c) 2020 . All rights reserved.
 * Last modified 12/25/20 12:14 PM
 * github: https://github.com/oandrz
 */

package com.dre.loyalty.features.userdetailform.presentation

import android.os.Bundle
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
import com.dre.loyalty.core.view.PrefixEditTextWithLabel
import com.dre.loyalty.databinding.FragmentUserDetailFormBinding
import javax.inject.Inject

class UserDetailFormFragment : BaseFragment() {

    @Inject
    lateinit var navigator: Navigator

    private lateinit var vm: UserDetailFormViewModel

    private var binding: FragmentUserDetailFormBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent.inject(this)
        vm = viewModel(viewModelFactory) {
            observe(showGenderSheet, ::showGenderModal)
            observe(selectedGender, ::renderSelectedGenderText)
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
        bindEtFormGender()
    }

    override fun onDetach() {
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

    private fun showGenderModal(event: Event<String?>?) {
        event?.getIfNotHandled().let {
            val modal = GenderSheetModal.newInstance(it)
            modal.onItemClickListener = { selected ->
                vm.handleSelectedGender(selected)
            }
            modal.show(activity!!.supportFragmentManager, GenderSheetModal.TAG)
        }
    }

    private fun renderSelectedGenderText(value: String?) {
        binding?.etFormGender?.editText?.setText(value)
    }

    companion object {
        fun newInstance(): UserDetailFormFragment {
            return UserDetailFormFragment()
        }
    }
}
