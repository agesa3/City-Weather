package com.agesadev.data.remote


import com.agesadev.data.BuildConfig
import com.agesadev.data.remote.dtos.ForecastWeatherApiResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {


    @GET("forecast")
    suspend fun getWeatherForecast(
        @Query("q") city: String,
        @Query("appid") apiKey: String = BuildConfig.API_KEY,
    ): ForecastWeatherApiResponse

}