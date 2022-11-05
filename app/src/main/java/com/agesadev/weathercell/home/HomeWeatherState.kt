package com.agesadev.weathercell.home

import com.agesadev.weathercell.model.WeatherPresentation

data class HomeWeatherState(
    val isLoading: Boolean = false,
    val data: WeatherPresentation? = null,
    val error: String? = null
)