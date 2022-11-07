package com.agesadev.weathercell.mappers

import com.agesadev.domain.models.*
import com.agesadev.domain.models.newmodel.CityDomain
import com.agesadev.domain.models.moredays.ForecastDomain
import com.agesadev.domain.models.moredays.WeatherForecastDomain
import com.agesadev.domain.models.newmodel.CityWeatherDomain
import com.agesadev.weathercell.model.*

fun WeatherDomain.toWeatherPresentation(): WeatherPresentation {
    return WeatherPresentation(
        coord = coord.toCoordPresentation(),
        weather = weather.map { it.toWeatherData() },
        main = main.toMainPresentation(),
        visibility = visibility,
        wind = wind.toWindPresentation(),
        clouds = clouds.toCloudsPresentation(),
        dt = dt,
        sys = sys.toSysPresentation(),
        timezone = timezone,
        id = id,
        name = name,
        cod = cod,
    )

}


fun CoordDomain.toCoordPresentation(): CoordPresentation {
    return CoordPresentation(
        lon = lon,
        lat = lat
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


fun WeatherDomainLayer.toWeatherData(): WeatherData {
    return WeatherData(
        description, icon, id, main

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

fun SysDomain.toSysPresentation(): SysPresentation {
    return SysPresentation(
        country = country,
        sunrise = sunrise,
        sunset = sunset,
    )
}

fun CityDomain.toCityPresentation(): CityPresentation {
    return CityPresentation(
        coord = coord.toCoordPresentation(),
        id = id,
        name = name,
        country = country,
    )
}

fun CityWeatherDomain.toCityWeatherPresentation():CityWeatherPresentation{
    return CityWeatherPresentation(
        clouds = clouds.toCloudsPresentation(),
        dt = dt,
        dt_txt = dt_txt,
        main = main.toMainPresentation(),
        pop = pop,
        visibility = visibility,
        wind = wind.toWindPresentation(),

    )
}




//fun ForecastDomain.toCityWeather(): CityWeatherPresentation {
//    return CityWeatherPresentation(
//        clouds = clouds.toCloudsPresentation(),
//        dt = dt,
//        dt_txt = dt_txt,
//        main = main.toMainPresentation(),
//        pop = pop,
//        visibility = visibility,
//        wind = wind.toWindPresentation()
//    )
//}
//
//fun WeatherForecastDomain.toWeatherForecastPresentation(): WeatherForecastPresentation {
//    return WeatherForecastPresentation(
//        city = city.toCityPresentation(),
//        list = list.map { it.toCityWeather() }
//    )
//}


