/*
 * Created by Andreas Oen on 1/13/21 7:10 PM
 * Copyright (c) 2021 . All rights reserved.
 * Last modified 1/13/21 7:10 PM
 * github: https://github.com/oandrz
 */

package com.dre.loyalty.features.faq.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dre.loyalty.R
import com.dre.loyalty.core.platform.BaseFragment
import com.dre.loyalty.core.view.VerticalSpaceDecoration
import com.dre.loyalty.databinding.FragmentFaqBinding
import com.dre.loyalty.features.faq.presentation.item.FaqExpandableItem
import com.dre.loyalty.features.faq.presentation.item.FaqItem
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.adapters.ItemAdapter
import com.mikepenz.fastadapter.expandable.getExpandableExtension

class FaqFragment : BaseFragment() {

    private var binding: FragmentFaqBinding? = null

    private val faqExpandableItemAdapter: ItemAdapter<FaqExpandableItem> by lazy {
        ItemAdapter<FaqExpandableItem>()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFaqBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindToolbar()
        bindList()
    }

    override fun onDetach() {
        binding = null
        super.onDetach()
    }

    private fun bindToolbar() {
        (activity as AppCompatActivity).run {
            setSupportActionBar(binding?.toolbarLayout?.toolbar)
            supportActionBar?.setTitle(R.string.faq_screen_title)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
    }

    private fun bindList() {
        binding?.rvFaq?.run {
            layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
            adapter = FastAdapter.with(faqExpandableItemAdapter).also {
                it.getExpandableExtension()
            }
        }
        faqExpandableItemAdapter.add(
            listOf(
                FaqExpandableItem("testing 1").also {
                    it.subItems = mutableListOf(FaqItem("testint content 1"))
                },
                FaqExpandableItem("testing 2").also {
                    it.subItems = mutableListOf(FaqItem("testint content 1"))
                },
                FaqExpandableItem("testing 3").also {
                    it.subItems = mutableListOf(FaqItem("testint content 1"))
                },
                FaqExpandableItem("testing 4").also {
                    it.subItems = mutableListOf(FaqItem("testint content 1"))
                },
                FaqExpandableItem("testing 5").also {
                    it.subItems = mutableListOf(FaqItem("testint content 1"))
                },
                FaqExpandableItem("testing 6").also {
                    it.subItems = mutableListOf(FaqItem("testint content 1"))
                },
                FaqExpandableItem("testing 7").also {
                    it.subItems = mutableListOf(FaqItem("testint content 1"))
                },
                FaqExpandableItem("testing 8").also {
                    it.subItems = mutableListOf(FaqItem("testint content 1"))
                }
            )
        )
    }

    companion object {
        fun newInstance(): FaqFragment {
            return FaqFragment()
        }
    }
}
