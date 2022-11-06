package com.agesadev.data.local.dao

import androidx.room.*
import com.agesadev.data.local.model.WeatherForecastEntity
import com.agesadev.data.remote.models.*
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

    @Query("SELECT * FROM weather_forecast")
    fun getWeatherForecast(): Flow<List<WeatherForecastEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWeatherForecast(weatherForecastEntity: WeatherForecastEntity)

    //    get weather by city name
    @Query("SELECT * FROM weather_forecast WHERE name = :name")
    suspend fun getWeatherForecastByName(name: String): WeatherForecastEntity

    @Query("DELETE FROM weather_forecast")
    suspend fun deleteAllWeatherForecast()
}