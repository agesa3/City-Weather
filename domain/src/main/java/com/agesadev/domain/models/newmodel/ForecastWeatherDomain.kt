package com.agesadev.domain.models.newmodel

data class ForecastWeatherDomain(
    val city: CityDomain,
    val cnt: Int,
    val cod: String,
    val list: List<CityWeatherDomain>,
    val message: Int
)





