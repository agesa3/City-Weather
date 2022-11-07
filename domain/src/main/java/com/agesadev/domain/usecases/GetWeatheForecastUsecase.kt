package com.agesadev.domain.usecases

import com.agesadev.domain.repositories.WeatherRepository
import javax.inject.Inject


class GetWeatheForecastUsecase @Inject constructor(
    private val weatherRepository: WeatherRepository
) {
    operator fun invoke(cityName: String) = weatherRepository.getCityWeatherByCityName(cityName)
}