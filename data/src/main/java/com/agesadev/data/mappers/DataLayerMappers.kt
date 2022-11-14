package com.agesadev.data.mappers

import com.agesadev.data.local.model.WeatherForecastEntity
import com.agesadev.data.remote.dtos.*
import com.agesadev.domain.models.*


fun Coord.toCoordDomain(): CoordDomain {
    return CoordDomain(
        lon = lon,
        lat = lat
    )
}

fun Weather.toWeatherDomain(): WeatherDomain {
    return WeatherDomain(
        description = description,
        icon = icon,
        id = id,
        main = main
    )
}


fun Main.toMainDomain(): MainDomain {
    return MainDomain(
        temp = temp,
        feels_like = feels_like,
        temp_min = temp_min,
        temp_max = temp_max,
        pressure = pressure,
        humidity = humidity
    )
}


fun Wind.toWindDomain(): WindDomain {
    return WindDomain(
        speed = speed ?: 0.0,
        deg = deg ?: 0
    )
}

fun Clouds.toCloudsDomain(): CloudsDomain {
    return CloudsDomain(
        all = all ?: 0
    )
}


fun City.toCityDomain(): CityDomain {
    return CityDomain(
        coord = coord?.toCoordDomain() ?: CoordDomain(0.0, 0.0),
        country = country,
        id = id,
        name = name,
        population = population,
        sunrise = sunrise,
        sunset = sunset,
        timezone = timezone,
    )
}


fun CityWeatherDto.toDomain(): CityWeatherDomain {
    return CityWeatherDomain(
        clouds = clouds.toCloudsDomain(),
        dt = dt,
        dt_txt = dt_txt,
        main = main.toMainDomain(),
        pop = pop,
        visibility = visibility,
        wind = wind.toWindDomain(),
        weather = weather.map { it.toWeatherDomain() },
    )
}


fun ForecastWeatherApiResponse.toWeatherForecastEntity(): WeatherForecastEntity {
    return WeatherForecastEntity(
        city = city,
        cnt = cnt,
        cod = cod,
        list = list,
        message = message
    )
}