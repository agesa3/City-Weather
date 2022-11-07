package com.agesadev.domain.models

data class ForecastWeatherDomain(
    val city: CityDomain,
    val cnt: Int,
    val cod: String,
    val list: List<CityWeatherDomain>,
    val message: Int
)





