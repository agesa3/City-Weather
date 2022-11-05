package com.agesadev.weathercell.mappers

import com.agesadev.domain.models.WeatherDomain
import com.agesadev.weathercell.model.WeatherPresentation

fun WeatherDomain.toWeatherPresentation(): WeatherPresentation {
    return WeatherPresentation(
        visibility = visibility,

        dt = dt,

        timezone = timezone,
        id = id,
        name = name,
        cod = cod,
    )

}