package com.nab.assignment.ui.weather

import com.nab.assignment.model.Resource
import com.nab.assignment.model.Weather
import com.nab.assignment.network.WeatherService
import com.nab.assignment.network.toWeatherModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * Created by Lan Tran on 1/31/21.
 */

class WeatherRepository@Inject constructor(
    private val apiService: WeatherService,
) {

    fun getWeatherForecast(searchKey: String) : Flow<Resource<List<Weather>>> = flow {
        runCatching {

            emit(Resource.loading(null))

            val weatherResponse = apiService.getWeatherInfo(searchKey)

            if (weatherResponse.code == 200) {
                val results = weatherResponse.list.map { weather -> weather.toWeatherModel() }
                emit(Resource.success(results))
            } else {
                emit(Resource.error(Exception(weatherResponse.errorMessage), null))
            }

        }.onFailure {
            emit(Resource.error(it, null))
        }
    }

}