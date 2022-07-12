package com.nathan.instant_news.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nathan.instant_news.data.model.News
import com.nathan.instant_news.data.repository.MainRepository
import com.nathan.instant_news.utils.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel class responsible to get the News data from the API
 */
@HiltViewModel
class MainViewModel
@Inject
constructor (private val mainRepository: MainRepository): ViewModel() {

    private val _dataState: MutableLiveData<DataState<List<News>>> = MutableLiveData()

    val dataState: LiveData<DataState<List<News>>>
        get() = _dataState

    /**
     * Observe MainStateEvents
     */
    fun setStateEvent(mainStateEvent: MainStateEvent) {
        viewModelScope.launch {
            when (mainStateEvent) {
                is MainStateEvent.GetNewsEvents -> {
                    /**
                     * Event to get the News list
                     */
                    mainRepository.getNews()
                        .onEach { dataState ->
                            _dataState.value = dataState
                        }
                        .launchIn(viewModelScope)
                }
            }
        }
    }

}

/**
 * Class representing all the possible events handled by the ViewModel
 */
sealed class MainStateEvent {
    object GetNewsEvents : MainStateEvent()
}