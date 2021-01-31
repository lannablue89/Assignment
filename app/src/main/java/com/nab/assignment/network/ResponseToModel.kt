package com.nab.assignment.network

import com.nab.assignment.model.Weather

/**
 * Created by Lan Tran on 1/31/21.
 */

fun WeatherResponse.ForecastResponse.toWeatherModel() = Weather(
    datetime = datetime,
    averageTemperature = (temperature.min + temperature.max) / 2,
    pressure = pressure,
    humidity = humidity,
    weatherDescription = weathers?.firstOrNull()?.description ?: "",
)