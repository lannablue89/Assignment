package com.nab.assignment.network

import com.nab.assignment.BuildConfig
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Lan Tran on 1/31/21.
 */

interface WeatherService {

    @GET("data/2.5/forecast/daily")
    suspend fun getWeatherInfo(
        @Query("q") searchKey: String = "saigon",
        @Query("cnt") days: Int = 7,
        @Query("appid") apiKey: String = BuildConfig.API_KEY,
    ) : WeatherResponse
}