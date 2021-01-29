/*
 * Created by Andreas Oen on 1/3/21 9:35 PM
 * Copyright (c) 2021 . All rights reserved.
 * Last modified 1/3/21 9:35 PM
 * github: https://github.com/oandrz
 */

package com.dre.loyalty.features.news.presentation.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.load.model.LazyHeaders
import com.dre.loyalty.R
import com.dre.loyalty.core.platform.extension.observe
import com.dre.loyalty.core.platform.extension.viewModel
import com.dre.loyalty.core.model.News
import com.dre.loyalty.core.platform.BaseFragment
import com.dre.loyalty.core.view.VerticalSpaceDecoration
import com.dre.loyalty.databinding.FragmentNewsDetailBinding
import com.dre.loyalty.features.news.presentation.view.VerticalNewsItem
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.adapters.ItemAdapter

class NewsDetailFragment : BaseFragment() {

    private var binding: FragmentNewsDetailBinding? = null

    private lateinit var vm: NewsDetailViewModel

    private val newsItem: ItemAdapter<VerticalNewsItem> by lazy {
        ItemAdapter<VerticalNewsItem>()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent.inject(this)
        vm = viewModel(viewModelFactory) {
            observe(detail, ::renderDetail)
            observe(loading, ::renderLoading)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNewsDetailBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindToolbar()
        bindList()
        vm.init()
    }

    override fun onDestroyView() {
        Glide.get(requireContext()).clearMemory()
        binding = null
        super.onDestroyView()
    }

    private fun bindToolbar() {
        (activity as AppCompatActivity).run {
            setSupportActionBar(binding?.toolbarLayout?.toolbar)
            supportActionBar?.setDisplayShowTitleEnabled(false)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
    }

    private fun bindList() {
        binding?.rvRelatedNews?.run {
            layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
            addItemDecoration(
                VerticalSpaceDecoration(resources.getDimensionPixelSize(R.dimen.space_16dp))
            )
            isNestedScrollingEnabled = false
            adapter = FastAdapter.with(newsItem)
        }
    }

    private fun renderDetail(detail: News?) {
        if (detail == null) return
        renderBanner(detail)
        renderDescription(detail)
        renderRelatedNews(detail)
    }

    private fun renderRelatedNews(detail: News) {
        newsItem.add(
            detail.relatedNews?.map { VerticalNewsItem(it) } ?: emptyList()
        )
    }

    private fun renderDescription(detail: News) {
        binding?.run {
            tvNewsTitle.text = detail.title
            tvNewsDate.text = detail.date
            tvNewsDesc.text = detail.desc
        }
    }

    private fun renderBanner(detail: News) {
        val url = GlideUrl(
            detail.imageUrl,
            LazyHeaders.Builder()
                .addHeader("User-Agent", "appllication")
                .build()
        )
        binding?.ivBanner?.let {
            Glide.with(requireContext())
                .load(url)
                .into(it)
        }
    }

    private fun renderLoading(visibility: Int?) {
        binding?.progress?.visibility = visibility ?: View.GONE
    }

    companion object {
        fun newInstance(): NewsDetailFragment {
            return NewsDetailFragment()
        }
    }
}
