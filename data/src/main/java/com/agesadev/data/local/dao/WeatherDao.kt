package com.agesadev.data.local.dao

import androidx.room.*
import com.agesadev.data.local.model.WeatherForecastEntity
import kotlinx.coroutines.flow.Flow


@Dao
interface WeatherDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWeatherForecast(weatherForecastEntity: WeatherForecastEntity)

    @Query("SELECT * FROM weather_forecast WHERE name LIKE '%' || :cityName || '%'")
    suspend fun getWeatherForecastByCityName(cityName: String): WeatherForecastEntity


    @Query("DELETE FROM weather_forecast")
    suspend fun deleteAllWeatherForecast()
}