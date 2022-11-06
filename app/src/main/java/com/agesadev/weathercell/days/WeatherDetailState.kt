package com.agesadev.weathercell.days

import com.agesadev.weathercell.model.WeatherForecastPresentation

data class WeatherDetailState(
    val isLoading: Boolean = false,
    val data: WeatherForecastPresentation? = null,
    val error: String? = null
)