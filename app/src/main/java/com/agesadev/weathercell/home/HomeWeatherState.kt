package com.agesadev.weathercell.home

import com.agesadev.weathercell.model.ForecastWeather
import com.agesadev.weathercell.model.WeatherPresentation

data class HomeWeatherState(
    val isLoading: Boolean = false,
    val data: ForecastWeather? = null,
    val error: String? = null
)