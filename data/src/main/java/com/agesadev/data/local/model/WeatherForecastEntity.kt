package com.agesadev.data.local.model

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.agesadev.data.mappers.toCityDomain
import com.agesadev.data.remote.model.City
import com.agesadev.data.remote.model.CityWeatherDto
import com.agesadev.data.remote.model.toDomain
import com.agesadev.domain.models.newmodel.ForecastWeatherDomain

@Entity(tableName = "weather_forecast")
data class WeatherForecastEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "forecast_id")
    val id: Int,
    @Embedded
    val city: City,
    val cnt: Int,
    val cod: String,
    val list: List<CityWeatherDto>,
    val message: Int
)


fun WeatherForecastEntity.toWeatherForeCastDomain(): ForecastWeatherDomain {
    return ForecastWeatherDomain(
        city = city.toCityDomain(),
        cnt = cnt,
        cod = cod,
        list = list.map { it.toDomain() },
        message = message
    )
}

//Required:
//WeatherForecastEntity
//Found:
//ForecastWeatherApiResponse





//
//fun WeatherApiResponse.toWeatherForecastEntity(): WeatherForecastEntity {
//    return WeatherForecastEntity(
//        id = 0,
//        name = name ?: "",
//        coord = coord ?: Coord(0.0, 0.0),
//        main = main ?: Main(0.0, 0, 0, 0, 0, 0.0),
//        wind = wind ?: Wind(0, 0.0),
//        clouds = clouds ?: Clouds(0),
//        dt = dt ?: 0,
//        sys = sys ?: Sys("", 0, 0),
//        timezone = timezone ?: 0,
//        cod = cod ?: 0
//    )
//
//}
//
//
//fun WeatherForecastEntity.toWeatherForecastDomain(): WeatherDomain {
//    return WeatherDomain(
//        coord = coord?.toCoordDomain() ?: CoordDomain(0.0, 0.0),
//        main = main?.toMainDomain() ?: MainDomain(0.0, 0, 0, 0.0, 0.0, 0.0),
//        wind = wind?.toWindDomain() ?: WindDomain(0.0, 0),
//        clouds = clouds?.toCloudsDomain() ?: CloudsDomain(0),
//        dt = dt ?: 0,
//        sys = sys?.toSysDomain() ?: SysDomain("", 0, 0),
//        timezone = timezone ?: 0,
//        id = id,
//        name = name ?: "",
//        cod = cod ?: 0,
//        visibility = 0,
//        weather = emptyList()
//
//    )
//}