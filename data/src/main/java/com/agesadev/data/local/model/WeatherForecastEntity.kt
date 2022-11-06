package com.agesadev.data.local.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.agesadev.data.remote.models.*

@Entity(tableName = "weather_forecast")
data class WeatherForecastEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    @Embedded
    val coord: Coord,
    @Embedded
    val main: Main,
    @Embedded
    val wind: Wind,
    @Embedded
    val clouds: Clouds,
    val dt: Int,
    @Embedded
    val sys: Sys,
    val timezone: Int,
    val cod: Int
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