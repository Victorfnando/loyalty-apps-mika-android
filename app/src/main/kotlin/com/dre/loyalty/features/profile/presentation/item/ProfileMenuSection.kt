/*
 * Created by Andreas Oen on 1/10/21 7:29 PM
 * Copyright (c) 2021 . All rights reserved.
 * Last modified 1/10/21 7:29 PM
 * github: https://github.com/oandrz
 */

package com.dre.loyalty.features.profile.presentation.item

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dre.loyalty.R
import com.dre.loyalty.databinding.SectionProfileMenuBinding
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.adapters.ItemAdapter
import com.mikepenz.fastadapter.binding.AbstractBindingItem

class ProfileMenuSection(
    private val sectionName: String,
    private val body: List<ItemAdapter<*>>
) : AbstractBindingItem<SectionProfileMenuBinding>() {

    var dividerItemDecoration: RecyclerView.ItemDecoration? = null

    override val type: Int
        get() = R.id.sectionMenuItem

    override fun createBinding(
        inflater: LayoutInflater,
        parent: ViewGroup?
    ): SectionProfileMenuBinding {
        return SectionProfileMenuBinding.inflate(inflater, parent, false)
    }

    override fun bindView(binding: SectionProfileMenuBinding, payloads: List<Any>) {
        super.bindView(binding, payloads)
        binding.rvMenu.run {
            dividerItemDecoration?.let {
                addItemDecoration(it)
            }
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            adapter = FastAdapter.with(body)
        }
        binding.tvSection.text = sectionName
    }

    override fun unbindView(binding: SectionProfileMenuBinding) {
        super.unbindView(binding)
        binding.tvSection.text = null
        binding.rvMenu.adapter = null
    }
}
