/*
 * Created by Andreas Oen on 12/30/20 8:15 PM
 * Copyright (c) 2020 . All rights reserved.
 * Last modified 12/30/20 8:15 PM
 * github: https://github.com/oandrz
 */

package com.dre.loyalty.features.home.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dre.loyalty.core.platform.BaseFragment
import com.dre.loyalty.databinding.FragmentHomeBinding

class HomeFragment : BaseFragment() {

    private var binding: FragmentHomeBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onDetach() {
        binding = null
        super.onDetach()
    }

    companion object {
        fun newInstance(): HomeFragment {
            return HomeFragment()
        }
    }
}
