package com.agesadev.data.remote.models.dtos

data class ForecaseWeatherApiResponse(
    val city: City?,
    val cnt: Int?,
    val cod: String?,
    val list: List<WeatherForecastDto>?,
    val message: Int?
)