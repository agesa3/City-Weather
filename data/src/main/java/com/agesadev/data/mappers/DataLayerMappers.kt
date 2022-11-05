package com.agesadev.data.mappers

import com.agesadev.data.remote.models.*
import com.agesadev.domain.models.*


fun WeatherApiResponse.toWeatherDomain(): WeatherDomain {
    return WeatherDomain(
        coord = coord.toCoordDomain(),
        weather = weather.map { it.toWeatherDomainLayer() },
        main = main.toMainDomain(),
        visibility = visibility,
        wind = wind.toWindDomain(),
        clouds = clouds.toCloudsDomain(),
        dt = dt,
        sys = sys.toSysDomain(),
        timezone = timezone,
        id = id,
        name = name,
        cod = cod
    )
}

fun Coord.toCoordDomain(): CoordDomain {
    return CoordDomain(
        lon = lon,
        lat = lat
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


fun Weather.toWeatherDomainLayer(): WeatherDomainLayer {
    return WeatherDomainLayer(
        description, icon, id, main

    )
}

fun Wind.toWindDomain(): WindDomain {
    return WindDomain(
        speed = speed,
        deg = deg
    )
}

fun Clouds.toCloudsDomain(): CloudsDomain {
    return CloudsDomain(
        all = all
    )
}

fun Sys.toSysDomain(): SysDomain {
    return SysDomain(
        country = country,
        sunrise = sunrise,
        sunset = sunset,
    )
}