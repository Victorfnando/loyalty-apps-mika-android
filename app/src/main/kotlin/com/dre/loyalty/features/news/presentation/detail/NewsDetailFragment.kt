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
import com.dre.loyalty.R
import com.dre.loyalty.core.platform.BaseFragment
import com.dre.loyalty.core.view.VerticalSpaceDecoration
import com.dre.loyalty.databinding.FragmentNewsDetailBinding
import com.dre.loyalty.features.news.presentation.entity.News
import com.dre.loyalty.features.news.presentation.view.VerticalNewsItem
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.adapters.ItemAdapter

class NewsDetailFragment : BaseFragment() {

    private var binding: FragmentNewsDetailBinding? = null

    private val newsItem: ItemAdapter<VerticalNewsItem> by lazy {
        ItemAdapter<VerticalNewsItem>()
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
    }

    override fun onDetach() {
        binding = null
        super.onDetach()
    }

    private fun bindToolbar() {
        (activity as AppCompatActivity).run {
            setSupportActionBar(binding?.toolbarLayout?.toolbar)
            supportActionBar?.setDisplayShowTitleEnabled(false)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
    }

    private fun bindList() {
        newsItem.add(
            listOf(
                VerticalNewsItem(News("12 Desember 2020", "Rumah Imunisasi Mitra Keluarga Bekasi", "Rumah Imunisasi Mitra Keluarga Bekasi Rumah Imunisasi Mitra Keluarga Bekasi", "")),
                VerticalNewsItem(News("20 Desember 2020", "Rumah Imunisasi Mitra Keluarga Jakarta", "Rumah Imunisasi Mitra Keluarga Bekasi Rumah Imunisasi Mitra Keluarga Bekasi", "")),
                VerticalNewsItem(News("12 Desember 2020", "Rumah Imunisasi Mitra Keluarga Bekasi", "Rumah Imunisasi Mitra Keluarga Bekasi Rumah Imunisasi Mitra Keluarga Bekasi", "")),
                VerticalNewsItem(News("20 Desember 2020", "Rumah Imunisasi Mitra Keluarga Jakarta", "Rumah Imunisasi Mitra Keluarga Bekasi Rumah Imunisasi Mitra Keluarga Bekasi", ""))
            )
        )
        binding?.rvRelatedNews?.run {
            layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
            addItemDecoration(
                VerticalSpaceDecoration(resources.getDimensionPixelSize(R.dimen.space_16dp))
            )
            isNestedScrollingEnabled = false
            adapter = FastAdapter.with(newsItem)
        }
    }

    companion object {
        fun newInstance(): NewsDetailFragment {
            return NewsDetailFragment()
        }
    }
}
