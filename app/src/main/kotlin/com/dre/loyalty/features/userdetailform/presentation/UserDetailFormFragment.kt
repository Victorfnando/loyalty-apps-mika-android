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
import com.dre.loyalty.core.navigation.Navigator
import com.dre.loyalty.core.platform.BaseFragment
import com.dre.loyalty.databinding.FragmentUserDetailFormBinding
import javax.inject.Inject

class UserDetailFormFragment : BaseFragment() {

    @Inject
    lateinit var navigator: Navigator

    private var binding: FragmentUserDetailFormBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent.inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentUserDetailFormBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onDetach() {
        binding = null
        super.onDetach()
    }

    companion object {
        fun newInstance(): UserDetailFormFragment {
            return UserDetailFormFragment()
        }
    }
}
