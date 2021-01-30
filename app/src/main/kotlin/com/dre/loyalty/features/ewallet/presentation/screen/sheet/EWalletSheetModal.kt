/*
 * Created by Andreas Oen on 1/21/21 5:26 PM
 * Copyright (c) 2021 . All rights reserved.
 * Last modified 1/21/21 5:26 PM
 * github: https://github.com/oandrz
 */

package com.dre.loyalty.features.ewallet.presentation.screen.sheet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.andrefrsousa.superbottomsheet.SuperBottomSheetFragment
import com.dre.loyalty.R
import com.dre.loyalty.databinding.SheetEwalletBinding
import com.dre.loyalty.features.ewallet.presentation.entity.Wallet
import com.dre.loyalty.features.ewallet.presentation.screen.item.WalletSelectorItem
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.adapters.ItemAdapter

class EWalletSheetModal : SuperBottomSheetFragment() {

    private var binding: SheetEwalletBinding? = null

    var listener: ((Wallet) -> Unit)? = null

    private val walletItemAdapter: ItemAdapter<WalletSelectorItem> by lazy {
        ItemAdapter<WalletSelectorItem>()
    }

    override fun getCornerRadius(): Float {
        return resources.getDimension(R.dimen.bottom_sheet_radius)
    }

    override fun getExpandedHeight(): Int {
        return WRAP_CONTENT
    }

    override fun isSheetAlwaysExpanded(): Boolean {
        return true
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = SheetEwalletBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindList()
    }

    private fun bindList() {
        binding?.rvWallet?.run {
            addItemDecoration(DividerItemDecoration(requireContext(), RecyclerView.VERTICAL))
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            adapter = FastAdapter.with(walletItemAdapter).also {
                it.onClickListener = { _, _, item, _ ->
                    listener?.invoke(item.wallet)
                    dismiss()
                    true
                }
            }
        }
        val item: List<Wallet> = arguments?.getParcelableArrayList<Wallet>(ARGUMENT_SHEET_ITEM) ?: emptyList()
        walletItemAdapter.add(
            item.map {
                WalletSelectorItem(it)
            }
        )
    }

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }

    companion object {
        const val TAG = "EWalletSheetModal"
        private const val ARGUMENT_SHEET_ITEM = "ARGUMENT_SHEET_ITEM"

        fun newInstance(item: List<Wallet>): EWalletSheetModal {
            return EWalletSheetModal().also {
                val bundle = Bundle()
                bundle.putParcelableArrayList(ARGUMENT_SHEET_ITEM, ArrayList(item))
                it.arguments = bundle
            }
        }
    }
}
