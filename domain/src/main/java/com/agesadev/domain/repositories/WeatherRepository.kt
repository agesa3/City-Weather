package com.agesadev.domain.repositories

import com.agesadev.common.utils.Resource
import com.agesadev.domain.models.WeatherDomain
import com.agesadev.domain.models.moredays.WeatherForecastDomain
import kotlinx.coroutines.flow.Flow

interface WeatherRepository {
    fun getCityWeatherByCityName(cityName: String): Flow<Resource<WeatherDomain>>
    fun getCityWeatherByLatLon(lat: Double, lon: Double): Flow<Resource<WeatherForecastDomain>>
    fun getCityWeatherFromDb(cityName: String): Flow<WeatherDomain>
}