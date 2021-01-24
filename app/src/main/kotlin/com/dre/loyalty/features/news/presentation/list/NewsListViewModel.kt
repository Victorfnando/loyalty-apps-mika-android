package com.dre.loyalty.features.news.presentation.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dre.loyalty.core.functional.Event
import com.dre.loyalty.core.platform.BaseViewModel
import javax.inject.Inject

class NewsListViewModel @Inject constructor() : BaseViewModel() {

    private val _newsItemClicked: MutableLiveData<Event<String>> = MutableLiveData()
    val newsItemClicked: LiveData<Event<String>> = _newsItemClicked

    fun handleNewsItemClicked(selectedId: String) {
        _newsItemClicked.value = Event(selectedId)
    }
}