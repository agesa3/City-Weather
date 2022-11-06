package com.agesadev.data.remote.models.dtos

import com.agesadev.data.remote.models.*

data class WeatherForecastDto(
    val clouds: Clouds?,
    val dt: Int?,
    val dt_txt: String?,
    val main: Main?,
    val wind: Wind?
)