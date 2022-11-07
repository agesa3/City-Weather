package com.agesadev.data.remote.model

import com.agesadev.data.mappers.toCloudsDomain
import com.agesadev.data.mappers.toMainDomain
import com.agesadev.data.mappers.toWindDomain
import com.agesadev.domain.models.newmodel.CityWeatherDomain

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

fun CityWeatherDto.toDomain(): CityWeatherDomain {
    return CityWeatherDomain(
        clouds = clouds.toCloudsDomain(),
        dt = dt,
        dt_txt = dt_txt,
        main = main.toMainDomain(),
        pop = pop,
        visibility = visibility,
        wind = wind.toWindDomain()
    )
}