/*
 * Created by Andreas Oen on 1/7/21 7:18 PM
 * Copyright (c) 2021 . All rights reserved.
 * Last modified 1/7/21 7:18 PM
 * github: https://github.com/oandrz
 */

package com.dre.loyalty.features.invoice.presentation.upload.screen.sheet

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.andrefrsousa.superbottomsheet.SuperBottomSheetFragment
import com.dre.loyalty.R
import com.dre.loyalty.core.view.VerticalDividerDecoration
import com.dre.loyalty.core.view.item.SelectorItem
import com.dre.loyalty.databinding.SheetBranchListBinding
import com.dre.loyalty.features.invoice.presentation.upload.entity.HospitalBranchState
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.adapters.ItemAdapter

class BranchListSheetModal : SuperBottomSheetFragment() {

    var onItemClickListener: ((String) -> Unit)? = null
    private var binding: SheetBranchListBinding? = null
    private var state: HospitalBranchState? = null

    private val selectorItem: ItemAdapter<SelectorItem> by lazy {
        ItemAdapter<SelectorItem>()
    }

    private val searchTextListener = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        }

        override fun afterTextChanged(s: Editable?) {
            selectorItem.filter(s.toString())
        }
    }

    override fun getCornerRadius(): Float {
        return resources.getDimension(R.dimen.bottom_sheet_radius)
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
        binding = SheetBranchListBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        state = arguments?.getParcelable(SHEET_PARAM)
        bindList()
        binding?.etSearch?.editText?.addTextChangedListener(searchTextListener)
    }

    override fun onDetach() {
        binding?.etSearch?.editText?.removeTextChangedListener(searchTextListener)
        binding = null
        super.onDetach()
    }

    private fun bindList() {
        binding?.rvHospital?.run {
            layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
            addItemDecoration(VerticalDividerDecoration(requireContext(), RecyclerView.VERTICAL))
            adapter = FastAdapter.with(selectorItem).also {
                it.onClickListener = { _, _, item, _ ->
                    onItemClickListener?.invoke(item.text)
                    dismiss()
                    true
                }
            }
        }
        val list = state?.hospitalList?.map { item ->
            SelectorItem(item).also {
                it.isSelected = state?.selectedItem?.isNotEmpty() ?: false && state?.selectedItem == item
            }
        } ?: emptyList()
        selectorItem.itemFilter.filterPredicate = { item, constraint ->
            item.text.toLowerCase().contains(constraint.toString())
        }
        selectorItem.set(list)
    }

    companion object {
        const val TAG = "BranchListSheetModal"
        private const val SHEET_PARAM = "SHEET_PARAM"

        fun newInstance(state: HospitalBranchState): BranchListSheetModal {
            val args = Bundle().also {
                it.putParcelable(SHEET_PARAM, state)
            }
            val fragment = BranchListSheetModal()
            fragment.arguments = args
            return fragment
        }
    }
}
