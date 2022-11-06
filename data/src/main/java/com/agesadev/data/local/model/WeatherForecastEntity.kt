package com.agesadev.data.local.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.agesadev.data.mappers.*
import com.agesadev.data.remote.models.*
import com.agesadev.domain.models.*

@Entity(tableName = "weather_forecast")
data class WeatherForecastEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String?=null,
    @Embedded
    val coord: Coord?=null,
    @Embedded
    val main: Main?=null,
    @Embedded
    val wind: Wind?=null,
    @Embedded
    val clouds: Clouds?=null,
    val dt: Int?=null,
    @Embedded
    val sys: Sys?=null,
    val timezone: Int?=null,
    val cod: Int?=null,
)


fun WeatherApiResponse.toWeatherForecastEntity(): WeatherForecastEntity {
    return WeatherForecastEntity(
        id = 0,
        name = name ?: "",
        coord = coord ?: Coord(0.0, 0.0),
        main = main ?: Main(0.0, 0, 0, 0, 0, 0.0),
        wind = wind ?: Wind(0, 0.0),
        clouds = clouds ?: Clouds(0),
        dt = dt ?: 0,
        sys = sys ?: Sys("", 0, 0),
        timezone = timezone ?: 0,
        cod = cod ?: 0
    )

}


fun WeatherForecastEntity.toWeatherForecastDomain(): WeatherDomain {
    return WeatherDomain(
        coord = coord?.toCoordDomain() ?: CoordDomain(0.0, 0.0),
        main = main?.toMainDomain() ?: MainDomain(0.0, 0, 0, 0.0, 0.0, 0.0),
        wind = wind?.toWindDomain() ?: WindDomain(0.0, 0),
        clouds = clouds?.toCloudsDomain() ?: CloudsDomain(0),
        dt = dt ?: 0,
        sys = sys?.toSysDomain() ?: SysDomain("", 0, 0),
        timezone = timezone ?: 0,
        id = id,
        name = name ?: "",
        cod = cod ?: 0,
        visibility = 0,
        weather = emptyList()

    )
}