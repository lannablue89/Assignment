package com.nab.assignment.ui.weather

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Lan Tran on 1/31/21.
 */

class WeatherViewModel @Inject constructor(
    private val weatherRepository: WeatherRepository
) : ViewModel() {

    private val _fetchFlow = MutableSharedFlow<Unit>(replay = 1)
    private val _searchFlow = MutableSharedFlow<String>(replay = 1)

    @ExperimentalCoroutinesApi
    val data = combine(_fetchFlow, _searchFlow.filter { it.length > 3 }) { _, searchText -> searchText }
        .flatMapLatest { searchText -> weatherRepository.getWeatherForecast(searchText) }
        .shareIn(viewModelScope, SharingStarted.Lazily, 1)
        .asLiveData()

    fun fetchSearchData(searchText: String) {
        viewModelScope.launch { _searchFlow.emit(searchText) }
    }

    fun fetchData() {
        viewModelScope.launch { _fetchFlow.emit(Unit) }
    }

}