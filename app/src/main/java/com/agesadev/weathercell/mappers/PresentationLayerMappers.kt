package com.agesadev.weathercell.mappers

import com.agesadev.domain.models.*
import com.agesadev.domain.models.CityDomain
import com.agesadev.domain.models.CityWeatherDomain
import com.agesadev.domain.models.ForecastWeatherDomain
import com.agesadev.weathercell.model.*


fun CoordDomain.toCoordPresentation(): CoordPresentation {
    return CoordPresentation(
        lon = lon,
        lat = lat
    )
}

fun WeatherDomain.toWeatherPresentation(): WeatherPresentation {
    return WeatherPresentation(
        description = description,
        icon = icon,
        id = id,
        main = main
    )
}


fun MainDomain.toMainPresentation(): MainPresentation {
    return MainPresentation(
        temp = temp,
        feels_like = feels_like,
        temp_min = temp_min,
        temp_max = temp_max,
        pressure = pressure,
        humidity = humidity
    )
}


fun WindDomain.toWindPresentation(): WindPresentation {
    return WindPresentation(
        speed = speed,
        deg = deg
    )
}

fun CloudsDomain.toCloudsPresentation(): CloudsPresentation {
    return CloudsPresentation(
        all = all
    )
}


fun CityDomain.toCityPresentation(): CityPresentation {
    return CityPresentation(
        coord = coord.toCoordPresentation(),
        id = id,
        name = name,
        country = country,
        sunrise = sunrise,
        sunset = sunset

    )
}

fun CityWeatherDomain.toCityWeatherPresentation(): CityWeatherPresentation {
    return CityWeatherPresentation(
        clouds = clouds.toCloudsPresentation(),
        dt = dt,
        dt_txt = dt_txt,
        main = main.toMainPresentation(),
        pop = pop,
        visibility = visibility,
        wind = wind.toWindPresentation(),
        weather = weather.map { it.toWeatherPresentation() }

    )
}


fun ForecastWeatherDomain.toForecastWeather(): ForecastWeather {
    return ForecastWeather(
        city = city.toCityPresentation(),
        cnt = cnt,
        cod = cod,
        list = list.map { it.toCityWeatherPresentation() },
        message = message

    )
}
