package com.agesadev.weathercell.model

data class CityWeatherPresentation(
    val clouds: CloudsPresentation,
    val dt: Int,
    val dt_txt: String,
    val main: MainPresentation,
    val pop: Double,
    val visibility: Int,
    val wind: WindPresentation
)