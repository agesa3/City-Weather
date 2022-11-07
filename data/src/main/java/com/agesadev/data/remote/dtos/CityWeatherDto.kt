package com.agesadev.data.remote.dtos

data class CityWeatherDto(
    val clouds: Clouds,
    val dt: Int,
    val dt_txt: String,
    val main: Main,
    val pop: Double,
    val rain: Rain,
    val sys: Sys,
    val visibility: Int,
    val wind: Wind
)

