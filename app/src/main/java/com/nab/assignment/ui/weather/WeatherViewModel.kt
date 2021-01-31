package com.nab.assignment.ui.weather

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.shareIn
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Lan Tran on 1/31/21.
 */

class WeatherViewModel @Inject constructor(
    private val weatherRepository: WeatherRepository
) : ViewModel() {

    private val _fetchFlow = MutableSharedFlow<Unit>(replay = 1)

    @ExperimentalCoroutinesApi
    val data = _fetchFlow
        .flatMapLatest { weatherRepository.getWeatherForecast() }
        .shareIn(viewModelScope, SharingStarted.Lazily, 1)
        .asLiveData()

    fun fetchData() {
        viewModelScope.launch { _fetchFlow.emit(Unit) }
    }

}