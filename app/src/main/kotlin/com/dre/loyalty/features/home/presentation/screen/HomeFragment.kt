/*
 * Created by Andreas Oen on 12/30/20 8:15 PM
 * Copyright (c) 2020 . All rights reserved.
 * Last modified 12/30/20 8:15 PM
 * github: https://github.com/oandrz
 */

package com.dre.loyalty.features.home.presentation.screen

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.dre.loyalty.R
import com.dre.loyalty.core.networking.exception.Failure
import com.dre.loyalty.core.platform.extension.observe
import com.dre.loyalty.core.platform.extension.viewModel
import com.dre.loyalty.core.platform.functional.Event
import com.dre.loyalty.core.model.Card
import com.dre.loyalty.core.model.CashBack
import com.dre.loyalty.core.model.News
import com.dre.loyalty.core.platform.navigation.Navigator
import com.dre.loyalty.core.platform.BaseFragment
import com.dre.loyalty.core.view.HorizontalSpaceDecoration
import com.dre.loyalty.core.view.VerticalDividerDecoration
import com.dre.loyalty.core.view.sheet.ConfirmationSheetModal
import com.dre.loyalty.databinding.FragmentHomeBinding
import com.dre.loyalty.features.camera.CameraActivity
import com.dre.loyalty.features.camera.CameraRequestType
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
            observe(navigateNewsDetail, ::showNewsDetailScreen)
            observe(navigateToCamera, ::showCamera)
            observe(navigateInvoiceDetail, ::showInvoiceDetail)
            observe(cardState, ::updateCard)
            observe(cashBackSection, ::updateCashBackSection)
            observe(newsSection, ::updateNewsSection)
            observe(loading, ::renderLoading)
            observe(failure, ::showFailureSheet)
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
        vm.loadData()
        bindList()
    }

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK && requestCode == CameraActivity.REQUEST_CODE_CAMERA) {
            val uri = data?.getStringExtra(CameraActivity.EXTRA_URI)
            uri?.let {
                navigator.showUploadInvoice(requireContext(), Uri.parse(it))
            }
        }
    }

    private fun bindList() {
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

    private fun showNewsDetailScreen(event: Event<String>?) {
        event?.getIfNotHandled()?.let {
            navigator.showNewsDetail(requireContext(), it)
        }
    }

    private fun showCamera(event: Event<String>?) {
        event?.getIfNotHandled()?.let {
            startActivityForResult(
                CameraActivity.callingIntent(requireContext(), CameraRequestType.CAMERA),
                CameraActivity.REQUEST_CODE_CAMERA
            )
        }
    }

    private fun showInvoiceDetail(event: Event<String>?) {
        event?.getIfNotHandled()?.let {
            navigator.showInvoiceDetail(requireContext(), it)
        }
    }

    private fun updateCard(state: Card?) {
        state?.let {
            binding?.cardMedicalNumber?.name = state.name
            if(state.memberSince.isNullOrEmpty()){
                binding?.cardMedicalNumber?.medicalNumber = "-"
            } else {
                binding?.cardMedicalNumber?.medicalNumber = state.memberSince
            }
        }
    }

    private fun updateCashBackSection(cashBackList: List<CashBack>?) {
        addCashBackSectionIfNeeded()
        if (cashBackList != null && cashBackList.isNotEmpty()) {
            if (uploadInvoiceItem.adapterItemCount > 0) {
                uploadInvoiceItem.remove(0)
            }
            cashBackItem.add(
                cashBackList.map { CashBackItem(it) }
            )
        } else {
            uploadInvoiceItem.add(
                UploadInvoiceItem {
                    vm.handleUploadInvoiceClicked()
                }
            )
        }
    }

    private fun addCashBackSectionIfNeeded() {
        val section = homeSection.adapterItems.firstOrNull { it.categoryName == getString(R.string.home_label_section_cashBack) }
        if (section != null) return
        homeSection.add(
            HomeSection(
                getString(R.string.home_label_section_cashBack),
                LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false),
                listOf(cashBackItem, uploadInvoiceItem)
            ).also {
                it.itemClickListener = { selectedItem ->
                    vm.handleCashBackItemClicked(selectedItem)
                }
                it.seeAllClickListener = {
                    vm.handleSeeAllCashBackClicked()
                }
                it.dividerItemDecoration = VerticalDividerDecoration(requireContext(), RecyclerView.VERTICAL)
            }
        )
    }

    private fun updateNewsSection(newsList: List<News>?) {
        addNewsSectionIfNeeded()
        newsItem.add(
            newsList?.map { NewsItem(it) } ?: emptyList()
        )
    }

    private fun addNewsSectionIfNeeded() {
        val section = homeSection.adapterItems.firstOrNull { it.categoryName == getString(R.string.home_label_section_information) }
        if (section != null) return
        homeSection.add(
            HomeSection(
                getString(R.string.home_label_section_information),
                LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false),
                listOf(newsItem)
            ).also {
                it.itemClickListener = { selectedItem ->
                    vm.handleNewsItemClicked(selectedItem)
                }
                it.seeAllClickListener = {
                    vm.handleSeeAllNewsClicked()
                }
                it.snapHelper = LinearSnapHelper()
                it.dividerItemDecoration = HorizontalSpaceDecoration(resources.getDimensionPixelSize(
                    R.dimen.space_16dp))
            }
        )
    }

    private fun renderLoading(visibility: Int?) {
        binding?.progress?.visibility = visibility ?: View.GONE
    }

    private fun showFailureSheet(failure: Failure?) {
        if (failure == null) return
        val sheet = getNetworkErrorSheet(failure)?.also {
            it.primaryButtonClickListener = {
                vm.loadData()
            }
            it.secondaryButtonClickListener = {
                navigator.showSetting(requireContext())
            }
        }
        sheet?.show(requireActivity().supportFragmentManager, ConfirmationSheetModal.TAG)
    }

    companion object {
        fun newInstance(): HomeFragment {
            return HomeFragment()
        }
    }
}
