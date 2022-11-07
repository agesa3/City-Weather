package com.agesadev.weathercell.model

data class CityPresentation(
    val id: Int,
    val name: String,
    val country: String,
    val coord: CoordPresentation,
    val sunrise: Int,
    val sunset: Int
)