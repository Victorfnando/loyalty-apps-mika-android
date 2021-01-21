/*
 * Created by Andreas Oen on 1/21/21 4:32 PM
 * Copyright (c) 2021 . All rights reserved.
 * Last modified 1/21/21 4:32 PM
 * github: https://github.com/oandrz
 */

package com.dre.loyalty.features.ewallet.presentation.screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.dre.loyalty.core.extension.observe
import com.dre.loyalty.core.extension.viewModel
import com.dre.loyalty.core.functional.Event
import com.dre.loyalty.core.platform.BaseFragment
import com.dre.loyalty.databinding.FragmentEwalletBinding
import com.dre.loyalty.features.ewallet.presentation.entity.Wallet
import com.dre.loyalty.features.ewallet.presentation.entity.WalletInputState
import com.dre.loyalty.features.ewallet.presentation.sheet.EWalletSheetModal

class EWalletFragment : BaseFragment() {

    private var binding: FragmentEwalletBinding? = null

    private lateinit var vm: EWalletViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent.inject(this)
        vm = viewModel(viewModelFactory) {
            observe(walletSelectorClicked, ::showWalletSelectorModal)
            observe(walletInputState, ::updateWalletInput)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEwalletBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindToolbar()
        bindEtWallet()
    }

    private fun bindToolbar() {
        (activity as AppCompatActivity).run {
            setSupportActionBar(binding?.toolbarLayout?.toolbar)
            supportActionBar?.setDisplayShowTitleEnabled(false)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
    }

    private fun bindEtWallet() {
        binding?.etEWallet?.editText?.setOnClickListener {
            vm.handleWalletSelectorClicked()
        }
    }

    private fun showWalletSelectorModal(event: Event<List<Wallet>>?) {
        event?.getIfNotHandled()?.let {
            val sheet = EWalletSheetModal.newInstance(it)
            sheet.listener = { selected ->
                vm.handleSelectedWallet(selected)
            }
            sheet.show(requireActivity().supportFragmentManager, EWalletSheetModal.TAG)
        }
    }

    private fun updateWalletInput(state: WalletInputState?) {
        state?.text?.let {
            binding?.etEWallet?.text = it
        }
    }

    companion object {
        fun newInstance(): EWalletFragment {
            return EWalletFragment()
        }
    }
}
