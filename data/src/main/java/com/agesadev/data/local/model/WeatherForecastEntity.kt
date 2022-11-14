package com.agesadev.data.local.model

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.agesadev.common.Constants.WEATHER_FORECAST_TABLE_NAME
import com.agesadev.data.mappers.toCityDomain
import com.agesadev.data.mappers.toDomain
import com.agesadev.data.remote.dtos.City
import com.agesadev.data.remote.dtos.CityWeatherDto
import com.agesadev.domain.models.ForecastWeatherDomain

@Entity(tableName = WEATHER_FORECAST_TABLE_NAME)
data class WeatherForecastEntity(
    @Embedded
    val city: City,
    val cnt: Int,
    val cod: String,
    val list: List<CityWeatherDto>,
    val message: Int
){
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "city_id")
    var cityId: Int = city.id
}


fun WeatherForecastEntity.toWeatherForeCastDomain(): ForecastWeatherDomain {
    return ForecastWeatherDomain(
        city = city.toCityDomain(),
        cnt = cnt,
        cod = cod,
        list = list.map { it.toDomain() },
        message = message
    )
}
