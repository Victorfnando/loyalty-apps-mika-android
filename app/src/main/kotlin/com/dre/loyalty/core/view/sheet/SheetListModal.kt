/*
 * Created by Andreas Oen on 1/14/21 8:35 PM
 * Copyright (c) 2021 . All rights reserved.
 * Last modified 1/14/21 8:35 PM
 * github: https://github.com/oandrz
 */

package com.dre.loyalty.core.view.sheet

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
import com.dre.loyalty.core.view.item.SelectorItem
import com.dre.loyalty.databinding.SheetListBinding
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.adapters.ItemAdapter

class SheetListModal : SuperBottomSheetFragment() {

    var onItemClickListener: ((String) -> Unit)? = null
    private var binding: SheetListBinding? = null
    private var state: SheetListState? = null

    private val selectorItem: ItemAdapter<SelectorItem> by lazy {
        ItemAdapter<SelectorItem>()
    }

    override fun getCornerRadius(): Float {
        return resources.getDimension(R.dimen.bottom_sheet_radius)
    }

    override fun isSheetAlwaysExpanded(): Boolean {
        return true
    }

    override fun getExpandedHeight(): Int {
        return WRAP_CONTENT
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        state = arguments?.getParcelable(SHEET_LIST_STATE_ARGS)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = SheetListBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.tvCategory?.text = state?.title.orEmpty()
        bindList()
    }

    override fun onDestroyView() {
        binding?.tvCategory?.text = null
        selectorItem.clear()
        binding = null
        super.onDestroyView()
    }

    private fun bindList() {
        binding?.rvContent?.run {
            layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
            addItemDecoration(DividerItemDecoration(requireContext(), RecyclerView.VERTICAL))
            adapter = FastAdapter.with(selectorItem).also {
                it.onClickListener = { _, _, item, _ ->
                    onItemClickListener?.invoke(item.text)
                    dismiss()
                    true
                }
            }
        }
        val list = state?.listContent?.map { item ->
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
        const val TAG = "SheetListModal"
        private const val SHEET_LIST_STATE_ARGS = "SHEET_LIST_STATE_ARGS"

        fun newInstance(state: SheetListState): SheetListModal {
            val args = Bundle().also {
                it.putParcelable(SHEET_LIST_STATE_ARGS, state)
            }
            val fragment = SheetListModal()
            fragment.arguments = args
            return fragment
        }
    }
}
