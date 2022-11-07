package com.agesadev.weathercell.home

import com.agesadev.weathercell.model.ForecastWeather

data class HomeWeatherState(
    val isLoading: Boolean = false,
    val data: ForecastWeather? = null,
    val error: String? = null
)