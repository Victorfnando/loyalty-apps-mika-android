/*
 * Created by Andreas Oen on 12/26/20 4:31 PM
 * Copyright (c) 2020 . All rights reserved.
 * Last modified 12/26/20 4:31 PM
 * github: https://github.com/oandrz
 */

package com.dre.loyalty.features.resetpin.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.dre.loyalty.core.platform.BaseFragment
import com.dre.loyalty.databinding.FragmentResetPinBinding

class ResetPinFragment : BaseFragment() {

    private var binding: FragmentResetPinBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentResetPinBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindToolbar()
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

    companion object {
        fun newInstance(): ResetPinFragment {
            return ResetPinFragment()
        }
    }
}
