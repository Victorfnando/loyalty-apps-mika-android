package com.dre.loyalty.features.news.presentation.list

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dre.loyalty.core.functional.Event
import com.dre.loyalty.core.model.News
import com.dre.loyalty.core.platform.BaseViewModel
import com.dre.loyalty.features.news.domain.usecase.GetNewsListUseCase
import com.dre.loyalty.features.news.domain.usecase.GetNewsListUseCase.Param
import javax.inject.Inject

class NewsListViewModel @Inject constructor(
    private val getNewsListUseCase: GetNewsListUseCase
) : BaseViewModel() {

    private val _newsList: MutableLiveData<List<News>> = MutableLiveData()
    val newsList: LiveData<List<News>> = _newsList

    private val _newsItemClicked: MutableLiveData<Event<String>> = MutableLiveData()
    val newsItemClicked: LiveData<Event<String>> = _newsItemClicked

    fun init() {
        _loading.value = View.VISIBLE
        getNewsListUseCase(Param("test", "test")) {
            it.fold(::handleFailure, ::handleSuccessGetNews)
        }
    }

    fun handleNewsItemClicked(selectedId: String) {
        _newsItemClicked.value = Event(selectedId)
    }

    private fun handleSuccessGetNews(news: List<News>) {
        _loading.value = View.GONE
        _newsList.value = news
    }
}