package com.agesadev.data.local.dao

import androidx.room.*
import com.agesadev.data.local.model.WeatherForecastEntity
import kotlinx.coroutines.flow.Flow


//data class WeatherForecastEntity(
//    @PrimaryKey(autoGenerate = true)
//    val id: Int,
//    val name: String,
//    @Embedded
//    val coord: Coord,
//    val weather: List<Weather>,
//    @Embedded
//    val main: Main,
//    @Embedded
//    val wind: Wind,
//    @Embedded
//    val clouds: Clouds,
//    val dt: Int,
//    val sys: Sys,
//    val timezone: Int,
//    val cod: Int
//)

@Dao
interface WeatherDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWeatherForecast(weatherForecastEntity: WeatherForecastEntity)

    @Query("SELECT * FROM weather_forecast WHERE name LIKE '%' || :cityName || '%'")
    suspend fun getWeatherForecastByCityName(cityName: String): WeatherForecastEntity


    @Query("DELETE FROM weather_forecast")
    suspend fun deleteAllWeatherForecast()
}