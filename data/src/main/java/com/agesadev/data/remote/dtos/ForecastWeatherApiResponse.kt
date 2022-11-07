package com.agesadev.data.remote.dtos

data class ForecastWeatherApiResponse(
    val city: City,
    val cnt: Int,
    val cod: String,
    val list: List<CityWeatherDto>,
    val message: Int
)




