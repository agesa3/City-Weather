package com.agesadev.data.local.model

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.agesadev.data.mappers.toCityDomain
import com.agesadev.data.mappers.toDomain
import com.agesadev.data.remote.dtos.City
import com.agesadev.data.remote.dtos.CityWeatherDto
import com.agesadev.domain.models.ForecastWeatherDomain

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
