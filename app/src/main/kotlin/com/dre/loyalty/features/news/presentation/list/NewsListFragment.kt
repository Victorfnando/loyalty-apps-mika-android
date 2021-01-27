/*
 * Created by Andreas Oen on 1/3/21 4:14 PM
 * Copyright (c) 2021 . All rights reserved.
 * Last modified 1/1/21 9:43 AM
 * github: https://github.com/oandrz
 */

package com.dre.loyalty.features.news.presentation.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dre.loyalty.R
import com.dre.loyalty.core.exception.Failure
import com.dre.loyalty.core.extension.observe
import com.dre.loyalty.core.extension.viewModel
import com.dre.loyalty.core.functional.Event
import com.dre.loyalty.core.interactor.UseCase
import com.dre.loyalty.core.model.News
import com.dre.loyalty.core.navigation.Navigator
import com.dre.loyalty.core.platform.BaseFragment
import com.dre.loyalty.core.view.VerticalSpaceDecoration
import com.dre.loyalty.core.view.sheet.ConfirmationSheetModal
import com.dre.loyalty.databinding.FragmentNewsListBinding
import com.dre.loyalty.features.news.presentation.view.VerticalNewsItem
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.adapters.ItemAdapter
import javax.inject.Inject

class NewsListFragment : BaseFragment() {

    @Inject
    lateinit var navigator: Navigator

    private var binding: FragmentNewsListBinding? = null

    private lateinit var vm: NewsListViewModel

    private val newsItem: ItemAdapter<VerticalNewsItem> by lazy {
        ItemAdapter<VerticalNewsItem>()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent.inject(this)
        vm = viewModel(viewModelFactory) {
            observe(newsItemClicked, ::showNewsDetail)
            observe(newsList, ::renderNewsList)
            observe(loading, ::renderLoading)
            observe(failure, ::showFailureSheet)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNewsListBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindToolbar()
        bindList()
        vm.loadData()
    }

    override fun onDetach() {
        (activity as AppCompatActivity).setSupportActionBar(null)
        binding = null
        super.onDetach()
    }

    private fun bindToolbar() {
        val showNavigationBack: Boolean = arguments?.getBoolean(SHOW_NAVIGATION_BACK_ARGS) ?: false
        (activity as AppCompatActivity).run {
            setSupportActionBar(binding?.toolbarLayout?.toolbar)
            supportActionBar?.title = resources.getText(R.string.newslist_screen_title)
            supportActionBar?.setDisplayHomeAsUpEnabled(showNavigationBack)
        }
    }

    private fun bindList() {
        binding?.rvNews?.run {
            layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
            addItemDecoration(VerticalSpaceDecoration(resources.getDimensionPixelSize(R.dimen.space_16dp)))
            adapter = FastAdapter.with(newsItem).also {
                it.onClickListener = { _, _, item, _ ->
                    vm.handleNewsItemClicked(item.item.id)
                    true
                }
            }
        }
    }

    private fun showNewsDetail(event: Event<String>?) {
        event?.getIfNotHandled()?.let {
            navigator.showNewsDetail(requireContext())
        }
    }

    private fun renderNewsList(list: List<News>?) {
        newsItem.add(list?.map { VerticalNewsItem(it) } ?: emptyList())
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

        private const val SHOW_NAVIGATION_BACK_ARGS = "SHOW_NAVIGATION_BACK_ARGS"

        fun newInstance(showNavigationBack: Boolean): NewsListFragment {
            return NewsListFragment().also {
                val bundle = Bundle()
                bundle.putBoolean(SHOW_NAVIGATION_BACK_ARGS, showNavigationBack)
                it.arguments = bundle
            }
        }
    }
}
