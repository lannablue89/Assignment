package com.nab.assignment.network

import com.google.gson.annotations.SerializedName

/**
 * Created by Lan Tran on 1/31/21.
 */

data class WeatherResponse(
    @SerializedName("cod")
    val code: Int,
    @SerializedName("message")
    val errorMessage: String,
    @SerializedName("list")
    val list: List<ForecastResponse> = emptyList(),
) {

    data class ForecastResponse(
        @SerializedName("dt")
        val datetime: Long,
        @SerializedName("temp")
        val temperature: TemperatureResponse,
        @SerializedName("pressure")
        val pressure: Int,
        @SerializedName("humidity")
        val humidity: Int,
        @SerializedName("weather")
        val weathers: List<WeatherDescriptionResponse>? = emptyList(),
    )

    data class WeatherDescriptionResponse(
        @SerializedName("description")
        val description: String,
    )

    data class TemperatureResponse(
        @SerializedName("min")
        val min: Float,
        @SerializedName("max")
        val max: Float,
    )
}
