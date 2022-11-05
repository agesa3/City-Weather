package com.agesadev.domain.repositories

import com.agesadev.common.utils.Resource
import com.agesadev.domain.models.WeatherDomain
import kotlinx.coroutines.flow.Flow

interface WeatherRepository {
    fun getCityWeatherByCityName(cityName: String): Flow<Resource<WeatherDomain>>
}