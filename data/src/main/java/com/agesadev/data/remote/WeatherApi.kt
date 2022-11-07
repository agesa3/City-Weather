package com.agesadev.data.remote

import com.agesadev.data.remote.model.ForecastWeatherApiResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {
    companion object {
        const val API_KEY = "094aa776d64c50d5b9e9043edd4ffd00"
    }

//    https://api.openweathermap.org/data/2.5/forecast?q=mombasa&appid=7362ac15e5d7d4717974fe7fd0f39e9d
    @GET("forecast")
    suspend fun getWeatherForecast(
        @Query("q") city: String,
        @Query("appid") apiKey: String = API_KEY
    ): ForecastWeatherApiResponse

}