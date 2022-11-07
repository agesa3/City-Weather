package com.agesadev.data.remote.model

import com.agesadev.data.local.model.WeatherForecastEntity
import com.agesadev.data.mappers.toCityDomain
import com.agesadev.domain.models.newmodel.ForecastWeatherDomain

data class ForecastWeatherApiResponse(
    val city: City,
    val cnt: Int,
    val cod: String,
    val list: List<CityWeatherDto>,
    val message: Int
)

fun ForecastWeatherApiResponse.toForecastWeatherDomain(): ForecastWeatherDomain {
    return ForecastWeatherDomain(
        city = city.toCityDomain(),
        cnt = cnt,
        cod = cod,
        list = list.map { it.toDomain() },
        message = message
    )
}

fun ForecastWeatherApiResponse.toWeatherForecastEntity(): WeatherForecastEntity {
    return WeatherForecastEntity(
        id=0,
        city = city,
        cnt = cnt,
        cod = cod,
        list =list,
        message = message
    )
}


