/*
 * Created by Andreas Oen on 12/30/20 8:15 PM
 * Copyright (c) 2020 . All rights reserved.
 * Last modified 12/30/20 8:15 PM
 * github: https://github.com/oandrz
 */

package com.dre.loyalty.features.home.presentation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.dre.loyalty.R
import com.dre.loyalty.core.extension.observe
import com.dre.loyalty.core.extension.viewModel
import com.dre.loyalty.core.functional.Event
import com.dre.loyalty.core.navigation.Navigator
import com.dre.loyalty.core.platform.BaseFragment
import com.dre.loyalty.core.view.HorizontalSpaceDecoration
import com.dre.loyalty.core.view.VerticalDividerDecoration
import com.dre.loyalty.databinding.FragmentHomeBinding
import com.dre.loyalty.features.cashback.presentation.entity.CashBack
import com.dre.loyalty.features.news.presentation.entity.News
import com.dre.loyalty.features.home.presentation.view.HomeSection
import com.dre.loyalty.features.cashback.presentation.item.CashBackItem
import com.dre.loyalty.features.news.presentation.view.NewsItem
import com.dre.loyalty.features.cashback.presentation.item.UploadInvoiceItem
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.adapters.ItemAdapter
import javax.inject.Inject

class HomeFragment : BaseFragment() {

    @Inject
    lateinit var navigator: Navigator

    private lateinit var vm: HomeViewModel

    private var binding: FragmentHomeBinding? = null

    private val homeSection: ItemAdapter<HomeSection> by lazy {
        ItemAdapter<HomeSection>()
    }

    private val uploadInvoiceItem: ItemAdapter<UploadInvoiceItem> by lazy {
        ItemAdapter<UploadInvoiceItem>()
    }

    private val cashBackItem: ItemAdapter<CashBackItem> by lazy {
        ItemAdapter<CashBackItem>()
    }

    private val newsItem: ItemAdapter<NewsItem> by lazy {
        ItemAdapter<NewsItem>()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent.inject(this)
        vm = viewModel(viewModelFactory) {
            observe(navigateCashBackList, ::showCashBackListScreen)
            observe(navigateNewsList, ::showNewsListScreen)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        cashBackItem.add(
            listOf(
                CashBackItem(CashBack("10", 10000L, "12 Desember 2020")),
                CashBackItem(CashBack("20", 20000L, "20 Desember 2020"))
            )
        )

//        uploadInvoiceItem.add(
//            UploadInvoiceItem {
//                Log.v("testing", "upload clicked")
//            }
//        )

        newsItem.add(
            listOf(
                NewsItem(News("12 Desember 2020", "Rumah Imunisasi Mitra Keluarga Bekasi", "Rumah Imunisasi Mitra Keluarga Bekasi Rumah Imunisasi Mitra Keluarga Bekasi", "")),
                NewsItem(News("20 Desember 2020", "Rumah Imunisasi Mitra Keluarga Jakarta", "Rumah Imunisasi Mitra Keluarga Bekasi Rumah Imunisasi Mitra Keluarga Bekasi", ""))
            )
        )
        homeSection.add(listOf(
            HomeSection(
                "Cashback diterima",
                LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false),
                listOf(cashBackItem)
            ).also {
                it.itemClickListener = {
                    Log.v("testing click", it)
                }
                it.seeAllClickListener = {
                    vm.handleSeeAllCashBackClicked()
                }
                it.dividerItemDecoration = VerticalDividerDecoration(requireContext(), RecyclerView.VERTICAL)
            },
            HomeSection(
                "Informasi",
                LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false),
                listOf(newsItem)
            ).also {
                it.itemClickListener = {
                    Log.v("testing click", it)
                }
                it.seeAllClickListener = {
                    vm.handleSeeAllNewsClicked()
                }
                it.snapHelper = LinearSnapHelper()
                it.dividerItemDecoration = HorizontalSpaceDecoration(resources.getDimensionPixelSize(
                    R.dimen.space_16dp))
            }
        ))
        binding?.rvContent?.run {
            layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
            addItemDecoration(
                VerticalDividerDecoration(requireContext(), RecyclerView.VERTICAL).also {
                    ContextCompat.getDrawable(requireContext(), R.drawable.normal_divider)?.let { divider ->
                        it.setDrawable(divider)
                    }
                }
            )
            adapter = FastAdapter.with(homeSection)
        }
    }

    override fun onDetach() {
        binding = null
        super.onDetach()
    }

    private fun showCashBackListScreen(event: Event<String>?) {
        event?.getIfNotHandled()?.let {
            navigator.showCashBackList(requireContext())
        }
    }

    private fun showNewsListScreen(event: Event<String>?) {
        event?.getIfNotHandled()?.let {
            navigator.showNewsList(requireContext())
        }
    }

    companion object {
        fun newInstance(): HomeFragment {
            return HomeFragment()
        }
    }
}
