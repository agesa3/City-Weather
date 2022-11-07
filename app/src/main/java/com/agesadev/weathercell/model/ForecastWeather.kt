package com.agesadev.weathercell.model

import com.agesadev.domain.models.newmodel.ForecastWeatherDomain
import com.agesadev.weathercell.mappers.toCityPresentation
import com.agesadev.weathercell.mappers.toCityWeatherPresentation

data class ForecastWeather(
    val city: CityPresentation,
    val cnt: Int,
    val cod: String,
    val list: List<CityWeatherPresentation>,
    val message: Int
)


fun ForecastWeatherDomain.toForecastWeather(): ForecastWeather {
    return ForecastWeather(
        city = city.toCityPresentation(),
        cnt = cnt,
        cod = cod,
        list = list.map { it.toCityWeatherPresentation() },
        message = message

    )
}



