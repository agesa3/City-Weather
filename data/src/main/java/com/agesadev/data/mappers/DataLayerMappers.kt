package com.agesadev.data.mappers

import com.agesadev.data.local.model.WeatherForecastEntity
import com.agesadev.data.remote.model.*
import com.agesadev.domain.models.*
import com.agesadev.domain.models.newmodel.CityDomain
import com.agesadev.domain.models.moredays.ForecastDomain
import com.agesadev.domain.models.moredays.WeatherForecastDomain


//fun WeatherApiResponse.toWeatherDomain(): WeatherDomain {
//    return WeatherDomain(
//        coord = coord?.toCoordDomain() ?: CoordDomain(0.0, 0.0),
//        weather = weather?.map { it.toWeatherDomainLayer() } ?: emptyList(),
//        main = main?.toMainDomain() ?: MainDomain(0.0, 0, 0, 0.0, 0.0, 0.0),
//        visibility = visibility ?: 0,
//        wind = wind?.toWindDomain() ?: WindDomain(0.0, 0),
//        clouds = clouds?.toCloudsDomain() ?: CloudsDomain(0),
//        dt = dt ?: 0,
//        sys = sys?.toSysDomain() ?: SysDomain("", 0, 0),
//        timezone = timezone ?: 0,
//        id = id ?: 0,
//        name = name ?: "",
//        cod = cod ?: 0
//    )
//}

fun Coord.toCoordDomain(): CoordDomain {
    return CoordDomain(
        lon = lon ?: 0.0,
        lat = lat ?: 0.0
    )
}


fun Main.toMainDomain(): MainDomain {
    return MainDomain(
        temp = temp ?: 0.0,
        feels_like = feels_like ?: 0.0,
        temp_min = temp_min ?: 0.0,
        temp_max = temp_max ?: 0.0,
        pressure = pressure ?: 0,
        humidity = humidity ?: 0
    )
}


//fun Weather.toWeatherDomainLayer(): WeatherDomainLayer {
//    return WeatherDomainLayer(
//        description = description ?: "",
//        icon = icon ?: "",
//        id = id ?: 0,
//        main = main ?: ""
//    )
//}

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

//fun Sys.toSysDomain(): SysDomain {
//    return SysDomain(
//        country = country ?: "",
//        sunrise = sunrise ?: 0,
//        sunset = sunset ?: 0,
//    )
//}

//fun WeatherForecastDto.toForecastDomain(): ForecastDomain {
//    return ForecastDomain(
//        clouds = clouds?.toCloudsDomain() ?: CloudsDomain(0),
//        dt = dt ?: 0,
//        dt_txt = dt_txt ?: "",
//        main = main?.toMainDomain() ?: MainDomain(0.0, 0, 0, 0.0, 0.0, 0.0),
//        pop = pop ?: 0.0,
//        sys = sys?.toSysDomain() ?: SysDomain("", 0, 0),
//        visibility = visibility ?: 0,
//        weather = weather?.map { it.toWeatherDomainLayer() } ?: listOf(),
//        wind = wind?.toWindDomain() ?: WindDomain(0.0, 0)
//    )
//}

//fun ForecastWeatherApiResponse.toCityForeCast(): WeatherForecastDomain {
//    return WeatherForecastDomain(
//        city = city?.toCityDomain() ?: CityDomain(
//            coord = CoordDomain(0.0, 0.0),
//            country = "",
//            id = 0,
//            name = "",
//            population = 0,
//            sunrise = 0,
//            sunset = 0,
//            timezone = 0
//        ),
//        list = list?.map { it.toForecastDomain() } ?: listOf()
//    )
//}


fun City.toCityDomain(): CityDomain {
    return CityDomain(
        coord = coord?.toCoordDomain() ?: CoordDomain(0.0, 0.0),
        country = country ?: "",
        id = id ?: 0,
        name = name ?: "",
        population = population ?: 0,
        sunrise = sunrise ?: 0,
        sunset = sunset ?: 0,
        timezone = timezone ?: 0,
    )
}

//Type mismatch.
//Required:
//List<WeatherForecastEntity>
//Found:
//List<WeatherForecastDto>?
//
//fun WeatherForecastDto.toWeatherForecastEntity(): WeatherForecastEntity {
//    return WeatherForecastEntity(
//        clouds = clouds,
//        dt = dt ?: 0,
//        main = main,
//        wind = wind,
//        )
//}