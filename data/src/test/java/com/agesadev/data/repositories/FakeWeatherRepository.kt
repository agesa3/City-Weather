package com.agesadev.data.repositories

import com.agesadev.common.utils.Resource
import com.agesadev.domain.models.*
import com.agesadev.domain.repositories.WeatherRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


class FakeWeatherRepository : WeatherRepository {

    val forecastWeatherDomain = ForecastWeatherDomain(
        city = CityDomain(
            id = 0,
            name = "Kisumu",
            coord = CoordDomain(
                lat = 0.0,
                lon = 0.0
            ),
            country = "EG",
            population = 0,
            timezone = 0,
            sunrise = 0,
            sunset = 0
        ),
        cnt = 0,
        cod = "200",
        list = listOf(
            CityWeatherDomain(
                dt = 0,
                main = MainDomain(
                    temp = 0.0,
                    feels_like = 0.0,
                    temp_min = 0.0,
                    temp_max = 0.0,
                    pressure = 0,
                    humidity = 0,
                ),
                clouds = CloudsDomain(
                    all = 0
                ),
                wind = WindDomain(
                    speed = 0.0,
                    deg = 0
                ),
                visibility = 0,
                pop = 0.0,
                dt_txt = "2021-09-01 12:00:00",
                weather = listOf(
                    WeatherDomain(
                        id = 0,
                        main = "main",
                        description = "description",
                        icon = "icon"
                    )
                )
            )
        ),
        message = 0
    )

    override fun getCityWeatherByCityName(cityName: String): Flow<Resource<ForecastWeatherDomain>> {
        return flow {
            emit(Resource.Success(forecastWeatherDomain))
        }
    }


}