package com.agesadev.data.remote

import com.agesadev.data.remote.models.WeatherApiResponse
import com.agesadev.data.remote.models.dtos.ForecaseWeatherApiResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {
    companion object {
        const val API_KEY = "094aa776d64c50d5b9e9043edd4ffd00"
    }

    @GET("weather")
    suspend fun getWeatherByCityName(
        @Query("q") city: String,
        @Query("appid") apiKey: String = API_KEY
    ): WeatherApiResponse

    //    https://api.openweathermap.org/data/2.5/forecast?lat=44.34&lon=10.99&appid=094aa776d64c50d5b9e9043edd4ffd00
    @GET("forecast")
    suspend fun getWeatherByCityCoord(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("appid") apiKey: String = API_KEY
    ): ForecaseWeatherApiResponse
}