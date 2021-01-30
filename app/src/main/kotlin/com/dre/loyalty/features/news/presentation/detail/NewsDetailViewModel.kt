/*
 *
 * Created by Andreas on 1/26/21 7:39 PM
 * Copyright (c) 2021 . All rights reserved.
 * Last modified 1/26/21 7:39 PM
 * Github Link: https://github.com/oandrz
 * Email: oentoro.andreas@gmail.com
 *
 */

package com.dre.loyalty.features.news.presentation.detail

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dre.loyalty.core.model.News
import com.dre.loyalty.core.platform.BaseViewModel
import com.dre.loyalty.core.platform.functional.Event
import com.dre.loyalty.features.news.domain.usecase.GetNewsDetailUseCase
import javax.inject.Inject

class NewsDetailViewModel @Inject constructor(
    private val getNewsDetailUseCase: GetNewsDetailUseCase
) : BaseViewModel() {

    private val _detail: MutableLiveData<News> = MutableLiveData()
    val detail: LiveData<News> = _detail

    private val _navigateNewsDetail: MutableLiveData<Event<String>> = MutableLiveData()
    val navigateNewsDetail: LiveData<Event<String>> = _navigateNewsDetail

    private lateinit var id: String

    fun init(id: String) {
        this.id = id
        refresh()
    }

    fun refresh() {
        _loading.value = View.VISIBLE
        getNewsDetailUseCase(id) {
            it.fold(::handleFailure, ::handleDetailSuccess)
        }
    }

    fun handleRelatedNewsClicked(id: String) {
        _navigateNewsDetail.value = Event(id)
    }

    private fun handleDetailSuccess(detail: News) {
        _loading.value = View.GONE
        _detail.value = detail
    }
}