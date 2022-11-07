package com.agesadev.weathercell.days.uistate

import com.agesadev.weathercell.model.CityWeatherPresentation

data class WeatherDetailState(
    val isLoading: Boolean = false,
    val data: List<CityWeatherPresentation>? = null,
    val error: String? = null
)