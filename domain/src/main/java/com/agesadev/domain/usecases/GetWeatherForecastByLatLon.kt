package com.agesadev.domain.usecases

import com.agesadev.domain.repositories.WeatherRepository
import javax.inject.Inject

class GetWeatherForecastByLatLon @Inject constructor(
    private val weatherRepository: WeatherRepository
) {
    operator fun invoke(lat: Double, lon: Double) =
        weatherRepository.getCityWeatherByLatLon(lat, lon)
}