/*
 * Created by Andreas Oen on 1/13/21 7:10 PM
 * Copyright (c) 2021 . All rights reserved.
 * Last modified 1/13/21 7:10 PM
 * github: https://github.com/oandrz
 */

package com.dre.loyalty.features.faq.presentation.screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dre.loyalty.R
import com.dre.loyalty.core.networking.exception.Failure
import com.dre.loyalty.core.platform.extension.observe
import com.dre.loyalty.core.platform.extension.viewModel
import com.dre.loyalty.core.platform.navigation.Navigator
import com.dre.loyalty.core.platform.BaseFragment
import com.dre.loyalty.core.view.sheet.ConfirmationSheetModal
import com.dre.loyalty.databinding.FragmentFaqBinding
import com.dre.loyalty.features.faq.domain.entity.FrequentlyAskedQuestion
import com.dre.loyalty.features.faq.presentation.item.FaqExpandableItem
import com.dre.loyalty.features.faq.presentation.item.FaqItem
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.adapters.ItemAdapter
import com.mikepenz.fastadapter.expandable.getExpandableExtension
import javax.inject.Inject

class FaqFragment : BaseFragment() {

    @Inject
    lateinit var navigator: Navigator

    private var binding: FragmentFaqBinding? = null
    private lateinit var vm: FaqViewModel

    private val faqExpandableItemAdapter: ItemAdapter<FaqExpandableItem> by lazy {
        ItemAdapter<FaqExpandableItem>()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent.inject(this)
        vm = viewModel(viewModelFactory) {
            observe(faqList, ::renderList)
            observe(loading, ::renderLoading)
            observe(failure, ::showFailureSheet)
        }
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
        vm.init()
    }

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
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
    }

    private fun renderList(faq: List<FrequentlyAskedQuestion>?) {
        faqExpandableItemAdapter.add(
            faq?.map {
                FaqExpandableItem(it.question).also { item ->
                    item.subItems = mutableListOf(FaqItem(it.answer))
                }
            } ?: emptyList()
        )
    }

    private fun renderLoading(visibility: Int?) {
        binding?.progress?.visibility = visibility ?: View.GONE
    }

    private fun showFailureSheet(failure: Failure?) {
        if (failure == null) return
        val sheet = getNetworkErrorSheet(failure)?.also {
            it.primaryButtonClickListener = {
                vm.init()
            }
            it.secondaryButtonClickListener = {
                navigator.showSetting(requireContext())
            }
        }
        sheet?.show(requireActivity().supportFragmentManager, ConfirmationSheetModal.TAG)
    }

    companion object {
        fun newInstance(): FaqFragment {
            return FaqFragment()
        }
    }
}
