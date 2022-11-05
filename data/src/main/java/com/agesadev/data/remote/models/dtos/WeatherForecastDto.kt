package com.agesadev.data.remote.models.dtos

import com.agesadev.data.remote.models.*

data class WeatherForecastDto(
    val clouds: Clouds,
    val dt: Int,
    val dt_txt: String,
    val main: Main,
    val pop: Double,
    val sys: Sys,
    val visibility: Int,
    val weather: List<Weather>,
    val wind: Wind
)