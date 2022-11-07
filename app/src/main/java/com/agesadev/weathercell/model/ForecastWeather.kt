package com.agesadev.weathercell.model



data class ForecastWeather(
    val city: CityPresentation,
    val cnt: Int,
    val cod: String,
    val list: List<CityWeatherPresentation>,
    val message: Int
)






