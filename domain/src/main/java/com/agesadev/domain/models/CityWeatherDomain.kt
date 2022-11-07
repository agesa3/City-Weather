package com.agesadev.domain.models

data class CityWeatherDomain(
    val clouds: CloudsDomain,
    val dt: Int,
    val dt_txt: String,
    val main: MainDomain,
    val pop: Double,
    val visibility: Int,
    val wind: WindDomain
)