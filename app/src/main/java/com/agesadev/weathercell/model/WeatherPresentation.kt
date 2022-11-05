package com.agesadev.weathercell.model


data class WeatherPresentation(
    val visibility: Int,
    val dt: Int,
    val timezone: Int,
    val id: Int,
    val name: String,
    val cod: Int
)
