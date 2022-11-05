package com.agesadev.data.remote

import com.agesadev.data.remote.models.WeatherApiResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {
    companion object {
        const val API_KEY = "b1b15e88fa797225412429c1c50c122a1"
    }

    @GET("weather")
    suspend fun getWeatherByCityName(
        @Query("q") city: String,
        @Query("appid") apiKey: String = API_KEY
    ): WeatherApiResponse
//    https://api.openweathermap.org/data/2.5/weather?q=MOMBASA&appid=094aa776d64c50d5b9e9043edd4ffd00
//    @GET("weather")
}