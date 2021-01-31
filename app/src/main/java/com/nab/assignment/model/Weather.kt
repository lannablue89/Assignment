package com.nab.assignment.model

/**
 * Created by Lan Tran on 1/31/21.
 */

class Weather(
    val datetime: Long,
    val averageTemperature: Float,
    val pressure: Int,
    val humidity: Int,
    val weatherDescription: String,
)